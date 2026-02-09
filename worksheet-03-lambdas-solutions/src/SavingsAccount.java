public class SavingsAccount extends BankAccount {
    public SavingsAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public String toString() {
        return "SavingsAccount [accountNumber=" + getAccountNumber() + ", balance=" + getBalance() + "]";
    }
}
