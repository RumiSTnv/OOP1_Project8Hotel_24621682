package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;

import java.text.SimpleDateFormat;

/**
 * Команда за извеждане на техническия и развлекателен график на конкретна стая.
 * <p>
 * Показва всички допълнителни активности, регистрирани към текущата резервация на стаята.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class RoomProgramCommand extends Command {
    private HotelState state;
    /**
     * Конструира команда за извеждане на програмата на стая.
     *
     * @param state текущото споделено състояние на системата
     */
    public RoomProgramCommand(HotelState state) {
        super("program", "program <room> - shows the room's activity program");
        this.state = state;
    }
    /**
     * Намира текущата резервация на стаята и отпечатва списъка с нейните събития.
     *
     * @param input потребителският вход съдържащ търсения номер на стая
     * @throws InvalidDataException ако подаденият номер на стая е невалиден
     */
    @Override
    public void execute(String input) {
        try {
            String[] parts = input.split("\\s+");
            if (parts.length < 2) {
                throw new InvalidDataException("Usage: program <room>");
            }

            int roomNumber = Integer.parseInt(parts[1]);
            state.getRoomProgramManaging().clear();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (Reservation reservation : state.getReservations().getReservations()) {
                if (reservation.getRoomNumber() == roomNumber) {
                    String header = "Program for room " + roomNumber;

                    System.out.println(header);
                    for (Activity activity : reservation.getActivities()) {
                        String start = sdf.format(activity.getFrom());
                        String end = sdf.format(activity.getTo());
                        String result = activity.getActivityType() + " - " + start + " - " + end;

                        System.out.println(successfulExecutionMessage() + ": " + result);

                        state.getRoomProgramManaging().addToProgram(result);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
