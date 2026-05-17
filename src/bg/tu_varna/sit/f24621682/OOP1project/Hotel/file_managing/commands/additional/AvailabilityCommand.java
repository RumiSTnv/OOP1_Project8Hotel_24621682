package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AvailabilityCommand extends Command {

    private final HotelState state;

    public AvailabilityCommand(HotelState state) {
        super("availability", "availability [<date>] - show free rooms");
        this.state = state;
    }

    @Override
    public void execute(String input) {

        try {

            String[] parts = input.split("\\s+");

            Date checkDate = new Date();

            if (parts.length == 2) {

                SimpleDateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd");

                checkDate = format.parse(parts[1]);

            } else if (parts.length > 2) {
                throw new InvalidDataException("Usage: availability [date]");
            }

            boolean found = false;

            for (Room room : state.getRoomManaging().getAllRooms()) {

                if (state.getFreeRooms().isRoomFreeByDay(
                        room,
                        checkDate,
                        state.getReservations().getReservations())) {

                    System.out.println(
                            room.getRoomNumber()
                                    + " "
                                    + room.getNumberOfBeds()
                    );

                    found = true;
                }
            }

            if (!found) {
                throw new NotFoundException("No available rooms!");
            }

        } catch (Exception e) {
            throw new InvalidDataException("Invalid date format!");
        }
    }
}