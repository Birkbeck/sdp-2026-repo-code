package classes;

class OuterClass {
    private int variable = 1;
    class InnerClass {
        // Each instance of InnerClass has a reference to
        // the enclosing instance of OuterClass.
        // So, each instance of InnerClass has direct access to
        // the fields and methods of the enclosing instance of OuterClass

        private int variable = 2; // shadows variable from OuterClass

        int method1(int variable) {
            // variable refers to the parameter variable
            return this.variable; // refers to variable from InnerClass
        }

        int method2() {
            OuterClass outer = OuterClass.this;  // refers to the enclosing instance
            return outer.variable; // refers to variable from OuterClass
            // alternatively, one can write a shorter version
            // return OuterClass.this.variable; // refers to variable from OuterClass
        }
    }

    InnerClass method() {
        return new InnerClass();
    }


    // Instances of StaticNestedClass are not connected
    // to instances of OuterClass - they are completely independent!
    // The "static" modifier significantly changes the behaviour!
    // Static nested classes are mainly used to reduce visibility of class names.

    static class StaticNestedClass {
        private int variable = 3;
        int method() {
            //return OuterClass.this.variable; // Outer.this not available
            return variable; // refers to variable in StaticNestedClass
        }
    }
}



public class NestedClassDemo {

    /*
       for varargs (...) - read
            https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html
            https://docs.oracle.com/javase/8/docs/technotes/guides/language/non-reifiable-varargs.html
     */
    public static void main(String... args) {
        OuterClass outer = new OuterClass();

        // one way of creating an instance of InnerClass for outer
        OuterClass.InnerClass inner0 = outer.method();
        System.out.println(inner0.method1(42));

        // another way of creating an instance of InnerClass
        OuterClass.InnerClass inner = outer.new InnerClass();
        System.out.println(inner.method2());

        // no reference to an instance of OuterClass for
        // creating instances of nested classes
        OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
        System.out.println(nested.method());
    }
}
