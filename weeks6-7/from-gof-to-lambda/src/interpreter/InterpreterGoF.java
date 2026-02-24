package interpreter;

import java.util.Stack;

public class InterpreterGoF {

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*");
    }

    private static Expression getOperator(String s, Expression left, Expression right) {
        return switch (s) {
            case "+" -> new Add(left, right);
            case "-" -> new Subtract(left, right);
            case "*" -> new Product(left, right);
            default -> null;
        };
    }

    // expression is in postfix notation (e.g., 4 3 +)
    public static int evaluate(String expression) {
        Stack<Expression> stack = new Stack<>();
        for (String s : expression.split(" ")) {
            if (isOperator(s)) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                stack.push(getOperator(s, left, right));
            }
            else {
                Expression i = new Number(Integer.parseInt(s));
                stack.push(i);
            }
        }
        return stack.pop().interpret();
    }

    public static void main(String... args) {
        String expression = "7 3 - 2 1 + *";
        System.out.println(evaluate(expression));
    }

    interface Expression {
        int interpret();
    }

    record Add(Expression leftExpression, Expression rightExpression) implements Expression {

        @Override
        public int interpret() {
            return leftExpression.interpret() + rightExpression.interpret();
        }
    }

    record Subtract(Expression leftExpression, Expression rightExpression) implements Expression {

        @Override
        public int interpret() {
            return leftExpression.interpret() - rightExpression.interpret();
        }
    }

    record Product(Expression leftExpression, Expression rightExpression) implements Expression {

        @Override
        public int interpret() {
            return leftExpression.interpret() * rightExpression.interpret();
        }
    }

    record Number(int n) implements Expression {
        @Override
        public int interpret() {
            return n;
        }
    }
}
