package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity;

import java.util.ArrayList;
import java.util.List;
/**
 * Специализиран буферен мениджър за генериране и структуриране
 * на графици и програми за стаите по дни.
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class RoomProgramManaging {
    private List<String> program;

    public RoomProgramManaging() {
        this.program = new ArrayList<>();
    }
    /**
     * Добавя нов ред или събитие към текущо генерираната програма.
     *
     * @param result описание на събитието
     */
    public void addToProgram(String result) {
        program.add(result);
    }
    /**
     * Изтрива съдържанието на текущата текстова програма.
     */
    public void clear() {
        program.clear();
    }
    /**
     * Връща форматираната програма за стаята.
     *
     * @return списък от текстови редове
     */
    public List<String> getProgram() {
        return program;
    }
}
