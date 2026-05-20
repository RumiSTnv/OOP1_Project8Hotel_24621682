package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Команда за генериране на статистически отчет за заетостта на стаите.
 * <p>
 * Изчислява реално използваните дни за всяка стая в рамките на посочен период,
 * като взема предвид частичното застъпване на резервациите чрез Unix време.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class ReportCommand extends Command {

    private final HotelState state;
    /**
     * Конструира команда за генериране на репорти.
     *
     * @param state текущото споделено състояние на системата
     */
    public ReportCommand(HotelState state) {
        super("report", "report <from> <to> - shows all rooms with reservation for that period");
        this.state = state;
    }
    /**
     * Изчислява и отпечатва броя на използваните дни за стаите в дадения интервал.
     *
     * @param input потребителският вход съдържащ началната и крайната дата
     */
    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");
            if (parts.length < 3) {
                throw new InvalidDataException("Usage: report <from> <to>");
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date from = format.parse(parts[1]);
            Date to = format.parse(parts[2]);

            for (Reservation r : state.getReservations().getReservations()) {
                Date start = r.getStartDate().after(from) ? r.getStartDate() : from;
                Date end = r.getEndDate().before(to) ? r.getEndDate() : to;

                if (!start.after(end)) {
                    long days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
                    System.out.println(successfulExecutionMessage() + ". " + r.getRoomNumber() + " used " + days + " days");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}