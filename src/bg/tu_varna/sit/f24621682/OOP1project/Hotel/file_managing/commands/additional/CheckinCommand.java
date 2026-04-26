package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.MainCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// клас за командата checkIn
public class CheckinCommand {

    public void checkIn(Scanner scanner, RoomManaging roomManaging, ReservationsManaging reservationsManaging,
                        MainCommands mainCommands, RoomsAvailability roomsAvailability) {
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

            if (!reservationsManaging.isRoomFreeFromDateToDate(room, startDate, endDate, reservationsManaging.getReservations())) {
                System.out.println("Room occupied!");
                return;
            }

            Reservation newRes = new Reservation(roomNumber, startDate, endDate, note, guests);
            reservationsManaging.getReservations().add(newRes);

            roomsAvailability.removeFreeRoom(room);
            mainCommands.save();

            System.out.println("Reservation added");

        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }
}
