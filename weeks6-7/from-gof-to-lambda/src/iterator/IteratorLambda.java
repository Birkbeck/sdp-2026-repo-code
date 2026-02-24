package iterator;

import java.util.List;

import static java.util.Arrays.asList;

public final class IteratorLambda {
    static final List<Integer> list = asList(1, 2, 3, 4, 5, 6);

    public static void main(String... args) {
        list.forEach(System.out::println);
    }
}
