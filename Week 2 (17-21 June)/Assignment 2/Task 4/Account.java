public interface Account {
    int getAccountId();
    double getBalance();
    void deposit(double amount);
    boolean withdraw(double amount);

    default String getAccountType() {
        return "Generic account";
    }

    static double calcInterest(double balance, double rate) {
        return balance * rate / 100;
    }
}

