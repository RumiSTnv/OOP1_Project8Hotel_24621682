package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
/**
 * Моделен клас, описващ физическа стая в хотела.
 * <p>
 * Капсулира нейния фиксиран номер, брой легла и текущ софтуерен статус.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class Room {
    private final int roomNumber;
    private final int numberOfBeds;
    private RoomStatus roomStatus;
    /**
     * Създава стая в хотела с константен номер и капацитет.
     *
     * @param roomNumber   уникален номер на стаята
     * @param numberOfBeds общ брой легла (капацитет)
     */
    public Room(int roomNumber, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.roomStatus = RoomStatus.AVAILABLE;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }
    /**
     * Променя текущия статус на стаята (напр. при заемане или освобождаване).
     *
     * @param roomStatus новият статус на стаята
     */
    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

}
