package bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Person.Guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private int roomNumber;
    private Date startDate;
    private Date endDate;
    private String note;
    private List<Guest> guests;
    private int guestNumber;

    public Reservation(int roomNumber, Date startDate, Date endDate, String note, int guestNumber) {
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
        this.guests =  new ArrayList<>();
        this.guestNumber = guestNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {}

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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
