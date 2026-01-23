package lambdas;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {

        Consumer<Integer> consumer = i -> System.out.print(" " + i);
        List<Integer> integerList = List.of(1, 10, 200, 101, -10, 0);
        printList(integerList, consumer);

        /*
         * A new instance of Consumer interface is defined which is assigned a value of the previous consumer
         * interface (which prints just the space prepended integer) aggregated with the new lambda
         * (which prints (printed i) where i is replaced by the value of the integer passed as input to the lambda).
         * We then have two Consumer Interface based operations aggregated – first one prints the integer value and the
         * second one prints (print i) where i is the value of the integer passed.
         */
        Consumer<Integer> consumerWithAndThen = consumer
                .andThen(i -> System.out.print("(printed " + i + ")"));

        /*
         * The consumer interface instance representing aggregated consumer operations is passed as parameter to
         * the printList() method.
         * While in the previous example the consumer interface passed to it contained a single operation which simply
         * printed the integer value prepended with a single space character; this time the consumer interface carries out
         * two operations which have been aggregated together for each value – printing the integer value
         * with space prepended and then printing the string (printed i), where i is the integer passed to it.
         */
        printList(integerList, consumerWithAndThen);
    }

    /*
     * The printList() method simply calls the accept() method on the consumer interface passed to it
     * while iterating through the list of integers passed to it.
     */
    public static void printList(List<Integer> listOfIntegers, Consumer<Integer> consumer) {
        for (Integer integer : listOfIntegers) {
            consumer.accept(integer);
        }
    }
}
