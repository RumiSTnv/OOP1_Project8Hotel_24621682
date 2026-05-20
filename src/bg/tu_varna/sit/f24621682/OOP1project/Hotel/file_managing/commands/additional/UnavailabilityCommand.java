package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.SaveCommand;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.UnavailablePeriod;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Команда за временно блокиране на стая (извеждане от експлоатация).
 * <p>
 * Използва се при нужди от ремонт, реновиране или почистване, като премахва
 * стаята от списъка със свободни помещения за посочения технически период.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class UnavailabilityCommand extends Command {

    private final HotelState state;
    /**
     * Конструира команда за отбелязване на стая като недостъпна.
     *
     * @param state текущото споделено състояние на системата
     */
    public UnavailabilityCommand(HotelState state) {
        super("unavailable", "unavailable <room> <from> <to> <note> - mark room unavailable");
        this.state = state;
    }
    /**
     * Създава период на недостъпност и изважда стаята от активния оборот.
     *
     * @param input входният низ с параметри (номер, дати и забележка/причина)
     */
    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            if (parts.length < 5) {
                throw new InvalidDataException("Usage: unavailable <room> <from> <to> <note>");
            }

            int roomNumber = Integer.parseInt(parts[1]);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date from = format.parse(parts[2]);
            Date to = format.parse(parts[3]);
            String note = parts[4];

            Room room = state.getRoomManaging().findRoomsByRoomNumber(roomNumber);

            if (room == null) return;

            UnavailablePeriod period = new UnavailablePeriod(roomNumber, from, to, note);

            state.getUnavailableRooms().addUnavailableRooms(period);
            state.getFreeRooms().removeFreeRoom(room);
            System.out.println(successfulExecutionMessage() + ". Room marked unavailable.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}