package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SyncProblems {
    private static int count = 0;

    public static void main(String... args) {
        IntStream.range(0, 10)
                .forEach(i -> addNumbersInParallel());
    }

    static void addNumbersInParallel() {
        count = 0;
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            IntStream.range(0, 10_000)
                    .forEach(i -> executor.submit(SyncProblems::incrementSync));
        }
        System.out.println(count);
    }

    static void increment() {
        count++;
    }

    static synchronized void incrementSync() {
        count++;
    }

}
