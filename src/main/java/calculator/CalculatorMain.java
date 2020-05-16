package calculator;

import java.util.Scanner;

/**
 * The main of theprogram
 */
public class CalculatorMain {

    private Scanner in = new Scanner(System.in);

    /**
     * start the program
     */
    public void start() {
        System.out.println("---------- Welcome to use RPN calculator -------------");
        while (true) {
            System.out.println("Entrez arithmetic formula (u for UNDO, q for Quit): ");
            String line = in.nextLine();
            if ("u".equalsIgnoreCase(line)) {
                String res = new UndoCommand().handle(line);
                System.out.println(res);
            } else if ("q".equalsIgnoreCase(line)) {
                String res = new QuitCommand().handle(line);
                System.out.println(res);
                break;
            } else {
                try {
                    String res = new MoteurRPN().handle(line);
                    System.out.println(res);
                    SaisieRPN.add(line, res);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error input, please retry");
                }
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorMain().start();
    }
}
