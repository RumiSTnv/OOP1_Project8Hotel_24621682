package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsUnavailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;

public class HotelState {
    private String filePath;
    private boolean isOpen;

    private RoomManaging roomManaging = new RoomManaging();
    private ReservationsManaging reservations = new ReservationsManaging();
    private RoomsAvailability freeRooms = new RoomsAvailability();
    private RoomsUnavailability unavailableRooms = new RoomsUnavailability();



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public RoomManaging getRoomManaging() {
        return roomManaging;
    }

    public void setRoomManaging(RoomManaging roomManaging) {
        this.roomManaging = roomManaging;
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

    public void setFreeRooms(RoomsAvailability freeRooms) {
        this.freeRooms = freeRooms;
    }

    public RoomsUnavailability getUnavailableRooms() {
        return unavailableRooms;
    }

    public void setUnavailableRooms(RoomsUnavailability unavailableRooms) {
        this.unavailableRooms = unavailableRooms;
    }
}
