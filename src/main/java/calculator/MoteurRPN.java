package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoteurRPN implements ICommand {

    private Pattern opParenPattern = Pattern.compile("[-+*/^()]");

    @Override public String handle(String line) {
        List<String> operators = parseOperators(line);
        List<String> postExp = parsePostTokenizer(operators);
        String res = "";
        for (String exp : postExp) {
            res = res + exp + " ";
        }
        return res;
    }

    /**
     * parse to operators
     *
     * @param expression the string of user input
     * @return the list of operators
     */
    public List<String> parseOperators(String expression) {
        List<String> operators = new ArrayList<>();

        Matcher finder = opParenPattern.matcher(expression);
        String opOrParen, between;
        int lastBetweenIndex = 0;
        String lastOpOrParen = "";

        while (finder.find()) {
            opOrParen = finder.group();
            between = expression.substring(lastBetweenIndex, finder.start());
            between = between.trim();
            if (opOrParen.charAt(0) == '-') {
                if (finder.start() == 0 || !lastOpOrParen.equals(")") && between.equals("")) {
                    continue;
                }
            }
            if (between.length() != 0) {
                operators.add(between);
            }
            lastBetweenIndex = finder.end();
            operators.add(opOrParen);
            lastOpOrParen = opOrParen;
        }
        if (lastBetweenIndex < expression.length()) {
            between = expression.substring(lastBetweenIndex, expression.length());
            between = between.trim();
            operators.add(between);
        }
        return operators;
    }

    /**
     * parse to post token
     */
    public List<String> parsePostTokenizer(List<String> operators) {
        Stack<String> stack = new Stack<>();
        List<String> postfixExp = new ArrayList<>();

        for (String s : operators) {
            if (isOperator(s)) {
                while (!stack.isEmpty() && !"(".equals(stack.peek())
                    && comparePrecedence(s.charAt(0), stack.peek().charAt(0)) <= 0) {
                    postfixExp.add(stack.pop());
                }
                stack.push(s);
            } else if ("(".equals(s)) {
                stack.push(s);
            } else if (")".equals(s)) {
                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    postfixExp.add(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Error, Invalid Expression, Mismatched parens");
                }
                stack.pop();
            } else {
                postfixExp.add(s);
            }
        }
        // add to list from stack
        while (!stack.isEmpty()) {
            postfixExp.add(stack.pop());
        }
        return postfixExp;
    }

    /**
     * compare the operator precedence
     *
     * @param op1 first operator
     * @param op2 second operator
     */
    private int comparePrecedence(char op1, char op2) {
        int first = getPrecedence(op1);
        int second = getPrecedence(op2);
        return first - second;
    }

    /**
     * the precedence of operator
     */
    private int getPrecedence(char ch) {
        int result = -1;
        switch (ch) {
            case '+':
            case '-':
                result = 1;
                break;
            case '*':
            case '/':
            case '^':
                result = 2;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }

    /**
     * check is operator
     */
    public boolean isOperator(char ch) {
        switch (ch) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
            default:
                return false;
        }
    }

    /**
     * @param op input.
     * @return True if input is a single character string that represents a valid operator.
     */
    public boolean isOperator(String op) {
        if (op.length() == 1) {
            return isOperator(op.charAt(0));
        } else {
            return false;
        }
    }
}
