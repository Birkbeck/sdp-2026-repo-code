import java.lang.reflect.InvocationTargetException;

public class Reflection {
    public static void main(String... args)  {
        try {
            Class<?> clazz = Class.forName("Equals");

            System.out.println(clazz.getName());
            Object o = clazz.getConstructor().newInstance();
            System.out.println(o);

            Object o1 = new Equals();
        }
        catch (ClassNotFoundException e) {

        }
        catch (ReflectiveOperationException e) {

        }
    }
}
