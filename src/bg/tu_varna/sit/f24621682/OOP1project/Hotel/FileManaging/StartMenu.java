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
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            String commandName = parts[0];

            if (commandName.equals("open")) {
                if (parts.length < 2) {
                    System.out.println("Usage: open <file>");
                    continue;
                }

                String filePath = parts[1];

                try {
                    if (filePath.contains("reservation")) {
                        reservationFile.open(filePath);
                        System.out.println("Reservations file opened");
                    }
                    else if (filePath.contains("free")) {
                        freeRoomsFile.open(filePath, reservationFile, roomManaging);
                        System.out.println("Free rooms file opened");
                    }
                    else {
                        System.out.println("Unknown file type");
                        continue;
                    }

                    fileLoaded = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (commandName.equals("close")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }
                reservationFile.close();
                freeRoomsFile.close();
                fileLoaded = false;

            } else if (commandName.equals("save")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.save();
                freeRoomsFile.save();

            } else if (commandName.equals("saveas")) {
                String newReservationFilePath = scanner.next();
                String newFreeRoomsFilePath = scanner.next();

                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.saveAs(newReservationFilePath);
                freeRoomsFile.saveAs(newFreeRoomsFilePath);

            } else if (commandName.equals("checkin")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                    continue;
                }
                reservationFile.checkIn(scanner, roomManaging, freeRoomsFile);

            } else if (commandName.equals("availability")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }

                scanner.nextLine();
                freeRoomsFile.availability(reservationFile, roomManaging, scanner);
            }

            else if(commandName.equals("help")) {
                printHelp();
            }

            else if (commandName.equals("exit")) {
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
