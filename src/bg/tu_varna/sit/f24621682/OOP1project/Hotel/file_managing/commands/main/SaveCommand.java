package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
/**
 * Команда за сериализация и запис на текущото състояние обратно във файла.
 * <p>
 * Превръща всички обекти (стаи, резервации, технически периоди) в структуриран текст
 * и ги съхранява персистентно на диска.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class SaveCommand extends Command {
    private final HotelState state;
    /**
     * Конструира команда за запис на промените.
     *
     * @param state състоянието на хотела, съдържащо колекциите за запис
     */
    public SaveCommand(HotelState state) {
        super("save", "Saves file");
        this.state = state;
    }
    /**
     * Записва структурираната информация чрез BufferedWriter.
     *
     * @param input празен низ или аргументи за запис
     */
    @Override
    public void execute(String input) {
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
            writer.newLine();

            writer.write("ACTIVITY_RESULTS");
            writer.newLine();

            for (String result : state.getActivityManaging().getResults()) {
                writer.write(result);
                writer.newLine();
            }

            writer.write("END_ACTIVITY_RESULTS");
            writer.newLine();
            writer.write("ROOM_PROGRAM_RESULTS");
            writer.newLine();

            for (String r : state.getRoomProgramManaging().getProgram()) {
                writer.write(r);
                writer.newLine();
            }

            writer.write("END_ROOM_PROGRAM_RESULTS");
            writer.newLine();

            System.out.println(successfulExecutionMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
