package iterator;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public final class IteratorGoF {
    static final List<Integer> list = asList(1, 2, 3, 4, 5, 6);

    public static void main(String... args) {

        for (Integer integer : list) {
            System.out.println(integer);
        }

        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer i = iterator.next();
            System.out.println(i);
        }
    }
}
