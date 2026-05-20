package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute;
/**
 * Абстрактен базов клас, представляващ системна команда.
 * <p>
 * Всички потребителски и файлови команди в приложението наследяват този клас
 * и имплементират неговия абстрактен метод за изпълнение.
 * </p>
 *
 * @author Румяна Танева
 * @version 1.0
 */
public abstract class Command {
    private String commandName;
    private String description;
    /**
     * Инициализира основните метаданни за всяка команда.
     *
     * @param commandName името на командата, чрез което се извиква (напр. "save")
     * @param description кратко упътване за синтаксиса и ролята на командата
     */
    public Command(String commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }
    public String getCommandName() {
        return commandName;
    }

    public String getDescription() {
        return description;
    }
    /**
     * Генерира стандартно системно съобщение за успешно приключила операция.
     *
     * @return форматиран низ за потвърждение
     */
    public String successfulExecutionMessage(){
        return "Successfully executed " + commandName;
    }
    /**
     * Абстрактен метод, съдържащ конкретната бизнес логика на командата.
     *
     * @param input входящият текстов низ с аргументи
     */
    public abstract void execute(String input);

}
