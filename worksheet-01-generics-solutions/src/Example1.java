public class Example1 {
    public static void main(String... args) {
        // use either <> or var
        // if both are used, then T is inferred to be Object
        Storage<BankAccount> bankAccountStorage0 = new Storage<>();
        var bankAccountStorage = new Storage<>();
        var stringStorage = new Storage<String>();

        BankAccount account = new BankAccount(2025);
        bankAccountStorage.setItem(account);

        Object s = account;
        f(account);
        account = s;
        f2(s);

        BankAccount account1 = bankAccountStorage.getItem();
        account1.deposit(15);
    }

    static void f(Object s) {
        s.hashCod();
    }

    static void f2(BankAccount account) {
        account.deposit(15);
    }
}
