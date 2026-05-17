package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class SaveCommand extends Command {
    private final HotelState state;

    public SaveCommand(HotelState state) {
        super("save", "Saves file");
        this.state = state;
    }

    @Override
    public void execute(String input) {
        System.out.println("FREE ROOMS: " + state.getFreeRooms().getFreeRooms().size());
        System.out.println("RES: " + state.getReservations().getReservations().size());
        System.out.println("UNAV: " + state.getUnavailableRooms().getUnavailableRooms().size());
        System.out.println("ALL ROOMS: " + state.getRoomManaging().getAllRooms().size());
        if (!state.isOpen() || state.getFilePath() == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(state.getFilePath()))) {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            writer.write("ROOMS");
            writer.newLine();
            for (Room r : state.getRoomManaging().getAllRooms()) {
                writer.write(r.getRoomNumber() + " " + r.getNumberOfBeds());
                writer.newLine();
            }
          writer.write("END_ROOMS");
            writer.newLine();
writer.write("RESERVATIONS");
            writer.newLine();
            for (Reservation r : state.getReservations().getReservations()) {
                writer.write(r.getRoomNumber() + " "
                        + df.format(r.getStartDate()) + " "
                        + df.format(r.getEndDate()) + " "
                        + r.getNote() + " "
                        + r.getGuestNumber() + " "
                        + r.guestsToString() + " "
                        + r.activitiesToString());
                writer.newLine();
            }
            writer.write("END_RESERVATIONS");
            writer.newLine();
            writer.write("UNAVAILABLE_ROOMS");
            writer.newLine();
            for (UnavailablePeriod u : state.getUnavailableRooms().getUnavailableRooms()) {
                writer.write(u.getRoomNumber() + " "
                        + df.format(u.getStartDate()) + " "
                        + df.format(u.getEndDate()) + " "
                        + u.getNote());
                writer.newLine();
            }
            writer.write("END_UNAVAILABLE_ROOMS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
