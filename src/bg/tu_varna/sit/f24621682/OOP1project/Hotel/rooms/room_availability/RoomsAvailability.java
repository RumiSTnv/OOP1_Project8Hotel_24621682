package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.util.*;

/**
 * Логически мениджър за администриране на текущо свободния капацитет на хотела.
 * <p>
 * Следи за добавянето на налични стаи и проверява статуса им за конкретен ден.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class RoomsAvailability {
    private List<Room> freeRooms;

    public RoomsAvailability() {
        freeRooms = new ArrayList<>();
    }
    /**
     * Извлича наличните свободни стаи към момента.
     *
     * @return списък със свободни стаи
     */
    public List<Room> getFreeRooms() {
        return freeRooms;
    }
    /**
     * Добавя стая към свободния фонд и установява статуса ѝ като {@code AVAILABLE}.
     *
     * @param room стаята, която се освобождава
     */
    public void addFreeRoom(Room room) {
        if (!freeRooms.contains(room)) {
            room.setRoomStatus(RoomStatus.AVAILABLE);
            freeRooms.add(room);
        }
    }
    /**
     * Премахва стая от свободния списък и превключва статуса ѝ на {@code OCCUPIED}.
     *
     * @param room стаята, която се заема или блокира
     */
    public void removeFreeRoom(Room room) {
        freeRooms.remove(room);
        room.setRoomStatus(RoomStatus.OCCUPIED);
    }
    /**
     * Изчиства целия списък със свободни стаи.
     */
    public void clearFreeRooms() {
        freeRooms.clear();
    }
    /**
     * Проверява дали дадена стая е свободна в рамките на точно определен календарен ден.
     *
     * @param room         стаята за проверка
     * @param checkDate    датата, която ни интересува
     * @param reservations списък с текущи резервации за съпоставка
     * @return {@code true} ако стаята е свободна за този ден; {@code false} в противен случай
     */
    public boolean isRoomFreeByDay(Room room, Date checkDate, List<Reservation> reservations) {
        for (Reservation res : reservations) {
            if (res.getRoomNumber() == room.getRoomNumber()) {
                boolean overlaps = !checkDate.before(res.getStartDate()) ||
                        !checkDate.after(res.getEndDate());
                if (overlaps) {
                    return false;
                }
            }
        }
        return true;
    }
}