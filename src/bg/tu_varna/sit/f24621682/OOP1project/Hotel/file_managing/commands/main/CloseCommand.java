package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;

public class CloseCommand extends Command {
    private final HotelState state;

    public CloseCommand(HotelState state) {
        super("close", "Closes file");
        this.state = state;
    }

    @Override
    public void execute(String input) {

        state.setOpen(false);
        state.setFilePath(null);

        System.out.println(successfulExecutionMessage());
    }
}
