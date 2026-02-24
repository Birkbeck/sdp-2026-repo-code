package reflection.question2;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * An object created from a fully qualified Java class name and list of arguments,
 * or just a fully qualified Java class name, any arguments being
 * supplied via the command line.
 */
public class InstantiateClass {

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Object obj1 = args.length > 1
                        ? builder(args[0], Arrays.copyOfRange(args, 1, args.length))
                        : builder(args[0], new String[]{});

                if (obj1 != null)
                    System.out.println(obj1 + " - " + obj1.getClass());
                else
                    System.out.println("Failed to create " + Arrays.toString(args));
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates an instance of the class described in param className should such a
     * class exist. An appropriate constructor is chosen based on the number of
     * elements in the array argumentsList and all elements are passed to the
     * constructor as an appropriate type.
     *
     * @param className     fully qualified Java class name as a string
     * @param argumentsList String array of arguments or an empty String array to
     *                      pass to the class constructor
     * @return instantiated Object of class type className or null if construction is
     * not possible.
     * @throws ClassNotFoundException className does not refer to a known Class
     */
    public static Object builder(String className, String[] argumentsList) throws ClassNotFoundException {
        for (Constructor<?> candidateConstructor : Class.forName(className).getConstructors()) {

            Class<?>[] parameterTypes = candidateConstructor.getParameterTypes();
            if (parameterTypes.length == argumentsList.length) {

                Object[] parameters = IntStream.range(0, argumentsList.length)
                        .mapToObj(i -> getConstructorFromString(parameterTypes[i])
                                .apply(argumentsList[i]))
                        .flatMap(Optional::stream) // remove empty optionals
                        .toArray();

                if (parameters.length == argumentsList.length) { // there were no empty optionals
                    Optional<Object> opt = construct(candidateConstructor, parameters);
                    if (opt.isPresent())
                        return opt.get();
                }
            }
        }

        return null;
    }

    private static Function<String, Optional<Object>> getConstructorFromString(Class<?> clazz) {

        Function<String, Optional<Object>> special = SPECIAL_CONSTRUCTORS.get(clazz);
        if (special != null)
            return special;

        try {
            Constructor<?> c = clazz.getConstructor(String.class);
            return s -> construct(c, new Object[] { s });
        }
        catch (NoSuchMethodException e) {
            return s -> Optional.empty();
        }
    }

    /**
     * Use the provided constructor to construct an object without throwing ReflectiveOperationException
     * @param constructor the constructor
     * @param arguments an array containing constructor arguments
     * @return an optional with a newly created object or empty optional if an exception is thrown
     * @param <T> the type of objects
     */
    private static <T> Optional<T> construct(Constructor<? extends T> constructor, Object[] arguments) {
        try {
            return Optional.of(constructor.newInstance(arguments));
        }
        catch (ReflectiveOperationException e) {
            return Optional.empty();
        }
    }

    private static final Map<Class<?>, Function<String, Optional<Object>>> SPECIAL_CONSTRUCTORS = Map.of(
            int.class, s -> Optional.of(Integer.valueOf(s)),
            long.class, s -> Optional.of(Long.valueOf(s)),
            short.class, s -> Optional.of(Short.valueOf(s)),
            byte.class, s -> Optional.of(Byte.valueOf(s)),
            float.class, s -> Optional.of(Float.valueOf(s)),
            double.class, s -> Optional.of(Double.valueOf(s)),
            boolean.class, s -> Optional.of(Boolean.valueOf(s)),
            char.class, s -> s.length() == 1 ? Optional.of(s.charAt(0)) : Optional.empty()
    );
}