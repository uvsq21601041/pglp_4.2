package calculator;

import org.junit.Test;

public class SaisieRPNTest {
    @Test
    public void testSaisie(){
        SaisieRPN.add("2*(3+4)","2 3 4 + * ");
        SaisieRPN.undo();
    }
}
