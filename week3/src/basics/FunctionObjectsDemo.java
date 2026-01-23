package basics;

interface IntBinaryFunction {
    int apply(int i, int j);
}

// Option 1: standalone class (since Java 1.0)
class IntMinimumStandalone implements IntBinaryFunction {
    // a typical function object
    //    - it has no state (no instance variables)
    //    - it implements a single method that transforms its inputs and produces an output
    //    - it has no side effects (does not modify any global variables, does not write into files, etc.)
    @Override public int apply(int i, int j) {
        return i < j ? i : j;
    }
}

public class FunctionObjectsDemo {

    public static void main(String... args) {
        int[] a1 = { 5, 2, 4, 1, 3, 8 };

        int r1 = arrayFunctionApplication(a1, new IntMinimumStandalone());
        System.out.println(r1); // good, but the class name is global (is it needed?)

        int r2 = arrayFunctionApplication(a1, new IntMinimumStatic());
        System.out.println(r2); // the class is private and its name is FunctionObjectsDemo.IntMinimumStatic

        // Step 3: local class, with an even  more local name that cannot be used elsewhere (since Java 1.1)
        class IntMinimumLocal implements IntBinaryFunction {
            @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
        }
        int r3 = arrayFunctionApplication(a1, new IntMinimumLocal());
        System.out.println(r3); // good, but do we need the class name at all then?

        // Step 4: anonymous class (since Java 1.1)
        int r4 = arrayFunctionApplication(a1, new IntBinaryFunction() {
            @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
        });
        System.out.println(r4); // good, but do we need to write the boilerplate?

        // Step 5: typed lambda with full body (since Java 8)
        int r5 = arrayFunctionApplication(a1, (int i, int j) -> {
            return i < j ? i : j;
        });
        System.out.println(r5); // good, but can Java not see that it's ints?

        // Step 6: short lambda with type inferencing (since Java 8)
        int r6 = arrayFunctionApplication(a1, (i, j) -> i < j ? i : j);
        System.out.println(r6); // good, but has no-one else ever written something like this?

        // Step 7: method reference (Java 8)
        int r7 = arrayFunctionApplication(a1, Integer::min);
        System.out.println(r7);

        // Step 8: method reference to another function object
        int r8 = arrayFunctionApplication(a1, Integer::max);
        System.out.println(r8);

        // Step 9: method reference to yet another function object
        int r9 = arrayFunctionApplication(a1, Integer::sum);
        System.out.println(r9);
    }

    // Step 2: nested static class, with a more local name (since Java 1.1)
    // private nested classes are not accessible from the outside
    private static class IntMinimumStatic implements IntBinaryFunction {
        @Override public int apply(int i, int j) {
                return i < j ? i : j;
            }
    }

    static int arrayFunctionApplication(int[] array, IntBinaryFunction function) {
        int result = function.apply(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            result = function.apply(result, array[i]);
        }
        return result;
    }
}
