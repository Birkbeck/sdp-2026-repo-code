package extra;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// the annotation is optional,
// but it ensures the compiler checks that the interface has a single abstract method (SAM)
@FunctionalInterface
interface MyTransformation {
    String transform(String s);
}

class ToUpperCaseTransformation implements  MyTransformation {
    @Override
    public String transform(String s) {
        return s.toUpperCase();
    }
}

public class Lambdas {

    static String[] transformArray(String[] array, MyTransformation transformation) {
        String[] r = new String[array.length];
        for (int i = 0; i < r.length; i++) {
            r[i] = transformation.transform(array[i]);
        }
        return r;
    }

    static String transformString(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String[] input = {"aaa", "bbb", "ccc"};

        // plain old Java
        MyTransformation toUpperCase = new ToUpperCaseTransformation();
        String[] res = transformArray(input, toUpperCase);
        System.out.println(Arrays.toString(res));

        // still old Java, with an anonymous
        MyTransformation toUpperCase1 = new MyTransformation() {
            @Override
            public String transform(String s) {
                return s.toUpperCase();
            }
        };
        String[] res1 = transformArray(input, toUpperCase1);
        System.out.println(Arrays.toString(res1));

        // lambda
        String[] res2 = transformArray(input, s -> s.toUpperCase());
        System.out.println(Arrays.toString(res2));

        // method reference
        String[] res3 = transformArray(input, String::toUpperCase);
        System.out.println(Arrays.toString(res3));

        // method reference
        String[] res4 = transformArray(input, Lambdas::transformString);
        System.out.println(Arrays.toString(res4));
    }

    static void methodReferences() {
        // all three match the same method signature
        //    void method-name(Lambdas)
        Consumer<Lambdas> c = Lambdas::f;
        Consumer<Lambdas> c2 = Lambdas::g;
        Consumer<Lambdas> c3 = Lambdas::g2;

        Lambdas l = new Lambdas();
        // all three methods need a Lambdas object for the call
        f(l);  // as a parameter
        l.g(); // as an object on which the method is called (this)
        l.g2(); // same here

        // all three match the same method signature
        //     void method-name()
        Runnable r = () -> f(l);
        Runnable r2 = l::g;
        Runnable r3 = l::g2;
    }

    static void f(Lambdas lambdas) {
    }

    // this is always an "implicit parameter" for every instance method
    void g() {
        System.out.println(this.hashCode());
    }

    // this _can be_ used as a first parameter to make it explicit
    void g2(Lambdas this) {
        System.out.println(this.hashCode());
    }

    // again, these two methods are identical
    void h(int i) {
    }
    void h2(Lambdas this, int i) {
    }

    // the first two methods below are identical (but the second is a bit more symmetric)
    boolean same(Lambdas other) {
        return this == other;
    }
    boolean same1(Lambdas this, Lambdas other) {
        return this == other;
    }
    // static (class) methods have no "this" parameter
    static boolean same(Lambdas lambda1, Lambdas lambda2) {
        return lambda1 == lambda2;
    }
}
