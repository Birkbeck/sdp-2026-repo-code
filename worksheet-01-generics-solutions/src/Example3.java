public class Example3 {
    public static void main(String[] args) {
        Storage<String> storage = new Storage<>();
        storage.setItem("Happy 2025!");
        //test(storage); does not compile due to invariance of generics
        Storage<SavingsAccount> storage1 = new Storage<>();
        test(storage1);
    }

    public static void test(Storage<BankAccount> s) {
        System.out.println("in test - before storing into s");
        s.setItem(new BankAccount(2025));
        System.out.println("in test - after storing into s");
    }
}
