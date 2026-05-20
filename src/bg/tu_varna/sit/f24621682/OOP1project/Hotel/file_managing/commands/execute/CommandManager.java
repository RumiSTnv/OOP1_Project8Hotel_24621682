package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.additional.*;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main.*;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.InvalidDataException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Мениджър на командния интерфейс (Command Line Interface).
 * <p>
 * Поддържа непрекъснатия жизнен цикъл на програмата (REPL цикъл), съхранява
 * регистрираните команди в {@code HashMap} и пренасочва потребителския вход
 * към съответния софтуерен модул.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class CommandManager {
    private Map<String, Command> commands;
    private HotelState state = new HotelState();
    private boolean fileLoaded = false;
    /**
     * Инициализира мениджъра и регистрира всички поддържани от системата команди
     * в асоциативния масив, свързвайки ги с общото състояние {@code HotelState}.
     */
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
    /**
     * Стартира безкрайния цикъл за четене на данни от конзолата.
     * <p>
     * Следи за глобалните команди "exit" и "help" и улавя контролирано
     * runtime изключенията на програмата.
     * </p>
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
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
    /**
     * Извършва първоначална обработка и парсване на въведения текст.
     *
     * @param input чистият команден ред, прочетен от конзолата
     * прави проверка, с която се забранява работата с другите команди (освен help и exit) ако не е отворен файла
     * @throws InvalidDataException ако файла не е отворен
     * @throws InvalidDataException ако първата дума не съвпада с нито една регистрирана команда
     */
    public void process(String input) {
        input = input.trim();

        if (input.isEmpty()) {
            return;
        }

        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0];
        Command command = commands.get(commandName);

        if (!fileLoaded && !commandName.equals("open") &&
                !commandName.equals("help") && !commandName.equals("exit")) {
            throw new InvalidDataException("You must open a file first using 'open <filename>'.");
        }

        if (command != null) {
            command.execute(input);

            if (commandName.equals("open")) {
                fileLoaded = true;
            }

            if (commandName.equals("close")) {
                fileLoaded = false;
            }
        } else {
            throw new InvalidDataException("Unknown command: " + commandName);
        }
    }
    /**
     * Итерира през всички регистрирани команди и отпечатва на екрана
     * техните имена и инструкции за употреба (помощно меню).
     */
    public void printHelp() {
        for (Command command : commands.values()) {
            System.out.println(command.getCommandName() + " - " + command.getDescription());
        }
    }
}
