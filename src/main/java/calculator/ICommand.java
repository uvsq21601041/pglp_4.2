package calculator;

/**
 * the interface of command
 */
public interface ICommand {
    /**
     * handle the command
     * @param line the input
     * @return the output
     */
    String handle(String line);
}
