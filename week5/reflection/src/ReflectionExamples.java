import java.lang.reflect.Constructor;
import java.util.Map;

record A(int i) {
    public A(String s) {
        this(Integer.parseInt(s));
    }
}

record B(double d) {
    public B(String s) {
        this(Double.parseDouble(s));
    }
}

public class ReflectionExamples {

    private static final Map<String, Class<?>> MAP = Map.of(
            "A", A.class,
            "B", B.class
    );

    // this example of using Reflection API is useless
    // as both A and B must be available at compile time - new A("1") is shorter
    public static void main(String... args) throws ReflectiveOperationException {
        Class<?> clazz = MAP.get("A");
        Constructor<?> constructor = clazz.getConstructor(String.class);
        Object o = constructor.newInstance("1");
    }
}







