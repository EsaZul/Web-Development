package command;

public class ConsoleCommand implements Command {
        // IMPLEMENT ME!
    private Console console;
    private String toPrint;
    public ConsoleCommand(Console speaker){
        this.console = speaker;
    }
    public void execute(){
        console.print(toPrint);
    }
    public void setToPrint(String toPrint) {
        this.toPrint = toPrint;
    }
}