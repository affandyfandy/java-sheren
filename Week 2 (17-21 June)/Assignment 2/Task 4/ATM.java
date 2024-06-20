public interface ATM {
    boolean withdraw(int accountId, double amount);
    boolean deposit(int accountId, double amount);
    double queryBalance(int accountId);
    boolean login(String userId, String password);
    boolean logout(String userId);
}