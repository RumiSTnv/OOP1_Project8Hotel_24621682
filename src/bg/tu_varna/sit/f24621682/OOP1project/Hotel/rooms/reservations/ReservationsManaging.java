package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.util.*;

/**
 * Мениджърски клас за администриране на колекцията от резервации.
 * <p>
 * Съдържа основните алгоритми за проверка на хронологично застъпване на периоди
 * и недопускане на дублиране при настаняване (Overbooking).
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class ReservationsManaging {
    private List<Reservation> reservations = new ArrayList<>();
    /**
     * Извлича всички активни резервации в системата.
     *
     * @return списък от всички обекти {@link Reservation}
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
    /**
     * Добавя нова валидирана резервация в регистъра.
     *
     * @param reservation обектът, който се добавя
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    /**
     * Премахва прекратена резервация (при checkout).
     *
     * @param reservation обектът за изтриване
     */
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }
    /**
     * Изчиства изцяло списъка с резервации от паметта.
     */
    public void clearReservations() {
        reservations.clear();
    }
    /**
     * Критичен бизнес алгоритъм за проверка дали стаята е заета в даден интервал.
     * <p>
     * Сравнява математически границите на новия интервал спрямо съществуващите резервации.
     * </p>
     *
     * @param room         стаята, която се проверява
     * @param startDate    начало на търсения период
     * @param endDate      край на търсения период
     * @param reservations глобалният списък с резервации за сравнение
     * @return {@code true} ако стаята се застъпва с текуща резервация; {@code false} ако е свободна
     */
    public boolean isRoomOccupied(Room room, Date startDate, Date endDate, List<Reservation> reservations) {
        for (Reservation r : reservations) {
            if (r.getRoomNumber() == room.getRoomNumber()) {
                boolean overlaps = !(endDate.before(r.getStartDate()) || startDate.after(r.getEndDate()));
                if (overlaps) {
                    return true;
                }
            }
        }
        return false;
    }
}