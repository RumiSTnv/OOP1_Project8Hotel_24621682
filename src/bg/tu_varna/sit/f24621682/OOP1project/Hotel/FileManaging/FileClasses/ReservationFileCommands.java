package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Reservation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservationFileCommands extends FileCommands {
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public void open(String path) {
        try{
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
                System.out.println("File created");
            }
            this.filePath = path;
            isOpen = true;

            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while((line = bufferedReader.readLine()) != null){
                try {
                    String[] splitting = line.split(" ", 4);
                    int roomNumber = Integer.parseInt(splitting[0]);
                    Date startDate = dateFormat.parse(splitting[1]);
                    Date endDate = dateFormat.parse(splitting[2]);
                    String note = splitting[3];
                    reservations.add(new Reservation(roomNumber, startDate, endDate, note, 0));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            bufferedReader.close();

            System.out.println("File opened");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkIn(Scanner scanner, RoomManaging roomManaging, FreeRoomsFileCommands freeRoomsFile) {
        if(!isOpen){
            System.out.println("Please enter the room number");
            return;
        }
        int roomNumber = scanner.nextInt();
        String start = scanner.next();
        String end = scanner.next();
        String note = scanner.nextLine();;

        try{
            Date startDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(start);
            Date endDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(end);

            Room room = roomManaging.findRoomsByRoomNumber(roomNumber);
            if (!freeRoomsFile.getFreeRooms().contains(room)) {
                System.out.println("Room is already occupied!");
                return;
            }

            if(room == null){
                System.out.println("Room not found");
                return;
            }

            if(room.getRoomStatus() == RoomStatus.OCCUPIED){
                System.out.println("Room is occupied");
                return;
            }

            int guestNumber = room.getNumberOfBeds();
            Reservation reservation = new Reservation(roomNumber, startDate, endDate, note, guestNumber);
            reservations.add(reservation);
            freeRoomsFile.removeFreeRoom(room);
            room.setRoomStatus(RoomStatus.OCCUPIED);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        if(!isOpen){
            return;
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

            for(Reservation reservation : reservations){
                writer.write(reservation.getRoomNumber() + " "
                        + dateFormat.format(reservation.getStartDate()) + " "
                + dateFormat.format(reservation.getEndDate()));
                writer.newLine();
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");

        }catch(Exception e){
            System.out.println("Error writing to the file.");
        }
    }
}
