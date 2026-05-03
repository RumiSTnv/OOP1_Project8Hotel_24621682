package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability;

import java.util.ArrayList;
import java.util.List;

public class RoomsUnavailability {
    private List<UnavailablePeriod> unavailableRooms;

    public RoomsUnavailability() {
        unavailableRooms = new ArrayList<>();
    }

    public List<UnavailablePeriod> getUnavailableRooms() {
        return unavailableRooms;
    }

    public void addUnavailableRooms(UnavailablePeriod unavailablePeriod) {
        unavailableRooms.add(unavailablePeriod);
    }

    public void clearUnavailableRooms() {
        unavailableRooms.clear();
    }
}
