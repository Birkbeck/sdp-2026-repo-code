import java.util.Map;

interface Expression {
    int evaluate(Map<String, Integer> variables);
}

record Constant(int value) implements Expression {
    @Override
    public int evaluate(Map<String, Integer> variables) {
        return value;
    }
}

record Variable(String name) implements Expression {
    @Override
    public int evaluate(Map<String, Integer> variables) {
        return variables.get(this.name);
    }
}

record PlusExpression(Expression left, Expression right) implements Expression {
    @Override
    public int evaluate(Map<String, Integer> variables) {
        int leftVal = left.evaluate(variables);
        int rightVal = right.evaluate(variables);
        return leftVal + rightVal;
    }
}

record TimesExpression(Expression left, Expression right) implements Expression {
    @Override
    public int evaluate(Map<String, Integer> variables) {
        int leftVal = left.evaluate(variables);
        int rightVal = right.evaluate(variables);
        return leftVal * rightVal;
    }
}

public class ExpressionDemo {
    public  static void main(String[] args) {
        Map<String, Integer> variables = Map.of("v", 3, "w", 4);
        Expression expression = new TimesExpression(
                new PlusExpression(
                        new Constant(1),
                        new Variable("v")),
                new PlusExpression(
                        new Variable("w"),
                        new Constant(2)));
        int r = expression.evaluate(variables);
    }
}
