package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import java.util.ArrayList;
import java.util.List;
// клас, който съдържа списък с ВСИЧКИ стаи
public class RoomManaging {
    private List<Room> allRooms;

    public RoomManaging() {
        allRooms = new ArrayList<>();

        allRooms.add(new Room(209, 2));
        allRooms.add(new Room(112, 3));
        allRooms.add(new Room(114, 3));
        allRooms.add(new Room(208, 4));
        allRooms.add(new Room(210, 4));
        allRooms.add(new Room(212, 4));
        allRooms.add(new Room(108, 5));
        allRooms.add(new Room(216, 4));
        allRooms.add(new Room(217, 2));
        allRooms.add(new Room(115, 2));
    }

    public List<Room> getAllRooms() {
        return allRooms;
    }

    // търси стая по номер
    public Room findRoomsByRoomNumber(int roomNumber) {
        for (Room room : allRooms) {
            if(room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

}
