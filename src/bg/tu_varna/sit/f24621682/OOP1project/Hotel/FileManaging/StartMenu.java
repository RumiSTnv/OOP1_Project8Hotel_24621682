package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.FreeRoomsFileCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.ReservationFileCommands;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.RoomManaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    private ReservationFileCommands reservationFile = new ReservationFileCommands();
    private FreeRoomsFileCommands freeRoomsFile = new FreeRoomsFileCommands();
    private RoomManaging roomManaging = new RoomManaging();
    private List<Room> rooms = new ArrayList<>();
    private boolean fileLoaded = false;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String command = scanner.next();

            if (command.equals("open")) {
                String reservationFilePath = scanner.next();
                scanner.nextLine();
                String freeRoomsFlePath = scanner.next();

                try {
                    reservationFile.open(reservationFilePath);
                    freeRoomsFile.open(freeRoomsFlePath);
                    fileLoaded = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (command.equals("close")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }
                reservationFile.close();
                freeRoomsFile.close();
                fileLoaded = false;

            } else if (command.equals("save")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.save();
                freeRoomsFile.save();

            } else if (command.equals("saveas")) {
                String newReservationFilePath = scanner.next();
                String newFreeRoomsFilePath = scanner.next();

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.saveAs(newReservationFilePath);
                freeRoomsFile.saveAs(newFreeRoomsFilePath);

            } else if (command.equals("checkin")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.checkIn(scanner, roomManaging, freeRoomsFile);

            } else if (command.equals("availability")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }

                scanner.nextLine();
                freeRoomsFile.availability(reservationFile, scanner);
            }

            else if(command.equals("help")) {
                printHelp();
            }

            else if (command.equals("exit")) {
                break;

            } else {

                System.out.println("Unknown command");
            }
        }
    }

    private void printHelp() {

        System.out.println("open <file> - open file");
        System.out.println("save - save file");
        System.out.println("saveas <file> - save file with new path");
        System.out.println("close - close file");
        System.out.println("help - show commands");
        System.out.println("exit - exit program");
    }
}
