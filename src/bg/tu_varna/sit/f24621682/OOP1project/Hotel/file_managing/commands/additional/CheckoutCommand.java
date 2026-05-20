package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;
/**
 * Команда за напускане на хотела и освобождаване на стая.
 * <p>
 * Прекратява активната резервация за дадена стая, променя нейния статус
 * на свободна и я връща обратно в списъка с налични стаи за настаняване.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class CheckoutCommand extends Command {

    private final HotelState state;
    /**
     * Конструира команда за освобождаване на стая.
     *
     * @param state текущото споделено състояние на хотелската система
     */
    public CheckoutCommand(HotelState state) {
        super("checkout", "checkout <room> - checking out and removing reservation");
        this.state = state;
    }
    /**
     * Изпълнява операцията по напускане (checkout).
     * <p>
     * Парсва входящия низ, намира резервацията по номер на стая, премахва я,
     * и актуализира статуса на стаята на {@code RoomStatus.AVAILABLE}.
     * </p>
     *
     * @param input текстовият низ, въведен от потребителя (напр. "checkout 112")
     */
    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            int roomNumber = Integer.parseInt(parts[1]);
            Reservation target = null;

            for (Reservation r : state.getReservations().getReservations()) {
                if (r.getRoomNumber() == roomNumber) {
                    target = r;
                    break;
                }
            }

            if (target == null) {
                throw new InvalidDataException("Reservation not found.");
            }

            state.getReservations().removeReservation(target);

            Room room = state.getRoomManaging().findRoomsByRoomNumber(roomNumber);

            if (room != null) {
                room.setRoomStatus(RoomStatus.AVAILABLE);
                state.getFreeRooms().addFreeRoom(room);
            }

            System.out.println(successfulExecutionMessage() + ". Checkout successful.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}