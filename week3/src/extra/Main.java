package extra;


import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        // static polymorphism (overloading)
        m.exp(Math.min(1, 2)); // exp(int) is called
        m.exp(Math.min(1L, 2)); // exp(long) is called

        BiConsumer<Main, Integer> r1 = Main::exp;
        BiConsumer<Main, Long> r2 = Main::exp;
        BiConsumer<Main, Integer> r3 = Main::exps;
        BiConsumer<Main, Long> r4 = Main::exps;

        // dynamic polymorphism (overriding)
        // whether Main.exp(int) or SubMain.exp(int) is called
        // depends on the type of the first argument of r1.accept
        r1.accept(m, 100);
        SubMain s = new SubMain();
        r1.accept(s, 100);
        // no dynamic polymorphism as r3 is a static method reference
        // so, Math.exp(int) is called even though the type of s is SubMain
        r3.accept(s, 100);

        // Math::max matches various functional interfaces from the standard library
        BiFunction<Integer, Integer, Integer> f = Math::max;
        BinaryOperator<Integer> f2 = Math::max;
        IntBinaryOperator f3 = Math::max;
        // and even some of our own
        IntMaxMethod f4 = Math::max;

        // the SAM in the interface has the same signature as method in class Main
        OurMethod2 om2 = m::method;
        // the SAM in the interface has the same signature as method in class Main
        // note that this time the method reference has no object and so,
        // the "this" parameter of the method needs to be included in the SAM signature
        OurMethod om = Main::method;
    }

    void method(int a, long b, double c, boolean f) {}

    void exp(int n) { System.out.println("Main.exp(int " + n + ")"); }
    void exp(long n) { System.out.println("Main.exp(long " + n + ")");}

    static void exps(Main m, int n) { System.out.println("Main.exps(Main m, int " + n + ")");}
    static void exps(Main m, long n) { System.out.println("Main.exps(Main m, long " + n + ")"); }
}

@FunctionalInterface
interface OurMethod {
    void method(Main m, int a, long b, double c, boolean f);
}

@FunctionalInterface
interface OurMethod2 {
    void method(int a, long b, double c, boolean f);
}

@FunctionalInterface
interface IntMaxMethod {
    int method(int a, int b);
}


class SubMain extends Main {
    void exp(int n) { System.out.println("SubMain.exp(int " + n + ")"); }
    void exp(long n) { System.out.println("SubMain.exp(long " + n + ")");}
}