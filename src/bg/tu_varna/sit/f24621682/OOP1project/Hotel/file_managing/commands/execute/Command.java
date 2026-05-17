package bg.tu_varna.sit.f24621682.OOP1project.Hotel.file_managing.commands.execute;

public abstract class Command {
    private String commandName;
    private String description;

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

    public String successfulExecutionMessage(){
        return "Successfully executed " + commandName;
    }

    public abstract void execute(String input);

}
