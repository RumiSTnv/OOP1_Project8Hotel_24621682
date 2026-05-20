package bg.tu_varna.sit.f24621682.OOP1project.Hotel;

import bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute.CommandManager;
/**
 * Главен стартов клас на приложението за управление на хотел.
 * <p>
 * Съдържа стандартния за Java входен метод за задвижване на софтуерния цикъл.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public class Main {
    /**
     * Основен статичен метод (Entry point).
     * <p>
     * Инициализира мениджъра на команди и стартира интерактивното конзолно меню.
     * </p>
     *
     * @param args масив от системни аргументи, подадени при стартиране на JVM
     */
    public static void main(String[] args) {
        CommandManager startMenu = new CommandManager();
        startMenu.start();
    }
}