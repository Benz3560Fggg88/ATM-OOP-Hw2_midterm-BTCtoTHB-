package ATM_Lab2;

import java.util.ArrayList;

public class ATM implements ATMAction {
    public ArrayList<Account> accounts = new ArrayList<>();
    private Manager manager;
    private double btcRate; // อัตราแลกเปลี่ยน BTC -> THB

    // Setter และ Getter สำหรับ BTC Rate
    public void setBtcRate(double btcRate) {
        this.btcRate = btcRate;
    }

    public double getBtcRate() {
        return btcRate;
    }

    // Setter และ Getter สำหรับ Manager
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public void checkBalance(Account account) {
        System.out.println("Current Balance in THB: " + account.getBalance());
        System.out.println("Current Balance in BTC: " + (account.getBalance() / btcRate));
    }

    @Override
    public void withdraw(Account currentAccount, double amount) {
        if (currentAccount.withdraw(amount)) {
            System.out.println("Withdrawn in THB: " + amount);
            System.out.println("Current balance: " + currentAccount.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }

    // ถอนเงินในรูปแบบ BTC
    public void withdrawBTC(Account currentAccount, double btcAmount) {
        double thbAmount = btcAmount * btcRate;
        if (currentAccount.withdraw(thbAmount)) {
            System.out.println("Withdrawn in BTC: " + btcAmount);
            System.out.println("Current balance in THB: " + currentAccount.getBalance());
        } else {
            System.out.println("Withdrawal in BTC failed. Insufficient balance or invalid amount.");
        }
    }

    @Override
    public void deposit(Account currentAccount, double amount) {
        if (amount > 0) {
            currentAccount.deposit(amount);
            System.out.println("Deposited in THB: " + amount);
            System.out.println("Current balance: " + currentAccount.getBalance());
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    // ฝากเงินในรูปแบบ BTC
    public void depositBTC(Account currentAccount, double btcAmount) {
        double thbAmount = btcAmount * btcRate;
        if (btcAmount > 0) {
            currentAccount.deposit(thbAmount);
            System.out.println("Deposited in BTC: " + btcAmount);
            System.out.println("Current balance in THB: " + currentAccount.getBalance());
        } else {
            System.out.println("Deposit amount in BTC must be greater than 0.");
        }
    }

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, double amount) {
        if (sourceAccount.transfer(targetAccount, amount)) {
            System.out.println("Transfer successful: " + amount + " THB to " + targetAccount.getFullName());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }
}
