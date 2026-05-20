package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability;

import java.util.ArrayList;
import java.util.List;
/**
 * Мениджър за поддръжка и следене на всички блокирани стаи в хотела.
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class RoomsUnavailability {
    private List<UnavailablePeriod> unavailableRooms;

    public RoomsUnavailability() {
        unavailableRooms = new ArrayList<>();
    }
    /**
     * Връща списъка с всички регистрирани периоди на недостъпност.
     *
     * @return колекция от обекти {@link UnavailablePeriod}
     */
    public List<UnavailablePeriod> getUnavailableRooms() {
        return unavailableRooms;
    }
    /**
     * Регистрира нова блокирана стая в списъка на техническо обслужване.
     *
     * @param unavailablePeriod обектът с техническия период
     */
    public void addUnavailableRooms(UnavailablePeriod unavailablePeriod) {
        unavailableRooms.add(unavailablePeriod);
    }
    /**
     * Изпразва списъка с технически блокирани стаи.
     */
    public void clearUnavailableRooms() {
        unavailableRooms.clear();
    }
}
