public class Example4 {
    public static void main(String... args) {
        var storage1 = new Storage<BankAccount>();
        storage1.setItem(new SavingsAccount(2024, 3));
        process(storage1);

        var storage2 = new Storage<SavingsAccount>();
        storage2.setItem(new SavingsAccount(2025, 2));
         process(storage2);
    }

    public static void process(Storage<? extends BankAccount> s) {
        // s is used as a "producer" of BankAccounts
        // s can give us a BankAccount or a SavingsAccount
        // ... or an instance of any other subclass of BankAccount
        BankAccount a = s.getItem();
        // ... = s (similar to using s on the right-hand side of an assignment)
        System.out.println("Account " + a.getAccountNumber());
    }

    // this version gives more flexibility as the type T can now be used in other contexts too,
    // for example, as the return type
    public static <T extends BankAccount> T processWithCapture(Storage<T> s) {
        T a = s.getItem();
        System.out.println("Account " + a.getAccountNumber());
        return a;
    }

}
