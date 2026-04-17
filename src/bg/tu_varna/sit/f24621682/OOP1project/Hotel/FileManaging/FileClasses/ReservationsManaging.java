package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Reservation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationsManaging {
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void clearReservations() {
        reservations.clear();
    }

    public void checkIn(Scanner scanner, RoomManaging roomManaging, FileCommands fileCommands) {

        try {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");

            if (parts.length < 5) {
                System.out.println("Invalid input");
                return;
            }

            int roomNumber = Integer.parseInt(parts[0]);
            String start = parts[1];
            String end = parts[2];
            String note = parts[3];
            int guests = Integer.parseInt(parts[4]);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);

            Room room = roomManaging.findRoomsByRoomNumber(roomNumber);

            if (room == null) {
                System.out.println("Room not found");
                return;
            }

            for (Reservation r : reservations) {
                if (r.getRoomNumber() == roomNumber) {
                    if (!(endDate.before(r.getStartDate()) ||
                            startDate.after(r.getEndDate()))) {

                        System.out.println("Room occupied!");
                        return;
                    }
                }
            }

            Reservation newRes = new Reservation(roomNumber, startDate, endDate, note, guests);
            reservations.add(newRes);

            fileCommands.getFreeRooms().removeFreeRoom(room);
            fileCommands.save();

            System.out.println("Reservation added");

        } catch (Exception e) {
            System.out.println("Invalid input");
        }

}

    /*public void checkOut(int roomNumber, FileCommands fileCommands,
                         RoomManaging roomManaging) {
        Iterator<Reservation> it = reservations.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Reservation reservation = it.next();

            if(reservation.getRoomNumber() == roomNumber) {
                it.remove();
                removed = true;

                Room room = roomManaging.findRoomsByRoomNumber(roomNumber);
                if(room != null) {
                    fileCommands.getFreeRooms().removeFreeRoom(room);
                }
                break;
            }
        }

        if(removed) {
           fileCommands.save();
        }
        else
            System.out.println("Reservation not found!");
    }*/
}