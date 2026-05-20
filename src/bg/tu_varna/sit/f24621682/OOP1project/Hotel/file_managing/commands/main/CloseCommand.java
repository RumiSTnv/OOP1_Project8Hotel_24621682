package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.main;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.Command;
import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.HotelState;
/**
 * Команда за затваряне на текущата сесия и освобождаване на заредения файл.
 * <p>
 * Изчиства логическата връзка с дисковия носител и превежда системата в затворено състояние.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class CloseCommand extends Command {
    private final HotelState state;
    /**
     * Конструира команда за затваряне на файл.
     *
     * @param state състоянието на хотела, чиито указатели ще бъдат занулени
     */
    public CloseCommand(HotelState state) {
        super("close", "Closes file");
        this.state = state;
    }
    /**
     * Прекратява активната сесия.
     *
     * @param input не се изискват допълнителни параметри
     */
    @Override
    public void execute(String input) {

        state.setOpen(false);
        state.setFilePath(null);

        System.out.println(successfulExecutionMessage());
    }
}
