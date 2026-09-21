import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;
    private String holderName;
    private String password;
    private String accountType;
    private double balance;
    private List<String> transactionHistory;

    public Account(int accountNumber, String holderName, String password, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.password = password;
        this.accountType = accountType;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public String getPassword() { return password; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public List<String> getTransactionHistory() { return transactionHistory; }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount + " | Balance: " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdraw: -" + amount + " | Balance: " + balance);
        return true;
    }

    public void printDetails() {
        System.out.println("Account No: " + accountNumber);
        System.out.println("Holder: " + holderName);
        System.out.println("Type: " + accountType);
        System.out.println("Balance: " + balance);
    }
}