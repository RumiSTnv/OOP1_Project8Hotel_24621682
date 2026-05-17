package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.SaveCommand;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnavailabilityCommand extends Command {

    private final HotelState state;
    private SaveCommand saveCommand;

    public UnavailabilityCommand(HotelState state) {
        super("unavailable", "mark room unavailable");
        this.state = state;
    }

    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            int roomNumber = Integer.parseInt(parts[1]);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);
            String note = parts[4];

            Room room = state.getRoomManaging().findRoomsByRoomNumber(roomNumber);

            if (room == null) return;

            UnavailablePeriod period =
                    new UnavailablePeriod(roomNumber, from, to, note);

            state.getUnavailableRooms().addUnavailableRooms(period);
            state.getFreeRooms().removeFreeRoom(room);

         //   saveCommand.execute("");

            System.out.println("Room marked unavailable.");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}