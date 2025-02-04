package ATM_Lab2;

public class Account extends Person {
    private String loginID;
    private String password;
    private double balance;

    public Account(String idCard, String fullName, String gender, String loginID, String password, double balance) {
        super(idCard, fullName, gender);
        this.loginID = loginID;
        this.password = password;
        this.balance = balance;
    }

    public String getLoginID() {
        return loginID;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account targetAccount, double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            return true;
        }
        return false;
    }
}
