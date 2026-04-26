package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomsAvailability;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AvailabilityCommand {

    public void availability(ReservationsManaging reservationFile,
                             RoomManaging roomManaging,
                             Scanner scanner, RoomsAvailability roomsAvailability) {
        try {
            System.out.println("Enter date (yyyy-MM-dd) or press Enter for today:");
            String input = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkDate = input.isEmpty() ? new Date() : dateFormat.parse(input);

            boolean found = false;

            for (Room room : roomManaging.getAllRooms()) {
                if (roomsAvailability.isRoomFreeByDay(room, checkDate, reservationFile.getReservations())) {
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

}
