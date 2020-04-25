package calculator;

public class UndoCommand implements ICommand {
    @Override public String handle(String line) {
        SaisieRPN.undo();
        return  "Undo success";
    }
}
