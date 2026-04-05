package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Reservation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationFileCommands extends FileCommands {
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public void open(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created");
            }

            this.filePath = path;
            isOpen = true;
            reservations.clear();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((line = reader.readLine()) != null) {
                try {
                    String[] splitting = line.split(" ", 5);

                    int roomNumber = Integer.parseInt(splitting[0]);
                    Date startDate = dateFormat.parse(splitting[1]);
                    Date endDate = dateFormat.parse(splitting[2]);

                    String note = "";
                    for (int i = 3; i < splitting.length - 1; i++) {
                        note += splitting[i] + " ";
                    }

                    note = note.trim();
                    int guests = Integer.parseInt(splitting[splitting.length - 1]);

                    reservations.add(new Reservation(roomNumber, startDate, endDate, note, 0));
                } catch (Exception e) {
                    System.out.println("Invalid line: " + line);
                }
            }

            reader.close();
            System.out.println("File opened");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkIn(Scanner scanner, RoomManaging roomManaging) {
        if (!isOpen) {
            System.out.println("File is not open!");
            return;
        }

        try {
            int roomNumber = scanner.nextInt();
            String start = scanner.next();
            String end = scanner.next();
            String note = scanner.nextLine().trim();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);

            Room room = roomManaging.findRoomsByRoomNumber(roomNumber);

            if (room == null) {
                System.out.println("Room not found");
                return;
            }

            // проверка дали е заета за този период
            for (Reservation res : reservations) {
                if (res.getRoomNumber() == roomNumber) {

                    if (!(endDate.before(res.getStartDate()) ||
                            startDate.after(res.getEndDate()))) {

                        System.out.println("Room is occupied for this period!");
                        return;
                    }
                }
            }

            int guests = room.getNumberOfBeds();

            reservations.add(new Reservation(roomNumber, startDate, endDate, note, guests));

            save();
            System.out.println("Reservation added!");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

    public void availability(RoomManaging roomManaging, Scanner scanner) {
        if (!isOpen) {
            System.out.println("File is not open!");
            return;
        }

        try {
            System.out.println("Enter date (yyyy-MM-dd) or press Enter for today:");
            String input = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkDate = input.isEmpty()
                    ? new Date()
                    : dateFormat.parse(input);

            boolean found = false;

            for (Room room : roomManaging.getAllRooms()) {
                boolean isOccupied = false;

                for (Reservation res : reservations) {
                    if (res.getRoomNumber() == room.getRoomNumber()) {

                        if (!checkDate.before(res.getStartDate()) &&
                                !checkDate.after(res.getEndDate())) {

                            isOccupied = true;
                            break;
                        }
                    }
                }

                if (!isOccupied) {
                    System.out.println(room.getRoomNumber() + " " + room.getNumberOfBeds());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No available rooms.");
            }

        } catch (Exception e) {
            System.out.println("Invalid date format!");
        }
    }


    @Override
    public void save() {
        if (!isOpen) return;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            for (Reservation r : reservations) {
                writer.write(r.getRoomNumber() + " "
                        + format.format(r.getStartDate()) + " "
                        + format.format(r.getEndDate()) + " "
                        + r.getNote() + " "
                        + r.getGuests());
                writer.newLine();
            }

            writer.close();
            System.out.println("Saved successfully");

        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }
}