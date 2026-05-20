package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Команда за проверка на наличните (свободни) стаи в хотела.
 * <p>
 * Може да приема конкретна дата като аргумент или, ако липсва такъв,
 * проверява заетостта на стаите спрямо текущата системна дата.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class AvailabilityCommand extends Command {

    private final HotelState state;
    /**
     * Конструира команда за проверка на наличността.
     *
     * @param state текущото споделено състояние на хотелската система
     */
    public AvailabilityCommand(HotelState state) {
        super("availability", "availability [<date>] - show free rooms");
        this.state = state;
    }
    /**
     * Изпълнява алгоритъма за извеждане на незаетите стаи.
     * <p>
     * Проверява всяка стая чрез метода {@code isRoomFreeByDay}.
     * Ако няма нито една свободна стая, хвърля изключение.
     * </p>
     *
     * @param input потребителският вход (напр. "availability" или "availability 2026-06-15")
     * @throws InvalidDataException ако форматът на датата е невалиден или аргументите са твърде много
     * @throws NotFoundException ако за посочения ден няма нито една свободна стая
     */
    @Override
    public void execute(String input) {

        try {

            String[] parts = input.split("\\s+");

            Date checkDate = new Date();

            if (parts.length == 2) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                checkDate = format.parse(parts[1]);

            } else if (parts.length > 2) {
                throw new InvalidDataException("Usage: availability [date]");
            }

            boolean found = false;

            for (Room room : state.getRoomManaging().getAllRooms()) {
                if (state.getFreeRooms().isRoomFreeByDay(room, checkDate, state.getReservations().getReservations())) {

                    System.out.println(room.getRoomNumber() + " " + room.getNumberOfBeds());
                    found = true;
                }
            }

            if (!found) {
                throw new NotFoundException("No available rooms!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}