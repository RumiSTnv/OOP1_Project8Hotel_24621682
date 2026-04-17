package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.FileCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;

import java.util.Scanner;

public class StartMenu {

    ReservationsManaging reservationFile = new ReservationsManaging();
    RoomManaging roomManaging = new RoomManaging();
    FileCommands fileCommands = new FileCommands();
    RoomsAvailability roomsAvailability = new RoomsAvailability();
    private boolean fileLoaded = false;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String commandName = parts[0];

            if (commandName.equals("open")) {

                if (parts.length < 2) {
                    System.out.println("Usage: open <file>");
                    continue;
                }

                fileCommands.open(parts[1], roomManaging);
                fileLoaded = true;
            }
            else if (commandName.equals("close")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                fileCommands.close();
                fileLoaded = false;
            }
            else if (commandName.equals("save")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                fileCommands.save();
            }
            else if (commandName.equals("saveas")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                if (parts.length < 2) {
                    System.out.println("Usage: saveas <file>");
                    continue;
                }

                fileCommands.saveAs(parts[1]);
            }
            else if (commandName.equals("checkin")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }
                fileCommands.getReservations().checkIn(scanner, roomManaging, fileCommands);
            }
            else if(commandName.equals("checkout")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

               /* Scanner scanner = new Scanner(System.in);
                System.out.print("Enter room number to checkout: ");
                int roomNumber;

                try {
                    roomNumber = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid room number!");
                    continue;
                }

                fileCommands.getReservations().removeReservation(roomNumber, fileCommands, roomManaging);
            */}
            else if (commandName.equals("availability")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                fileCommands.getFreeRooms().availability(fileCommands.getReservations(),
                        roomManaging, scanner);
            }
            else if (commandName.equals("help")) {
                fileCommands.help();
            }
            else if (commandName.equals("exit")) {
                System.out.println("Exiting...");
                break;
            }
            else {
                System.out.println("Unknown command");
            }
        }
    }
}