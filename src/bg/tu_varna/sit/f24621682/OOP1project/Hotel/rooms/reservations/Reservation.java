package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.person.Guest;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity.Activity;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.PeriodBooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Моделен клас за хотелска резервация, разширяващ {@link PeriodBooking}.
 * <p>
 * Поддържа динамични списъци от регистрирани гости (настанявания)
 * и прилежащи допълнителни мероприятия за стаята в рамките на престоя.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class Reservation extends PeriodBooking {
    private List<Guest> guests;
    private List<Activity> activities;
    private int guestNumber;
    /**
     * Инициализира нова хотелска резервация с базови параметри.
     *
     * @param roomNumber   номер на стаята
     * @param startDate    начална дата на престоя
     * @param endDate      крайна дата на престоя
     * @param note         бележка към резервацията
     * @param guestNumber  брой планирани гости
     */
    public Reservation(int roomNumber, Date startDate, Date endDate, String note, int guestNumber) {
        super(roomNumber, startDate, endDate, note);
        this.guests =  new ArrayList<>();
        this.guestNumber = guestNumber;
        this.activities = new ArrayList<>();
    }
    /**
     * Връща списъка с настанените гости.
     *
     * @return списък от обекти {@link Guest}
     */
    public List<Guest> getGuests() {
        return guests;
    }
    /**
     * Сериализира имената на всички гости в общ текстов низ, разделен с интервали.
     *
     * @return форматиран низ с гостите, използван при запис във файл
     */
    public String guestsToString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < guests.size(); i++) {
            String name = guests.get(i).getGuestName()
                    .replace("\"", "");
            sb.append("\"")
                    .append(name)
                    .append("\"");

            if (i < guests.size() - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
    /**
     * Сериализира типовете на планираните активности в общ текстов низ.
     *
     * @return форматиран низ с дейностите за текстовия файл
     */
    public String activitiesToString(){
        String result="";
        for(Activity activity:activities){
            result+=activity.getActivityType()+" ";
        }
        return result;
    }
    /**
     * Връща заложения брой гости за резервацията.
     *
     * @return числов капацитет на наемане
     */
    public int getGuestNumber() {
        return guestNumber;
    }
    /**
     * Добавя нов гост към настоящата резервация.
     *
     * @param guest обектът на новия гост
     */
    public void addGuest(Guest guest) {
        guests.add(guest);
    }
    /**
     * Добавя нова техническа или развлекателна активност към престоя.
     *
     * @param activity обект на дейността
     */
    public void addActivity(Activity activity){
        activities.add(activity);
    }
    /**
     * Връща пълния график с активности за резервацията.
     *
     * @return списък от обекти {@link Activity}
     */
    public List<Activity> getActivities(){
        return activities;
    }
}
