package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.ActivityManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.RoomProgramManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsUnavailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;
/**
 * Централен клас за управление и координация на споделеното състояние на системата.
 * <p>
 * Капсулира метаданните за отворения файл и държи директни референции
 * към четирите основни логически мениджъра в приложението.
 * </p>
 */
public class HotelState {
    private String filePath;
    private boolean isOpen;

    private RoomManaging roomManaging ;
    private ReservationsManaging reservations;
    private RoomsAvailability freeRooms;
    private RoomsUnavailability unavailableRooms;
    private ActivityManaging activityManaging;
    private RoomProgramManaging roomProgramManaging;

    public HotelState() {
        roomManaging = new RoomManaging();
        reservations = new ReservationsManaging();
        freeRooms = new RoomsAvailability();
        unavailableRooms = new RoomsUnavailability();
        activityManaging = new ActivityManaging();
        roomProgramManaging = new RoomProgramManaging();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Проверява дали има активна отворена сесия с база данни.
     *
     * @return {@code true} ако има зареден файл; {@code false} в противен случай
     */
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public RoomManaging getRoomManaging() {
        return roomManaging;
    }

    public ReservationsManaging getReservations() {
        return reservations;
    }

    public void setReservations(ReservationsManaging reservations) {
        this.reservations = reservations;
    }

    public RoomsAvailability getFreeRooms() {
        return freeRooms;
    }

    public RoomsUnavailability getUnavailableRooms() {
        return unavailableRooms;
    }

    public ActivityManaging getActivityManaging() {
        return activityManaging;
    }

    public RoomProgramManaging getRoomProgramManaging() {
        return roomProgramManaging;
    }
}
