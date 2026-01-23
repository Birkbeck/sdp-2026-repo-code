package defaultmethods;

interface Alpha {
    default void reset() {
        System.out.println("Alpha.reset");
    }
}

interface Beta {
    default void reset() {
        System.out.println("Beta.reset");
    }
}

public class MyClass implements Alpha, Beta {
    @Override
    // must override reset as Alpha and Beta's default implementations
    // are unrelated (neither interface extends the other)
    public void reset() {
        Alpha.super.reset();
        Beta.super.reset();
        System.out.println("MyClass.reset");
    }
}
