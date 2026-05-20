package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Команда за извършване на регистрация и настаняване на гости (Check-in).
 * <p>
 * Това е основна бизнес команда, която обработва сложен входен низ (използващ редовни изрази),
 * валидира датите, проверява легловия капацитет и заетостта, след което генерира
 * нова резервация с прикачени гости и допълнителни услуги.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class CheckinCommand extends Command {

    private final HotelState state;
    /**
     * Конструира команда за регистрация и настаняване.
     *
     * @param state текущото споделено състояние на хотелската система
     */
    public CheckinCommand(HotelState state) {
        super("checkin", "checkin <room> <from> <to> <note> [guests] [<guest name> <activity>]");
        this.state = state;
    }
    /**
     * Изпълнява комплексната логика по създаване на резервация.
     * <p>
     * Парсва аргументите, проверява за хронологични конфликти и застъпвания,
     * добавя съответния брой обекти {@code Guest} и {@code Activity}, и накрая
     * маркира стаята като заета.
     * </p>
     *
     * @param input пълният команден ред с аргументи, въведен от потребителя
     */
    @Override
    public void execute(String input)  {

        try {
            String[] parts = input.trim().split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            if (parts.length < 5) {
                throw new InvalidDataException("Usage: checkin <room> <from> <to> <note> [guests] [<guest name> <activity>]");
            }

            int roomNumber = Integer.parseInt(parts[1]);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);
            String note = parts[4];

            if (!from.before(to)) {
                throw new InvalidDataException("Start date must be BEFORE end date!");
            }

            Room room = state.getRoomManaging().findRoomsByRoomNumber(roomNumber);

            if (room == null) {
                throw new NotFoundException("Room not found!");
            }

            int guestsCount = (parts.length == 6) ? Integer.parseInt(parts[5]) : room.getNumberOfBeds();

            if (guestsCount > room.getNumberOfBeds()) {
                throw new InvalidDataException("Too many guests! Room has only " + room.getNumberOfBeds() + " beds.");
            }

            if (state.getReservations().isRoomOccupied(room, from, to, state.getReservations().getReservations())) {
                throw new NotFoundException("Room occupied!");
            }

            Reservation reservation = new Reservation(roomNumber, from, to, note, guestsCount);
            int currentIndex = 6;

            for (int i = 0; i < guestsCount; i++) {

                if (currentIndex >= parts.length) {
                    throw new InvalidDataException("Not enough guest names provided!");
                }

                String fullName = parts[currentIndex].replace("\"", "");
                reservation.addGuest(new Guest(fullName));
                currentIndex ++;
            }

            while (currentIndex < parts.length)
            {
                String raw = parts[currentIndex].toUpperCase();
                RoomActivity type;

                try {
                    type = RoomActivity.valueOf(raw);
                } catch (IllegalArgumentException e) {
                    throw new InvalidDataException("Invalid activity: " + parts[currentIndex]);
                }

                Activity activity = new Activity(type, from, to);
                reservation.addActivity(activity);
                currentIndex++;
            }

            state.getReservations().addReservation(reservation);
            state.getFreeRooms().removeFreeRoom(room);
            System.out.println(successfulExecutionMessage() + ". Reservation added.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}