package generics;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        // raw types (no <...>)
        List rawList = new ArrayList();
        rawList.add("s");
        rawList.add(1);
        String s = (String)rawList.get(0);

        // raw types do not offer type safety
        List<Integer> intList = rawList;
        //int j = intList.get(0); // ClassCast Exception at run time

        rawList = new ArrayList<String>();

        // a proper list of objects
        List<Object> objectList = new ArrayList<>();
        objectList.add("s");
        objectList.add(1);

        String s2 = (String)objectList.get(0); // ok, but need to use cast
        int i = (Integer)objectList.get(1); // also ok, but again with a cast

        // wildcards are unknown types
        List<?> wildcardList = new ArrayList<String>();
        // wildcardList.add("s"); does not compile as the type of elements is not known
        // wildcardList.add(1); does not compile as the type of elements is not known
        wildcardList.add(null); // null "belongs" to any reference type
        Object o = wildcardList.get(0); // works, but we cannot be any more specific than Object without a cast
    }
}
