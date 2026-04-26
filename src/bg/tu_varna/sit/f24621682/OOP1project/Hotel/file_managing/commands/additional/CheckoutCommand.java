package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional;

public class CheckoutCommand {

    /*public void checkOut(int roomNumber, FileCommands fileCommands,
                         RoomManaging roomManaging) {
        Iterator<Reservation> it = reservations.iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Reservation reservation = it.next();

            if(reservation.getRoomNumber() == roomNumber) {
                it.remove();
                removed = true;

                Room room = roomManaging.findRoomsByRoomNumber(roomNumber);
                if(room != null) {
                    fileCommands.getFreeRooms().removeFreeRoom(room);
                }
                break;
            }
        }

        if(removed) {
           fileCommands.save();
        }
        else
            System.out.println("Reservation not found!");
    }*/

}
