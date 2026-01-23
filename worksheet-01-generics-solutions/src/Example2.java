public class Example2 {
    public static void main(String[] args) {
        String[] array = new String[2];
        array[0] = "hi there";
        test(array);
    }

    public static void test(Object[] a) {
        System.out.println("in test - before storing into a");
        // ArrayStoreException below if a is not an array of BankAccounts
        a[1] = new BankAccount(2025);
        System.out.println("in test - after storing into a");
    }

    static void test2(BankAccount[] a) {
        a[0] = new SavingsAccount(1, 2);
    }
}
