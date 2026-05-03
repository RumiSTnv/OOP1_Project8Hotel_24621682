package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation extends PeriodBooking {
    private List<Guest> guests;
    private int guestNumber;

    public Reservation(int roomNumber, Date startDate, Date endDate, String note, int guestNumber) {
        super(roomNumber, startDate, endDate, note);
        this.guests =  new ArrayList<>();
        this.guestNumber = guestNumber;
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
