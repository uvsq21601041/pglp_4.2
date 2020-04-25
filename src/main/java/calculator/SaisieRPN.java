package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * store the user input
 */
public class SaisieRPN {
    private static List<String> userInputList = new ArrayList<>();

    private static List<String> rpnResultList = new ArrayList<>();

    /**
     * add the user input
     * @param userInput
     * @param rpnResult
     */
    public static void add(String userInput, String rpnResult) {
        userInputList.add(userInput);
        rpnResultList.add(rpnResult);
    }

    /**
     * undo
     */
    public static void undo() {
        if (!userInputList.isEmpty()) {
            userInputList.remove(userInputList.size() - 1);
            rpnResultList.remove(rpnResultList.size() - 1);
        }
    }
}
