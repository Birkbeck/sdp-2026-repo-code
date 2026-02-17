public class Fibonacci {
    public static void main() {
        System.out.println(fib(7));
    }

    public static int fib(int n) {
        if (n > 2)
            return fib(n - 1) + fib(n - 2);

        return 1;
    }
}
