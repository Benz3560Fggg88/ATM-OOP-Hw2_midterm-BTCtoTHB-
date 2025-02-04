package ATM_Lab2;

public interface ATMAction {
    void checkBalance(Account account);
    void withdraw(Account account, double amount);
    void deposit(Account account, double amount);
    void transfer(Account sourceAccount, Account targetAccount, double amount);
}
