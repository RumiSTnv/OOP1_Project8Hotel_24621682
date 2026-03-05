package Hotel.Rooms;

import Hotel.Person.Guest;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String startDate;
    private String endDate;
    private String note;
    private List<Guest> guests;

    //private Room room;
    //private RoomStatus roomStatus;

    public Reservation(String startDate, String endDate, String note) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
        this.guests =  new ArrayList<>();
    }

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

    public void addGuest(Guest guest) {
        guests.add(guest);
    }
}
