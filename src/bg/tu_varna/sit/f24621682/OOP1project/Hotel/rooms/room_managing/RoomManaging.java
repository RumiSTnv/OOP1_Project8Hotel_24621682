package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import java.util.ArrayList;
import java.util.List;
/**
 * Мениджър на хотелския сграден фонд.
 * <p>
 * Съдържа пълния списък на стаите и извършва търсене по техния цифров номер.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class RoomManaging {
    private List<Room> allRooms = new ArrayList<>();
    /**
     * Инициализира мениджъра и твърдо кодира (хардкодва) десетте базови стаи на хотела.
     */
    public RoomManaging() {addData();}

    public void addData(){
        allRooms.add(new Room(209, 2));
        allRooms.add(new Room(112, 3));
        allRooms.add(new Room(114, 3));
        allRooms.add(new Room(208, 4));
        allRooms.add(new Room(210, 4));
        allRooms.add(new Room(212, 4));
        allRooms.add(new Room(108, 5));
        allRooms.add(new Room(216, 4));
        allRooms.add(new Room(217, 2));
        allRooms.add(new Room(115, 2));
    }
    /**
     * Връща пълния списък на всички съществуващи стаи в хотела.
     *
     * @return колекция от обекти {@link Room}
     */
    public List<Room> getAllRooms() {
        return allRooms;
    }
    /**
     * Извършва линейно търсене на стая по нейния номер.
     *
     * @param roomNumber търсеният номер
     * @return намерената {@link Room} инстанция или {@code null} при липса на такава стая
     */
    public Room findRoomsByRoomNumber(int roomNumber) {
        for (Room room : allRooms) {
            if(room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
