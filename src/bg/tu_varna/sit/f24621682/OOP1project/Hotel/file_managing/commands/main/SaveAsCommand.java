package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;

import java.io.File;

public class SaveAsCommand extends Command {
    private final HotelState state;

    public SaveAsCommand(HotelState state) {
        super("saveas", "Saves file as new file");
        this.state = state;
    }

    @Override
    public void execute(String input) {
        System.out.println("SAVE EXECUTED");
        System.out.println("FILE: " + state.getFilePath());
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
                    throw new RuntimeException("Failed to create file: " + parts[1]);
                }
            }

            new SaveCommand(state).execute("save");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
