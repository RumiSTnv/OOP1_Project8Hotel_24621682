package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportCommand extends Command {

    private final HotelState state;

    public ReportCommand(HotelState state) {
        super("report", "report <from> <to>");
        this.state = state;
    }

    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date from = format.parse(parts[1]);
            Date to = format.parse(parts[2]);

            for (Reservation r : state.getReservations().getReservations()) {

                Date start = r.getStartDate().after(from) ? r.getStartDate() : from;
                Date end = r.getEndDate().before(to) ? r.getEndDate() : to;

                if (!start.after(end)) {
                    long days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
                    System.out.println(r.getRoomNumber() + " used " + days + " days");
                }
            }

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}