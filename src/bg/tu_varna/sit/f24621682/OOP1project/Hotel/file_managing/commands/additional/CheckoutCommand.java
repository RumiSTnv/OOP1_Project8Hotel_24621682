package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.Reservation;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_enum.RoomStatus;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.Room;

public class CheckoutCommand extends Command {

    private final HotelState state;

    public CheckoutCommand(HotelState state) {
        super("checkout", "checkout <room>");
        this.state = state;
    }

    @Override
    public void execute(String input) {

        try {
            String[] parts = input.split(" ");

            int roomNumber = Integer.parseInt(parts[1]);

            Reservation target = null;

            for (Reservation r : state.getReservations().getReservations()) {
                if (r.getRoomNumber() == roomNumber) {
                    target = r;
                    break;
                }
            }

            if (target == null) {
                System.out.println("Reservation not found.");
                return;
            }

            state.getReservations().removeReservation(target);

            Room room = state.getRoomManaging()
                    .findRoomsByRoomNumber(roomNumber);

            if (room != null) {
                room.setRoomStatus(RoomStatus.AVAILABLE);
                state.getFreeRooms().addFreeRoom(room);
            }

            System.out.println("Checkout successful.");

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}