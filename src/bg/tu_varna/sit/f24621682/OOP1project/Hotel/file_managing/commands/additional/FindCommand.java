package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindCommand extends Command {

    private final HotelState state;

    public FindCommand(HotelState state) {
        super("find", "find <beds> <from> <to>");
        this.state = state;
    }

    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            int beds = Integer.parseInt(parts[1]);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);

            Room best = null;

            for (Room room : state.getRoomManaging().getAllRooms()) {

                boolean occupied = state.getReservations()
                        .isRoomOccupied(room, from, to,
                                state.getReservations().getReservations());

                if (!occupied && room.getNumberOfBeds() >= beds) {

                    if (best == null ||
                            room.getNumberOfBeds() < best.getNumberOfBeds()) {
                        best = room;
                    }
                }
            }

            if (best == null) {
                throw new NotFoundException("No room found.");
            }

            System.out.println("Room: " + best.getRoomNumber());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}