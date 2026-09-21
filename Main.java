import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== ONLINE BANKING SYSTEM =====");
            System.out.println("1. Register (Create Account)");
            System.out.println("2. Login & Manage Account");
            System.out.println("3. View All Accounts");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                System.out.print("Enter your name: ");
                String name = sc.nextLine();
                System.out.print("Set a password: ");
                String password = sc.nextLine();
                System.out.print("Account type (Savings/Current): ");
                String type = sc.nextLine();
                System.out.print("Initial deposit: ");
                double initial = Double.parseDouble(sc.nextLine());
                Account newAcc = bank.createAccount(name, password, type, initial);
                System.out.println("Account created! Your account number is: " + newAcc.getAccountNumber());

            } else if (choice == 2) {
                System.out.print("Enter account number: ");
                int accNo = Integer.parseInt(sc.nextLine());
                System.out.print("Enter password: ");
                String pass = sc.nextLine();
                Account acc = bank.login(accNo, pass);

                if (acc == null) {
                    System.out.println("Invalid account number or password.");
                    continue;
                }

                boolean loggedIn = true;
                while (loggedIn) {
                    System.out.println("\n-- Logged in as " + acc.getHolderName() + " --");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Transfer");
                    System.out.println("4. View Balance");
                    System.out.println("5. Transaction History");
                    System.out.println("6. Logout");
                    System.out.print("Choose an option: ");
                    int action = Integer.parseInt(sc.nextLine());

                    switch (action) {
                        case 1:
                            System.out.print("Amount to deposit: ");
                            acc.deposit(Double.parseDouble(sc.nextLine()));
                            System.out.println("New balance: " + acc.getBalance());
                            break;
                        case 2:
                            System.out.print("Amount to withdraw: ");
                            double amt = Double.parseDouble(sc.nextLine());
                            if (acc.withdraw(amt)) {
                                System.out.println("New balance: " + acc.getBalance());
                            } else {
                                System.out.println("Insufficient balance.");
                            }
                            break;
                        case 3:
                            System.out.print("Recipient account number: ");
                            int toAcc = Integer.parseInt(sc.nextLine());
                            System.out.print("Amount to transfer: ");
                            double transferAmt = Double.parseDouble(sc.nextLine());
                            if (bank.transfer(acc.getAccountNumber(), toAcc, transferAmt)) {
                                System.out.println("Transfer successful.");
                            }
                            break;
                        case 4:
                            acc.printDetails();
                            break;
                        case 5:
                            System.out.println("Transaction History:");
                            for (String t : acc.getTransactionHistory()) {
                                System.out.println(t);
                            }
                            break;
                        case 6:
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }

            } else if (choice == 3) {
                bank.listAllAccounts();
            } else if (choice == 4) {
                running = false;
                System.out.println("Thank you for using our banking system!");
            } else {
                System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}