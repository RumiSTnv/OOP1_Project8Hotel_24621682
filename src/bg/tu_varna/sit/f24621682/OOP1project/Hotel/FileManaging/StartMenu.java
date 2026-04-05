package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.ReservationFileCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;

import java.util.Scanner;

public class StartMenu {

    private ReservationFileCommands reservationFile = new ReservationFileCommands();
    private RoomManaging roomManaging = new RoomManaging();
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

                reservationFile.open(parts[1]);
                fileLoaded = true;
            }
            else if (commandName.equals("close")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                reservationFile.close();
                fileLoaded = false;
            }
            else if (commandName.equals("save")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                reservationFile.save();
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

                reservationFile.saveAs(parts[1]);
            }
            else if (commandName.equals("checkin")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                System.out.println("Enter: <roomNumber> <startDate> <endDate> <note>");
                reservationFile.checkIn(scanner, roomManaging);
            }
            else if (commandName.equals("availability")) {

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }

                reservationFile.availability(roomManaging, scanner);
            }
            else if (commandName.equals("help")) {
                printHelp();
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

    private void printHelp() {
        System.out.println("open <file> - open reservations file");
        System.out.println("save - save file");
        System.out.println("saveas <file> - save file with new path");
        System.out.println("close - close file");
        System.out.println("checkin - add reservation");
        System.out.println("availability - show free rooms");
        System.out.println("help - show commands");
        System.out.println("exit - exit program");
    }
}