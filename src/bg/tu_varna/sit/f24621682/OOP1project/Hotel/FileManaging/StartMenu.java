package bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.FileManaging.FileClasses.ReservationFile;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Classes.Room;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.Rooms.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    private ReservationFile reservationFile = new ReservationFile();
    private List<Room> rooms = new ArrayList<>();
    private boolean fileLoaded = false;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String command = scanner.next();

            if (command.equals("open")) {
                String path = scanner.next();
                try {
                    reservationFile.open(path);
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
                fileLoaded = false;

            } else if (command.equals("save")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.save();

            } else if (command.equals("saveas")) {
                String newPath = scanner.next();
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                reservationFile.saveAs(newPath);

            } else if (command.equals("checkin")) {
                if (!fileLoaded) {
                    System.out.println("File not loaded");
                }
                Room room = new Room(209, 4);
                reservationFile.checkIn(scanner,room);

            } else if(command.equals("help")) {
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
