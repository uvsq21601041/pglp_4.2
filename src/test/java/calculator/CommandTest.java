package calculator;

import org.junit.Assert;
import org.junit.Test;

public class CommandTest {
    @Test
    public void testQuit(){
        ICommand command = new QuitCommand();
        String res = command.handle("line");
        Assert.assertEquals("Good-Bye",res);
    }

    @Test
    public void testUndo(){
        ICommand command = new UndoCommand();
        String res = command.handle("line");
        Assert.assertEquals("Undo success",res);
    }

    @Test
    public void testMoteurRPN(){
        ICommand command = new MoteurRPN();
        String res =  command.handle("2*(3+4)");
        Assert.assertEquals("2 3 4 + * ",res);
        res =  command.handle("3+5");
        Assert.assertEquals("3 5 + ",res);
    }
}
