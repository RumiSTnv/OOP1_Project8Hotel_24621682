package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.exceptions.FileErrorException;

import java.io.File;
/**
 * Команда за експортиране на текущото състояние на хотела под нов файлов път.
 * <p>
 * Създава нов физически файл, ако не съществува, и пренасочва логиката за запис към него.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class SaveAsCommand extends Command {
    private final HotelState state;
    /**
     * Конструира команда за запис като нов файл.
     *
     * @param state текущото състояние на хотела
     */
    public SaveAsCommand(HotelState state) {
        super("saveas", "Saves file as new file");
        this.state = state;
    }
    /**
     * Променя текущия файлов път в състоянието и извиква SaveCommand.
     *
     * @param input новият път/име на файла, въведен от потребителя
     */
    @Override
    public void execute(String input) {
        try {
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                System.out.println("Usage: saveas <file>");
                return;
            }

            state.setFilePath(parts[1]);

            File file = new File(parts[1]);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("New file created: " + parts[1]);
                } else {
                    throw new FileErrorException("Failed to create file: " + parts[1]);
                }
            }

            new SaveCommand(state).execute("save");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
