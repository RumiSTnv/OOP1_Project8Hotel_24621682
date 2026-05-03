package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.MainCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsUnavailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsAvailability;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnavailabilityCommand {
    public void unavailable(String[] parts,
                            RoomManaging roomManaging,
                            ReservationsManaging reservationsManaging, RoomsAvailability roomsAvailability,
                            MainCommands mainCommands, RoomsUnavailability roomsUnavailability){

        try {

            if (parts.length != 5) {
                throw new InvalidDataException("Invalid input!");
            }

            int roomNumber = Integer.parseInt(parts[1]);
            String start = parts[2];
            String end = parts[3];
            String note = parts[4];

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date startDate = format.parse(start);
            Date endDate = format.parse(end);

            Room room = roomManaging.findRoomsByRoomNumber(roomNumber);

            if (room == null) {
                throw new NotFoundException("Room not found!");
            }

            if (!reservationsManaging.isRoomOccupied(
                    room, startDate, endDate,
                    reservationsManaging.getReservations())) {

                throw new NotFoundException("Room occupied!");
            }

            UnavailablePeriod unavailablePeriod = new UnavailablePeriod(roomNumber, startDate, endDate, note);

            roomsUnavailability.addUnavailableRooms(unavailablePeriod);
            roomsAvailability.removeFreeRoom(room);
            mainCommands.save();

            System.out.println("Room labeled as 'Unavailable' successfully!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
