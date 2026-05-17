package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional.*;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.*;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.reservations.ReservationsManaging;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsAvailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_availability.RoomsUnavailability;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_managing.RoomManaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
    private Map<String, Command> commands;
    private HotelState state = new HotelState();

    public CommandManager(){
        commands = new HashMap<>();

        OpenCommand openCommand = new OpenCommand(state);
        commands.put(openCommand.getCommandName(), openCommand);

        CloseCommand closeCommand = new CloseCommand(state);
        commands.put(closeCommand.getCommandName(), closeCommand);

        SaveCommand saveCommand = new SaveCommand(state);
        commands.put(saveCommand.getCommandName(), saveCommand);

        SaveAsCommand saveAsCommand = new SaveAsCommand(state);
        commands.put(saveAsCommand.getCommandName(), saveAsCommand);

        CheckinCommand checkinCommand = new CheckinCommand(state);
        commands.put(checkinCommand.getCommandName(), checkinCommand);

        AvailabilityCommand  availabilityCommand = new AvailabilityCommand(state);
        commands.put(availabilityCommand.getCommandName(), availabilityCommand);

        CheckoutCommand checkoutCommand = new CheckoutCommand(state);
        commands.put(checkoutCommand.getCommandName(), checkoutCommand);

        ReportCommand reportCommand = new ReportCommand(state);
        commands.put(reportCommand.getCommandName(), reportCommand);

        FindCommand findCommand = new FindCommand(state);
        commands.put(findCommand.getCommandName(), findCommand);

        FindImportantCommand findImportantCommand = new FindImportantCommand(state, findCommand);
        commands.put(findImportantCommand.getCommandName(), findImportantCommand);

        UnavailabilityCommand unavailabilityCommand = new UnavailabilityCommand(state);
        commands.put(unavailabilityCommand.getCommandName(), unavailabilityCommand);

        RoomProgramCommand roomProgramCommand = new RoomProgramCommand(state);
        commands.put(roomProgramCommand.getCommandName(), roomProgramCommand);

        ActivityCommand activityCommand = new ActivityCommand(state);
        commands.put(activityCommand.getCommandName(), activityCommand);
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("> ");

            String input =
                    scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {

                System.out.println(
                        "Exiting...");

                break;
            }

            if (input.equalsIgnoreCase("help")) {

                printHelp();

                continue;
            }
try {
    process(input);
}
catch (InvalidDataException e) {
    System.out.println(e.getMessage());
}
        }

        scanner.close();
    }

    public void process(String input) {
        input = input.trim();

        if (input.isEmpty()) {
            return;
        }

        //String[] parts = input.split(" ");
        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0];
        Command command = commands.get(commandName);

        if (command != null) {
            command.execute(input);
        } else {
            throw new InvalidDataException("Unknown command: " + commandName);
        }
    }

    public void printHelp() {
        for (Command command : commands.values()) {
            System.out.println(command.getCommandName() + " - " + command.getDescription());
        }
    }
}
