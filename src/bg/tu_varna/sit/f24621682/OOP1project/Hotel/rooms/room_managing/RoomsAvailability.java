package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;

import java.util.*;

// клас, който съдържа списък с ВСИЧКИ СВОБОДНИ стаи
// class, containing list with ALL FREE rooms
public class RoomsAvailability {
    private List<Room> freeRooms;

    public RoomsAvailability() {
        freeRooms = new ArrayList<>();
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    // добавяне в списъка
    // adding in the list
    public void addFreeRoom(Room room) {
        if (!freeRooms.contains(room)) {
            room.setRoomStatus(RoomStatus.AVAILABLE);
            freeRooms.add(room);
        }
    }

    // изтриване от списъка ако стаята се заеме
    // removing from the list if the room gets occupied / unavailable
    public void removeFreeRoom(Room room) {
        freeRooms.remove(room);
        room.setRoomStatus(RoomStatus.OCCUPIED);
    }

    // изчистване на целия списък
    // clearing the whole list
    public void clearFreeRooms() {
        freeRooms.clear();
    }

    // проверка на стая дали е свободна на дадена дата
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