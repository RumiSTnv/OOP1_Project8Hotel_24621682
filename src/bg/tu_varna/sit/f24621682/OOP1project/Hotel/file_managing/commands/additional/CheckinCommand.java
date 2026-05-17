package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckinCommand extends Command {

    private final HotelState state;

    public CheckinCommand(HotelState state) {
        super("checkin", "checkin <room> <from> <to> <note> [guests] [<guest name> <activity>]");
        this.state = state;
    }

    @Override
    public void execute(String input)  {

        try {
            String[] parts = input.split(" ");

            if (parts.length < 5) {
                throw new InvalidDataException("Invalid input!");
            }

            int roomNumber = Integer.parseInt(parts[1]);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);
            String note = parts[4];

            if (!from.before(to)) {
                throw new InvalidDataException("Start date must be BEFORE end date!");
            }

            Room room = state.getRoomManaging().findRoomsByRoomNumber(roomNumber);

            if (room == null) {
                throw new NotFoundException("Room not found!");
            }

            int guestsCount = (parts.length == 6)
                    ? Integer.parseInt(parts[5])
                    : room.getNumberOfBeds();

            if (guestsCount > room.getNumberOfBeds()) {
                throw new InvalidDataException("Too many guests! Room has only " + room.getNumberOfBeds() + " beds.");
            }

            if (state.getReservations().isRoomOccupied(
                    room, from, to, state.getReservations().getReservations())) {
                throw new NotFoundException("Room occupied!");
            }

            Reservation reservation = new Reservation(roomNumber, from, to, note, guestsCount);

            int currentIndex = 6;

            for (int i = 0; i < guestsCount  && currentIndex < parts.length; i++) {

                if (currentIndex + 1 >= parts.length) {
                    throw new InvalidDataException("Invalid guest data!");
                }

                String fullName = parts[currentIndex] + " " + parts[currentIndex + 1];

                reservation.addGuest(new Guest(fullName));
                currentIndex++;

            }

            while (currentIndex < parts.length)
            {
                String raw = parts[currentIndex].toUpperCase();

                RoomActivity type;

                try {
                    type = RoomActivity.valueOf(raw);
                } catch (IllegalArgumentException e) {
                    throw new InvalidDataException("Invalid activity: " + parts[currentIndex]);
                }

                Activity activity = new Activity(type, from, to);

                reservation.addActivity(activity);
                currentIndex++;
            }

            state.getReservations().addReservation(reservation);

            state.getFreeRooms().removeFreeRoom(room);

            System.out.println("Reservation added.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}