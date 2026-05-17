package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindImportantCommand extends Command {

    private HotelState state;
    private FindCommand findCommand;

    public FindImportantCommand(HotelState state, FindCommand findCommand) {
        super("find!", "find! <beds> <from> <to> - finds a suitable room for an important guest");
        this.state = state;
        this.findCommand = findCommand;
    }

    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            if (parts.length != 4) {
                System.out.println("Usage: find! <beds> <from> <to>");
                return;
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int beds = Integer.parseInt(parts[1]);
            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);

            boolean hasSingleRoom = false;

            for (Room room : state.getRoomManaging().getAllRooms()) {

                boolean occupied = state.getReservations().isRoomOccupied(
                        room, from, to,
                        state.getReservations().getReservations()
                );

                if (!occupied && room.getNumberOfBeds() >= beds) {
                    hasSingleRoom = true;
                    break;
                }
            }

            if (hasSingleRoom) {
                findCommand.execute(input);
                return;
            }

            for (int i = 0; i < state.getRoomManaging().getAllRooms().size(); i++) {

                for (int j = i + 1; j < state.getRoomManaging().getAllRooms().size(); j++) {

                    Room r1 = state.getRoomManaging().getAllRooms().get(i);
                    Room r2 = state.getRoomManaging().getAllRooms().get(j);

                    boolean occupied1 = state.getReservations().isRoomOccupied(
                            r1, from, to, state.getReservations().getReservations());

                    boolean occupied2 = state.getReservations().isRoomOccupied(
                            r2, from, to, state.getReservations().getReservations());

                    if (!occupied1 && !occupied2) {

                        int totalBeds = r1.getNumberOfBeds() + r2.getNumberOfBeds();

                        if (totalBeds >= beds) {

                            System.out.println("Split accommodation:");
                            System.out.println("Room " + r1.getRoomNumber());
                            System.out.println("Room " + r2.getRoomNumber());

                            return;
                        }
                    }
                }
            }

            System.out.println("No emergency accommodation possible.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}