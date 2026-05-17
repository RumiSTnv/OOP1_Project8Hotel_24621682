package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation extends PeriodBooking {
    private List<Guest> guests;
    private List<Activity> activities;
    private int guestNumber;

    public Reservation(int roomNumber, Date startDate, Date endDate, String note, int guestNumber) {
        super(roomNumber, startDate, endDate, note);
        this.guests =  new ArrayList<>();
        this.guestNumber = guestNumber;
        this.activities = new ArrayList<>();
    }

    public List<Guest> getGuests() {
        return guests;
    }
    public String guestsToString(){
        String result="";
        for(Guest g:guests){
            result+=g.getGuestName()+" ";
        }
        return result;
    }

    public String activitiesToString(){
        String result="";
        for(Activity activity:activities){
            result+=activity.getActivityType()+" ";
        }
        return result;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public List<Activity> getActivities(){
        return activities;
    }
}
