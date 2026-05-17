package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomProgramCommand extends Command {
    private HotelState state;

    public RoomProgramCommand(HotelState state) {
        super("program", "program <room> - shows the room's activity program");
        this.state = state;
    }

    @Override
    public void execute(String input) {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                System.out.println("Usage: program <room>");
                return;
            }

            int roomNumber = Integer.parseInt(parts[1]);

            for (Reservation reservation : state.getReservations().getReservations()) {
                if (reservation.getRoomNumber() == roomNumber) {
                    System.out.println("Program for room " + roomNumber + ":");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    for (Activity activity : reservation.getActivities()) {
                        String start = sdf.format(activity.getFrom());
                        String end = sdf.format(activity.getTo());
                        System.out.println(activity + " - " + start + " - " + end);
                    }
                }
            }
        }catch (InvalidDataException e){
            throw new InvalidDataException("Invalid room number!");
        }
    }
}
