package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import java.util.Date;

public class PeriodBooking {
    private int roomNumber;
    private Date startDate;
    private Date endDate;
    private String note;

    public PeriodBooking(int roomNumber, Date startDate, Date endDate, String note) {
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
