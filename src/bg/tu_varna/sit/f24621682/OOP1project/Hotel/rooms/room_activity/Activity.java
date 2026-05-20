package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomActivity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.Date;
/**
 * Моделен клас, описващ допълнително мероприятие или събитие, прикачено към стая.
 * <p>
 * Капсулира вида на събитието (от тип {@link RoomActivity}) и неговите времеви граници.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class Activity {
    private RoomActivity eventType;
    private Date from;
    private Date to;
    /**
     * Конструира нова активност за стая.
     *
     * @param eventType видът на активността (напр. SPA, DINNER)
     * @param from      начален час/дата
     * @param to        краен час/дата
     */
    public Activity(RoomActivity eventType,  Date from, Date to) {
        this.eventType = eventType;
        this.from = from;
        this.to = to;
    }
    /**
     * Извлича типа на мероприятието.
     *
     * @return изброима стойност от {@link RoomActivity}
     */
    public RoomActivity getActivityType() {
        return eventType;
    }
    /**
     * Връща началния момент на активността.
     *
     * @return обект от тип {@link Date}
     */
    public Date getFrom() {
        return from;
    }
    /**
     * Връща крайния момент на активността.
     *
     * @return обект от тип {@link Date}
     */
    public Date getTo() {
        return to;
    }

    @Override
    public String toString() {
        return eventType + " ";
    }
}
