package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;

public class Room {
    private final int roomNumber;
    private final int numberOfBeds;
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

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

}
