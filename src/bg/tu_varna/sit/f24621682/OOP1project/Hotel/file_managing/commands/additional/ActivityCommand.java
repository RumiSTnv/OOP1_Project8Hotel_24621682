package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;

public class ActivityCommand extends Command {
    private HotelState state;

    public ActivityCommand(HotelState state) {
        super("activity","activity <activity name> - shows all rooms for that activity.");
        this.state = state;
    }

    @Override
    public void execute(String input) {
        try{
            String[] parts = input.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Usage: program <activity name>");
                return;
            }

            String activityName = parts[1];

            for(Reservation reservation : state.getReservations().getReservations()) {
                for (Activity activity : reservation.getActivities()) {

                    if (activity.getActivityType() == RoomActivity.valueOf(activityName.toUpperCase())) {

                        for (Guest guest : reservation.getGuests()) {
                            System.out.println(guest);
                        }
                    }
                }
            }

        }catch(InvalidDataException e){
            throw new InvalidDataException("Invalid activity command!");
        }
    }
}
