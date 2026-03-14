package bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Person.Guest;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String roomNumber;
    private String startDate;
    private String endDate;
    private String note;
    private List<Guest> guests;
    private int guestNumber;

    public Reservation(String roomNumber, String startDate, String endDate, String note) {
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
        this.guests =  new ArrayList<>();
        this.guestNumber = guestNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {}

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNote() {
        return note;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }
}
