import java.util.*;

/**
 * Created by Meemaw on 12/06/16.
 */
public class Calculator {

    private static final int LEFT_PARENTHESIS = -1;
    private static final int RIGHT_PARENTHESIS = -2;
    private static final int RIGHT_ASOCIATIVITY = 1;
    private static final int LEFT_ASOCIATIVITY = 0;

    /**
     * Static Hashmap for constant lookup time for operator precedence and asociativity
     * {precedence, asociativity}
     */
    private static Map<String, int[]> precedenceMap = new HashMap<String, int[]>() {{
        put("+", new int[]{2, LEFT_ASOCIATIVITY});
        put("-", new int[]{2, LEFT_ASOCIATIVITY});
        put("*", new int[]{3, LEFT_ASOCIATIVITY});
        put("/", new int[]{3, LEFT_ASOCIATIVITY});
        put("^", new int[]{4, RIGHT_ASOCIATIVITY});
        put("(", new int[]{LEFT_PARENTHESIS, LEFT_PARENTHESIS});
        put(")", new int[]{RIGHT_PARENTHESIS, RIGHT_PARENTHESIS});
    }};

    private boolean isNumber(String token) {
        return token.matches("^-?\\d+$");
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    /**
     * Returns tokenized expression with one whitespace between all operands.
     * Example: (1+2     / (2*1)-5     )  --->  ( 1 + 2 / ( 2 * 1 ) - 5 )
     *
     * @param expression
     * @return tokenized expression
     */
    private static String tokenizeInput(String expression) {
        return expression.trim().replaceAll("(?<=\\S)(?:(?<=\\p{Punct})|(?=\\p{Punct}))(?=\\S)", " ");
    }

    /**
     * Shunting-yard algorithm to parse expression
     *
     * @param infixExpression arithmetic expression in infix notation
     * @return expression in postfix notation (Reverse Polish notation)
     */
    private String toPostfix(String infixExpression) {

        StringBuilder builder = new StringBuilder();
        Stack<String> s = new Stack<>();

        for (String token : tokenizeInput(infixExpression).split(" ")) {
            if (token.isEmpty()) continue;
            if (isNumber(token)) builder.append(token).append(' ');
            else if (isOperator(token)) {
                if (s.isEmpty()) s.push(token);
                else {
                    while (!s.isEmpty()) {
                        int precSecond = precedenceMap.get(s.peek())[0];
                        int precFirst = precedenceMap.get(token)[0];
                        int asociativity = precedenceMap.get(token)[1];
                        if ((asociativity == LEFT_ASOCIATIVITY && precFirst <= precSecond) || (asociativity == RIGHT_ASOCIATIVITY && precFirst < precSecond))
                            builder.append(s.pop()).append(' ');
                        else
                            break;

                    }
                    s.push(token);
                }
            } else if (token.equals("(")) {
                s.push(token);
            } else if (token.equals(")")) {
                while (precedenceMap.get(s.peek())[0] != LEFT_PARENTHESIS)
                    builder.append(s.pop()).append(' ');
                s.pop();
            }
        }
        while (!s.isEmpty())
            builder.append(s.pop()).append(' ');

        return builder.toString();
    }

    /**
     * @param operator for evaluating the expression
     * @param first    value of expression
     * @param second   value of expression
     * @return evaluated expression value
     */
    private long evaluate(String operator, long first, long second) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
            case "−":
                return first - second;
            case "*":
            case "×":
                return first * second;
            case "/":
                return first / second;
            case "^":
                return (long) Math.pow(first, second);
            default:
                throw new NumberFormatException("Unknown operator");
        }
    }

    /**
     * Postfix algorithm to evaluate postfix expression also known as Reverse Polish notation
     *
     * @param postfixExpression being evaluated
     * @return result of arithemtic expression
     */
    private long reversePolishNotation(String postfixExpression) {
        Stack<Long> stack = new Stack<>();
        for (String token : postfixExpression.split("\\s")) {
            if (token.isEmpty()) continue;
            if (isNumber(token)) stack.push(Long.parseLong(token));
            else {
                if (stack.size() < 2) throw new NumberFormatException("Wrong expression. Not enough values on stack!");
                long valueSecond = stack.pop();
                long valueFirst = stack.pop();
                long result = evaluate(token, valueFirst, valueSecond);
                stack.push(result);
            }
        }
        if (stack.size() == 1) return stack.pop();
        else throw new NumberFormatException("Wrong expresion. Too many values on stack");
    }

    /**
     * Public method for calculating result.
     *
     * @param expression to evaluate
     * @return result
     */
    public long calculate(String expression) {
        return reversePolishNotation(toPostfix(expression));
    }

    /**
     * Public method for calculating result and obtaining some more information about calculation
     *
     * @param expression being evaluated
     * @return "user input, postfix expression, time spent for calculation, result"
     */
    public String getTrace(String expression) {
        long t1 = System.currentTimeMillis();
        String postfix = toPostfix(expression);
        long duration = System.currentTimeMillis() - t1;
        StringBuilder builder = new StringBuilder();
        String inputString = "User input               ->   " + expression + "\n";
        builder.append(inputString);
        builder.append("Reverse Polish notation  ->   ").append(postfix).append("\n");
        builder.append("Time spent calculating   ->   ").append(duration).append(" ms").append("\n");
        for (int i = 0; i < inputString.length(); i++)
            builder.append("-");
        builder.append("\n").append(reversePolishNotation(postfix));
        return builder.toString();
    }
}