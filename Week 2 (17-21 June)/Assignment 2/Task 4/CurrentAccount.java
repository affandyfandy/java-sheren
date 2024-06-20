public class CurrentAccount implements Account {
    private int accountId;
    private double balance;
    private double overdraftLimit;

    public CurrentAccount(int accountId, double balance, double overdraftLimit) {
        this.accountId = accountId;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public int getAccountId() {
        return accountId;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "Current account";
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}