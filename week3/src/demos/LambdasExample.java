package demos;

import java.util.*;

public class LambdasExample {

    public static void main(String... args) {
        lambdasVersion();
        methodReferencesVersion();
    }

    private static void lambdasVersion() {
        String[] strings = {"Mary", "had", "a", "little", "Lamb"};
        Arrays.sort(strings, (first, second) -> first.compareToIgnoreCase(second));
        System.out.println(Arrays.toString(strings));  // [a, had, lamb, little, Mary]

        List<String> list = new ArrayList<>(Arrays.asList("Malfoy", "Crabbe", null, "Goyle"));
        list.removeIf(e -> e == null);
        System.out.println(list); // [Malfoy, Crabbe, Goyle]
    }

    // method references provide a more concise notation

    private static void methodReferencesVersion() {
        String[] strings = {"Mary", "had", "a", "little", "Lamb"};
        Arrays.sort(strings, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strings));    // [a, had, lamb, little, Mary]

        ArrayList<String> list = new ArrayList<>(Arrays.asList("Malfoy", "Crabbe", null, "Goyle"));
        list.removeIf(Objects::isNull);
        System.out.println(list); // [Malfoy, Crabbe, Goyle]
    }
}
