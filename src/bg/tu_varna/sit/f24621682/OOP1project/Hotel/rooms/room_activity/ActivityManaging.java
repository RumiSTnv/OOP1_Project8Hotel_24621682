package bg.tu_varna.sit.f24621682.OOP1project.Hotel.rooms.room_activity;

import java.util.ArrayList;
import java.util.List;
/**
 * Спомагателен мениджър за текстово акумулиране и филтриране на резултати
 * свързани с активностите.
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class ActivityManaging {
    private List<String> activities;

    public ActivityManaging() {
        activities = new ArrayList<>();
    }
    /**
     * Добавя нов низов резултат към списъка.
     *
     * @param result текстовото съобщение/резултат
     */
    public void addResult(String result) {
        activities.add(result);
    }
    /**
     * Изчиства натрупаните текстови резултати.
     */
    public void clearResults() {
        activities.clear();
    }
    /**
     * Извлича списъка с натрупаните текстови данни.
     *
     * @return списък от низове
     */
    public List<String> getResults() {
        return activities;
    }
}
