package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Reservation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FreeRoomsFileCommands {

    private List<Room> freeRooms;

    public FreeRoomsFileCommands() {
        freeRooms = new ArrayList<>();
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public void addFreeRoom(Room room) {
        if (!freeRooms.contains(room)) {
            room.setRoomStatus(RoomStatus.AVAILABLE);
            freeRooms.add(room);
        }
    }

    public void removeFreeRoom(Room room) {
        freeRooms.remove(room);
        room.setRoomStatus(RoomStatus.OCCUPIED);
    }

    /*public void open(String path, ReservationFileCommands reservationFile, RoomManaging roomManaging) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            this.filePath = path;
            isOpen = true;
            freeRooms.clear();

            for (Room room : roomManaging.getAllRooms()) {
                boolean isReserved = false;

                for (Reservation res : reservationFile.getReservations()) {
                    if (res.getRoomNumber() == room.getRoomNumber()) {
                        isReserved = true;
                        break;
                    }
                }

                if (!isReserved) {
                    room.setRoomStatus(RoomStatus.AVAILABLE);
                    freeRooms.add(room);
                }
            }

            save();
            System.out.println("Free rooms loaded: " + freeRooms.size());

        } catch (Exception e) {
            System.out.println("Error loading free rooms file");
        }
    }

    @Override
    public void save() {
        if (!isOpen) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Room room : freeRooms) {
                writer.write(room.getRoomNumber() + " " + room.getNumberOfBeds());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving free rooms file");
        }
    }

    @Override
    public void open(String path) {
    }

    public void availability(ReservationFileCommands reservationFile,
                             RoomManaging roomManaging,
                             Scanner scanner) {
        try {
            System.out.println("Enter date (yyyy-MM-dd) or press Enter for today:");
            String input = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkDate;

            if (input.isEmpty()) {
                checkDate = new Date();
            } else {
                checkDate = dateFormat.parse(input);
            }

            boolean found = false;

            for (Room room : roomManaging.getAllRooms()) {
                boolean isOccupied = false;

                for (Reservation res : reservationFile.getReservations()) {
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
    }*/
}