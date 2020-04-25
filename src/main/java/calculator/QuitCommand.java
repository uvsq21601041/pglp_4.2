package calculator;

public class QuitCommand implements ICommand {
    @Override public String handle(String line) {
        return "Good-Bye";
    }
}
