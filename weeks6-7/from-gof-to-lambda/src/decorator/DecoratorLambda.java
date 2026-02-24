package decorator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class DecoratorLambda {

    public static void main(String... args) {
        DoubleUnaryOperator defaultSalaryCalculator = g -> g / 12;

        DoubleUnaryOperator calculator = defaultSalaryCalculator
                        .andThen(Taxes::generalTax)
                        .andThen(Taxes::regionalTax)
                        .andThen(Taxes::healthInsurance);

        System.out.println(calculator.applyAsDouble(80000.00));

        System.out.println(
                calculateSalary(80000.00,
                        defaultSalaryCalculator,
                        Taxes::generalTax,
                        Taxes::regionalTax,
                        Taxes::healthInsurance));
    }

    public static double calculateSalary(double annualGross, DoubleUnaryOperator... taxes) {
        DoubleUnaryOperator calculator = Stream.of(taxes)
                .reduce(DoubleUnaryOperator.identity(),
                        DoubleUnaryOperator::andThen);

        return calculator.applyAsDouble(annualGross);

/*
        return Stream.of(taxes)
                .collect(Collector.of(
                        () -> annualGross,
                        (v, f) -> f.applyAsDouble(v),
                        (v1, v2) -> 0.0));
 */
    }
}
