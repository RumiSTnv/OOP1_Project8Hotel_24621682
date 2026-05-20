package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import java.util.Date;
/**
 * Абстрактен базов клас, обобщаващ концепцията за ангажиране на хотелска стая във времето.
 * <p>
 * Служи като родителско ядро за резервациите и за периодите на техническа недостъпност.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public abstract class PeriodBooking {
    private int roomNumber;
    private Date startDate;
    private Date endDate;
    private String note;
    /**
     * Инициализира споделените свойства за времево заемане на ресурс.
     *
     * @param roomNumber номер на съответната стая
     * @param startDate  начало на ангажирания период
     * @param endDate    край на ангажирания период
     * @param note       текстова бележка или описание
     */
    public PeriodBooking(int roomNumber, Date startDate, Date endDate, String note) {
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getNote() {
        return note;
    }

}
