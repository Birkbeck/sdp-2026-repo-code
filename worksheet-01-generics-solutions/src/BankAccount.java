public class BankAccount {
    private final int accountNumber;
    private double balance;

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
