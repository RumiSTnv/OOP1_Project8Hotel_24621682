package Hotel.Rooms.Classes;

import Hotel.Rooms.Enum.RoomStatus;

public class Room {
    private int roomNumber;
    private int numberOfBeds;
    private RoomStatus roomStatus;

    public Room(int roomNumber, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.roomStatus = RoomStatus.AVAILABLE;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }
}
