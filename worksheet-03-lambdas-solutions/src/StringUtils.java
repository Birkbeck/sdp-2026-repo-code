import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class StringUtils {

    /**
     * Compares two Strings so that Strings that include the letter
     * e are listed before Strings without a letter e.
     *
     * @param s1 String to be compared
     * @param s2 String to be compared
     * @return -1 if s1 contains e and s2 does not, 1 if s2 contains e and s1 does not
     * and 0 if both contain e.
     */
    public static int eChecker(String s1, String s2) {
        if (s1.contains("e") && !s2.contains("e")) return -1;
        if (s2.contains("e") && !s1.contains("e")) return 1;
        return 0;
    }

    /**
     * Applies a lambda to two Strings to define which of the Strings is better.
     * The method should return the better string
     *
     * @param s1     String to be compared
     * @param s2     String to be compared
     * @param lambda implementation of TwoStringPredicate that compares the two Strings
     * @return the better String
     */
    public static String betterString(String s1, String s2, TwoStringPredicate lambda) {
        return lambda.findBest(s1, s2) ? s1 : s2;
    }

    /**
     * Applies a lambda to two objects of type T to determine
     * which of the objects is better.
     * The method should return the better object
     *
     * @param t1     object of type T to be compared
     * @param t2     object of type T to be compared
     * @param lambda implementation of TwoElementPredicate that compares the two objects of type T
     * @param <T>    the generic type
     * @return the better object of type T
     */
    public static <T> T betterElement(T t1, T t2, TwoElementPredicate<? super T> lambda) {
        return lambda.findBest(t1, t2) ? t1 : t2;
    }

    /**
     * Takes a List of Strings and a Predicate of String,
     * and returns a new List of all the values that passed the test.
     *
     * @param lst  List of String
     * @param pred java.util.function.Predicate representing the test
     * @return List of Strings that pass the test
     */
    public static List<String> allStringMatches(List<String> lst, Predicate<String> pred) {
        List<String> result = new ArrayList<>();
        for (String s : lst)
            if (pred.test(s))
                result.add(s);

        return result;
    }


    /**
     * Take a List of T and a Predicate of T,
     * and returns a new List of all the values that passed the test.
     *
     * @param lst  List of type T
     * @param pred java.util.function.Predicate representing the test
     * @param <T>  Generic type
     * @return List of type T of elements that pass the test
     */
    public static <T> List<T> allMatches(List<T> lst, Predicate<? super T> pred) {
        List<T> result = new ArrayList<>();
        for (T s : lst)
            if (pred.test(s))
                result.add(s);

        return result;
    }

    /**
     * Takes a List of type String and a Function from String to String
     * and returns a new List that contains the result of applying the function to
     * each element of the original List.
     *
     * @param lst  List of String
     * @param func java.util.function.Function that will be applied to each element of the list
     * @return List of Strings after the application of the function
     */

    public static List<String> transformedStringList(List<String> lst, Function<String, String> func) {
        List<String> result = new ArrayList<>();
        for (String s : lst)
            result.add(func.apply(s));

        return result;
    }


    /**
     * Takes a List of Type S and a Function from S to T and returns a new List that
     * contains the result of applying the function to each element of the original List.
     *
     * @param lst  List of Type S
     * @param func java.util.function.Function that will be applied to each element of the list
     * @param <S>  Generic type
     * @param <T>  Generic type
     * @return List of type T of values after the application of the function
     */
    public static <S, T> List<T> transformedList(List<S> lst, Function<? super S, ? extends T> func) {
        List<T> result = new ArrayList<>();
        for (S s : lst) {
            T t = func.apply(s);
            result.add(t);
        }

        return result;
    }
}
