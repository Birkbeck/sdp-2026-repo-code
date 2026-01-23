package lambdas;

import java.util.function.Predicate;

@FunctionalInterface
interface MyInterface {
    // SAM - Single Abstract Method
    void myMethod();

    // can't add this if we want to retain the "FunctionalInterface" annotation
    // void myOtherMethod();

    // static methods do not count
    static void myStaticMethod() { }

    // default methods do not count
    default void myDefaultMethod() {
        helper();
    }

    // private methods do not count
    private void helper() { }

    // could use a lambda here by assigning to a static field
    Predicate<String> STRING_PREDICATE = (x) -> true;
}

class MyInterfaceImpl implements MyInterface {

    @Override
    public void myMethod() {
        System.out.println("In " + this.getClass().getName());
    }
}

public class FunctionalInterfaceDemo {
    static class IntContainer {
        int v;
    }

    private static int nonFinalVariable;

    public static void main(String[] args) {
        // named class (lambdas.MyClass)
        MyInterface my = new MyInterfaceImpl();
        my.myMethod();  // In lambdas.MyInterfaceImpl

        int i = 2; // effectively final

        IntContainer c = new IntContainer(); // c is effectively final
        c.v = 42; // but the instance variable v of IntContainer is not final
                  // in other words, IntContainer is mutable

        // anonymous inner class (lambdas.Example$1)
        my = new MyInterface() {
            @Override
            public void myMethod() {
                // only effectively final local variables can be used here
                System.out.println("In " + this.getClass().getName() + " with " + i + " and " + c.v);
                c.v = 96; // change the contents of IntContainer
                // any instance / class variables can be used here
                nonFinalVariable = 101;
            }
        };
        my.myMethod();  // In lambdas.FunctionalInterfaceDemo$1

        // lambdas: shorter version with the same functionality
        my = () -> {
            System.out.println("In lambda with " + i + " and " + c.v);
            c.v = 85; // change the contents of IntContainer - should be avoided in lambdas
        };
        my.myMethod(); // In lambda expression

        // method reference
        // static method System.out.println() matches the SAM of MyInterface
        my = System.out::println;
        my.myMethod(); // a blank line

        System.out.println("End");
    }
}

