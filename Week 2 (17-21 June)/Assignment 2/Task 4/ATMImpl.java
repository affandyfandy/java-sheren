import java.util.HashMap;
import java.util.Map;

public class ATMImpl implements ATM {
    private int idATM;
    private Map<Integer, Account> accounts;
    private Map<String, Boolean> users;

    public ATMImpl(int idATM) {
        this.idATM = idATM;
        this.accounts = new HashMap<>();
        this.users = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public boolean withdraw(int accountId, double amount) {
        Account account = accounts.get(accountId);
        return account != null && account.withdraw((amount));
    }

    @Override
    public boolean deposit(int accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    @Override
    public double queryBalance(int accountId) {
        Account account = accounts.get(accountId);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

    @Override
    public boolean login(String userId, String password) {
        // Assume the login is always successful
        users.put(userId, true);
        return true;
    }

    @Override
    public boolean logout(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return true;
        }
        return false;
    }

    public int getIdATM() {
        return idATM;
    }

    public void setIdATM(int idATM) {
        this.idATM = idATM;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<Integer, Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, Boolean> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Boolean> users) {
        this.users = users;
    }
    
}