package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.Date;

public class Activity {
    private RoomActivity eventType;
    private Date from;
    private Date to;

    public Activity(RoomActivity eventType,  Date from, Date to) {
        this.eventType = eventType;
        this.from = from;
        this.to = to;
    }

    public RoomActivity getActivityType() {
        return eventType;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    @Override
    public String toString() {
        return eventType + " ";
    }
}
