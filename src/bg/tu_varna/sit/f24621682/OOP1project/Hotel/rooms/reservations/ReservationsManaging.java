package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

import java.util.*;

// клас, съдържащ списък с резервации / резервирани стаи
public class ReservationsManaging {
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    // добавяне на резервация
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // премахване на всички резервации
    public void clearReservations() {
        reservations.clear();
    }

    // проверка за свободна стая в диапазона от дата - до дата
    public boolean isRoomFreeFromDateToDate(Room room, Date startDate, Date endDate, List<Reservation> reservations) {
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