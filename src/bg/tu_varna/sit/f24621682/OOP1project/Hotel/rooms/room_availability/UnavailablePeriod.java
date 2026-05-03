package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.Date;

public class UnavailablePeriod extends PeriodBooking {
    public UnavailablePeriod(int roomNumber, Date startDate, Date endDate, String note) {
        super(roomNumber, startDate, endDate, note);
    }
}
