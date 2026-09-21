import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber;
    private final String DATA_FILE = "accounts.txt";

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1001;
        loadAccounts();
    }

    public Account createAccount(String holderName, String password, String accountType, double initialDeposit) {
        Account acc = new Account(nextAccountNumber, holderName, password, accountType, initialDeposit);
        accounts.add(acc);
        nextAccountNumber++;
        saveAccounts();
        return acc;
    }

    public Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    public Account login(int accountNumber, String password) {
        Account acc = findAccount(accountNumber);
        if (acc != null && acc.getPassword().equals(password)) {
            return acc;
        }
        return null;
    }

    public boolean transfer(int fromAccNo, int toAccNo, double amount) {
        Account from = findAccount(fromAccNo);
        Account to = findAccount(toAccNo);

        if (from == null || to == null) {
            System.out.println("One or both accounts do not exist.");
            return false;
        }
        if (!from.withdraw(amount)) {
            System.out.println("Insufficient balance for transfer.");
            return false;
        }
        to.deposit(amount);
        saveAccounts();
        return true;
    }

    public void listAllAccounts() {
        for (Account acc : accounts) {
            acc.printDetails();
            System.out.println("-----");
        }
    }

    private void saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Account acc : accounts) {
                writer.println(acc.getAccountNumber() + "," + acc.getHolderName() + "," +
                        acc.getPassword() + "," + acc.getAccountType() + "," + acc.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadAccounts() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int accNo = Integer.parseInt(parts[0]);
                Account acc = new Account(accNo, parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                accounts.add(acc);
                if (accNo >= nextAccountNumber) {
                    nextAccountNumber = accNo + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}