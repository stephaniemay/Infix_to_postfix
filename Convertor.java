import java.util.*;

public class Convertor {

    public static String convertToPostfix(String expression) {
        StringBuilder postFix = new StringBuilder(expression.length());
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (character == ')') {
                if (!stack.isEmpty()) {
                    postFix.append(stack.pop());
                }
                postFix.append(" ");
            } else if (operatorCheck(character)) {
                if (!stack.isEmpty()) {
                    if (pemdas(stack.peek()) > pemdas(character)) {
                        postFix.append(stack.pop()).append(" ");
                    }
                }
                stack.push(character);
            } else if (Character.isDigit(character)) {
                postFix.append(character);
                if (i == expression.length() - 1) {
                    postFix.append(" ");
                }
            } else if (Character.isWhitespace(character) && Character.isDigit(expression.charAt(i - 1))) {
                postFix.append(" ");
            }
            if (i == expression.length() - 1) {
                for (int j = 0; j < stack.size() + 1; j++) {
                    postFix.append(stack.pop()).append(" ");
                }
            }
        }
        return postFix.toString();
    }

    public static String evaluatePostfix(String postfixExpr) {
        StringBuilder evaluatedPostfix = new StringBuilder();
        Stack<Float> expression = new Stack<>();
        float num = postfixExpr.length();
        float operand1, operand2;
        float exp = 0;
       
        for (int i = 0; i < num; i++) {
            if (operatorCheck(postfixExpr.charAt(i))) {
                operand1 = expression.pop();
                operand2 = expression.pop();

                switch (postfixExpr.charAt(i)) {
                      case '+' -> {
                        expression.push(operand2 + operand1);
                        exp = (operand2 + operand1);
                    } case '-' -> {
                        expression.push(operand2 - operand1);
                        exp = (operand2 - operand1);
                    } case '*' -> {
                        expression.push(operand2 * operand1);
                        exp = (operand2 * operand1);
                    } case '/' -> {
                        expression.push(operand2 / operand1);
                        exp = (operand2 / operand1);
                    }
                }
                
            } else if (!Character.isWhitespace(postfixExpr.charAt(i))) {
                String val = String.valueOf(postfixExpr.charAt(i));
                while (Character.isDigit(postfixExpr.charAt(i + 1))) {
                    val += String.valueOf(postfixExpr.charAt(i + 1));
                    i++;
                }
                expression.push(Float.valueOf(val));  
            }
            if (i == num - 1)
                evaluatedPostfix.append(exp);
        }
        return evaluatedPostfix.toString();
    }

    public static boolean operatorCheck(char ch) {
        return ch == '+' || ch == '-' || ch == '/' || ch == '*';
    }

    public static int pemdas(char ch) {
        int val;
        val = switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
        return val;
    }
}
