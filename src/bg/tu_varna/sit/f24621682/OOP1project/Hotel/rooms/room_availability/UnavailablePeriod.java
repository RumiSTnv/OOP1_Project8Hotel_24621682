package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.Date;
/**
 * Моделен клас за представяне на технически неактивен период на стая.
 * <p>
 * Наследява базовия {@link PeriodBooking} без допълнителни полета.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class UnavailablePeriod extends PeriodBooking {
    /**
     * Конструира технически блокиран период за определена стая.
     *
     * @param roomNumber номер на блокираната стая
     * @param startDate  начало на техническата дейност
     * @param endDate    край на техническата дейност
     * @param note       причина или бележка (напр. Ремонт)
     */
    public UnavailablePeriod(int roomNumber, Date startDate, Date endDate, String note) {
        super(roomNumber, startDate, endDate, note);
    }
}
