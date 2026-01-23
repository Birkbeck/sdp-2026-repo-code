package demos;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

class MyClass {
    final String s;

    MyClass(String s) {
        this.s = s;
    }

    String method(MyClass other) {
        return "MyClass.method: this = " + this.s + ", other = " + other.s;
    }

    String methodThis(
            MyClass this, // this refers to the object on which the method is called
            MyClass other) {
        return "MyClass.methodThis: this = " + this.s + ", other = " + other.s;
    }

    static String staticMethod(MyClass first, MyClass second) {
        return "MyClass.staticMethod: first = " + first.s + ", second = " + second.s;
    }
}

class MySubClass extends MyClass {
    MySubClass(String s) {
        super(s);
    }

    @Override
    String method(MyClass other) {
        return "MySubClass.method: this = " + this.s + ", other = " + other.s;
    }
}


public class MethodReferencesExample {

    public static void main(String... args) {

        MyClass o1 = new MyClass("1");
        MyClass o2 = new MyClass("2");
        // each of the three method calls below work on two objects, o1 and o2
        System.out.println(o1.method(o2));
        System.out.println(o1.methodThis(o2));
        System.out.println(MyClass.staticMethod(o1, o2));

        // each of the three methods references matches BiFunction<MyClass, MyClass, String>
        BiFunction<MyClass, MyClass, String> function1 = MyClass::method;
        caller(MyClass::method, o1, o2);
        BiFunction<MyClass, MyClass, String> function2 = MyClass::methodThis;
        caller(MyClass::methodThis, o1, o2);
        BiFunction<MyClass, MyClass, String> function3 = MyClass::staticMethod;
        caller(MyClass::staticMethod, o1, o2);

        MySubClass o3 = new MySubClass("3");
        caller(MyClass::method, o3, o1);
        // is equivalent to (and thus, which implementation of method
        // is called depends on the type of a1 (MyClass or MySubClass) at run-time)
        caller((a1, a2) -> a1.method(a2), o3, o1);
        // the same applies to Method::methodThis,
        // but not to MyClass::staticMethod (as one cannot override static methods)

        Function<MyClass, String> function4 = o1::method;
        caller2(o1::method, o2);
        // is equivalent to
        Function<MyClass, String> function5 = a2 -> o1.method(a2);
        caller2(a2 -> o1.method(a2), o2);
        // but the following calls the subclass method:
        Function<MyClass, String> function5b = a1 -> o2.method(a1);
        caller2(a1 -> o2.method(a1), o1);
        // and so does the following:
        Function<MyClass, String> function4b = o2::method;
        caller2(o2::method, o1);
        // the same applies to Method::methodThis,
        // but not to MyClass::staticMethod

        // constructor references
        MyClass[] array = Stream.of("i", "ii", "iii")
                .map(MyClass::new)
                .toArray(MyClass[]::new);
    }

    static void caller(BiFunction<MyClass, MyClass, String> p, MyClass o1, MyClass o2) {
        System.out.println(p.apply(o1, o2));
        System.out.println(p.apply(o2, o1));
    }

    static void caller2(Function<MyClass, String> p, MyClass o2) {
        System.out.println(p.apply(o2));
    }
}
