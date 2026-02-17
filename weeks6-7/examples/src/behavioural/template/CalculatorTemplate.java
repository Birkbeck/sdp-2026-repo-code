package behavioural.template;

public abstract class CalculatorTemplate implements Calculator {
    private double result;
    private boolean initialized;

    @Override
    public final void calculate(double operand) {
        if (initialized) {
            result = doCalculate(result, operand);
        }
        else {
            result = operand;
            initialized = true;
        }
    }

    @Override
    public final double getResult() {
        return result; // throw exception if not initialized
    }

    protected abstract double doCalculate(double o1, double o2);
}