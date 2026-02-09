// generic interface for Question 4

@FunctionalInterface
interface TwoElementPredicate<T> {
    boolean findBest(T s1, T s2);
}
