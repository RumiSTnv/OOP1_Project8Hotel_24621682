package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;
/**
 * Команда за извеждане на справка по конкретна допълнителна дейност.
 * <p>
 * Търси във всички текущи резервации и филтрира кои гости в кои стаи са
 * заявили съответната активност. Резултатите се отпечатват на конзолата
 * и се записват в мениджъра на активности.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class ActivityCommand extends Command {
    private HotelState state;
    /**
     * Конструира команда за търсене на активност.
     *
     * @param state текущото споделено състояние на хотелската система
     */
    public ActivityCommand(HotelState state) {
        super("activity","activity <activity name> - shows all rooms for that activity.");
        this.state = state;
    }
    /**
     * Изпълнява филтрирането на резервациите по зададената дейност.
     * <p>
     * Сравнява текстовия вход с наличните изброими стойности в {@code RoomActivity}.
     * При успешно съвпадение извежда списък с гостите.
     * </p>
     *
     * @param input текстовият низ, въведен от потребителя (напр. "activity spa")
     */
    @Override
    public void execute(String input) {
        try{
            String[] parts = input.split("\\s+");
            if (parts.length < 2) {
                throw new InvalidDataException("Usage: program <activity name>");
            }

            String activityName = parts[1];

            state.getActivityManaging().clearResults();

            for(Reservation reservation : state.getReservations().getReservations()) {
                for (Activity activity : reservation.getActivities()) {

                    if (activity.getActivityType() == RoomActivity.valueOf(activityName.toUpperCase())) {

                        for (Guest guest : reservation.getGuests()) {
                            String result = activityName.toUpperCase() + " -> Room " + reservation.getRoomNumber() + " -> " + guest;

                            System.out.println(result);
                            state.getActivityManaging().addResult(result);
                        }
                    }
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
