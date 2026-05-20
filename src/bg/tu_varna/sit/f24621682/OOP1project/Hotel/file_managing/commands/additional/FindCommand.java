package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Команда за търсене на оптимална свободна стая по зададени критерии.
 * <p>
 * Намира стая, която е свободна в посочения период и чийто брой легла е
 * възможно най-близък до изисквания от потребителя (минимално превишаване).
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class FindCommand extends Command {

    private final HotelState state;
    /**
     * Конструира команда за търсене на стая.
     *
     * @param state текущото споделено състояние на хотелската система
     */
    public FindCommand(HotelState state) {
        super("find", "find <beds> <from> <to> - find a room by the given beds");
        this.state = state;
    }
    /**
     * Изпълнява алгоритъма за търсене на подходяща свободна стая.
     *
     * @param input потребителският вход (напр. "find 3 2026-06-01 2026-06-10")
     * @throws NotFoundException ако не бъде открита нито една свободна стая, отговаряща на условията
     */
    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            if (parts.length < 4) {
                throw new InvalidDataException("Usage: find <beds> <from> <to>");
            }

            int beds = Integer.parseInt(parts[1]);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);
            Room best = null;

            for (Room room : state.getRoomManaging().getAllRooms()) {

                boolean occupied = state.getReservations().isRoomOccupied(room, from, to,
                                state.getReservations().getReservations());

                if (!occupied && room.getNumberOfBeds() >= beds) {

                    if (best == null || room.getNumberOfBeds() < best.getNumberOfBeds()) {
                        best = room;
                    }
                }
            }

            if (best == null) {
                throw new NotFoundException("No room found.");
            }

            System.out.println(successfulExecutionMessage() + ". Room: " + best.getRoomNumber());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}