public class SavingAccount implements Account {
    private int accountId;
    private double balance;
    private double interestRate;

    public SavingAccount(int accountId, double balance, double interestRate) {
        this.accountId = accountId;
        this.balance = balance;
        this.interestRate = interestRate;
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
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void addInterest() {
        balance += Account.calcInterest(balance, interestRate);
    }

    @Override
    public String getAccountType() {
        return "Saving account";
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
