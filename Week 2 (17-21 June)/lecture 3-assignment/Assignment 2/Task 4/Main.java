public class Main {
    public static void main(String[] args) {
        SavingAccount saving = new SavingAccount(1, 1000.0, 5);
        CurrentAccount current = new CurrentAccount(2, 2000.0, 500.0);
        
        ATMImpl atm = new ATMImpl(123);
        atm.addAccount(saving);
        atm.addAccount(current);

        atm.login("user1", "123");

        atm.deposit(1, 200.0);
        System.out.println("Savings account balance: " + atm.queryBalance(1));
        
        atm.withdraw(2, 2200.0);
        System.out.println("Current account balance: " + atm.queryBalance(2));

        saving.addInterest();
        System.out.println("Savings account balance after interest: " + atm.queryBalance(1));
        
        atm.logout("user1");
    }
}
