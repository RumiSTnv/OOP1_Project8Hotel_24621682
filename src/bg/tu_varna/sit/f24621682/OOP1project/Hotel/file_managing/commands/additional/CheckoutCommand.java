package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.MainCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsAvailability;

public class CheckoutCommand {

    public void checkOut(String[] parts,
                         RoomManaging roomManaging,
                         ReservationsManaging reservationsManaging,
                         RoomsAvailability roomsAvailability,
                         MainCommands mainCommands) {

        try {
            if (parts.length < 2) {
                System.out.println("Usage: checkout <room>");
                return;
            }

            int roomNumber = Integer.parseInt(parts[1]);
            boolean removed = false;

            for (int i = 0; i < reservationsManaging.getReservations().size(); i++) {
                Reservation reservation = reservationsManaging.getReservations().get(i);

             if (reservation.getRoomNumber() == roomNumber) {
                reservationsManaging.getReservations().remove(i);
                removed = true;

                Room room = roomManaging.findRoomsByRoomNumber(roomNumber);
                if (room != null) {
                    room.setRoomStatus(RoomStatus.AVAILABLE);
                    roomsAvailability.addFreeRoom(room);
                }

                break;
            }
        }

        if (removed) {
            mainCommands.save();
            System.out.println("Room " + roomNumber + " checked out successfully.");
        } else {
            throw new InvalidDataException("Reservation for room " + roomNumber + " not found!");
        }
        } catch (Exception e) {
            throw new InvalidDataException("Invalid room number format!");
        }
    }
}
