package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional.AvailabilityCommand;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional.CheckinCommand;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.MainCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;

import java.util.Scanner;

public class StartMenu {

    ReservationsManaging reservationsManaging = new ReservationsManaging();
    RoomManaging roomManaging = new RoomManaging();
    MainCommands mainCommands = new MainCommands();
    RoomsAvailability roomsAvailability = new RoomsAvailability();
    AvailabilityCommand availabilityCommand = new AvailabilityCommand();
    CheckinCommand checkinCommand = new CheckinCommand();
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

                mainCommands.open(parts[1], roomManaging);
                fileLoaded = true;
            }
            else if (commandName.equals("close")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                mainCommands.close();
                fileLoaded = false;
            }
            else if (commandName.equals("save")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                mainCommands.save();
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

                mainCommands.saveAs(parts[1]);
            }
            else if (commandName.equals("checkin")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }
                checkinCommand.checkIn(scanner, roomManaging, reservationsManaging, mainCommands, roomsAvailability);
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

                availabilityCommand.availability(mainCommands.getReservations(),
                        roomManaging, scanner, roomsAvailability);

            }
            else if (commandName.equals("help")) {
                mainCommands.help();
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