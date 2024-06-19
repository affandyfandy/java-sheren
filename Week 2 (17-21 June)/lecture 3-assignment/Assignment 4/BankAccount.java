// Creates a bank account with concurrent deposits and withdrawals using threads.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private Lock lock;

    public BankAccount() {
        balance = 0.0;
        lock = new ReentrantLock();
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Deposit: " + amount);
            System.out.println("Balance after deposit: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Deposit: " + amount);
                System.out.println("Balance after withdrawal: " + balance);
            } else {
                System.out.println("Want to withdraw: ");
                System.err.println("Failed. Insufficient funds");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread depositThread1 = new Thread(() -> account.deposit(2000));
        Thread depositThread2 = new Thread(() -> account.deposit(500));
        Thread withdrawalThread1 = new Thread (() -> account.withdraw(200));
        Thread withdrawalThread2 = new Thread (() -> account.withdraw(50));

        depositThread1.start();
        depositThread2.start();
        withdrawalThread1.start();
        withdrawalThread2.start();
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
