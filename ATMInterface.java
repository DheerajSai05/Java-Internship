import java.util.Scanner;
import java.util.HashMap;

class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<Integer, UserAccount> accounts = new HashMap<>();
    private static UserAccount currentAccount = null;

    public static void main(String[] args) {
        // Sample user accounts
        accounts.put(12345, new UserAccount(12345, 2905, 5000));

        System.out.println("Welcome to the ATM!!");

        // Login
        if (login()) {
            boolean quit = false;
            while (!quit) {
                switch (displayMenu()) {
                    case 1:
                        currentAccount.viewTransactionHistory();
                        break;
                    case 2:
                        currentAccount.withdraw(scanner);
                        break;
                    case 3:
                        currentAccount.deposit(scanner);
                        break;
                    case 4:
                        currentAccount.transfer(scanner, accounts);
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Thank You ");
                        System.out.println("VISIT AGAIN!! ");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed.");
        }
    }

    private static boolean login() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        if (accounts.containsKey(userId) && accounts.get(userId).validatePin(pin)) {
            currentAccount = accounts.get(userId);
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid user ID or PIN.");
            return false;
        }
    }

    private static int displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Select an option: ");
        return scanner.nextInt();
    }
}

class UserAccount {
    private int userId;
    private int pin;
    private double balance;
    private String transactionHistory = "";

    public UserAccount(int userId, int pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = "Account Created. Initial Balance: $" + balance + "\n";
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    public void viewTransactionHistory() {
        System.out.println("\nTransaction History:");
        System.out.println(transactionHistory);
    }

    public void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            transactionHistory += "Withdrawn: $" + amount + ". Remaining Balance: $" + balance + "\n";
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        balance += amount;
        System.out.println("Deposited: $" + amount);
        transactionHistory += "Deposited: $" + amount + ". New Balance: $" + balance + "\n";
    }

    public void transfer(Scanner scanner, HashMap<Integer, UserAccount> accounts) {
        System.out.print("Enter recipient User ID: ");
        int recipientId = scanner.nextInt();
        if (accounts.containsKey(recipientId)) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            if (amount <= balance) {
                balance -= amount;
                accounts.get(recipientId).balance += amount;
                System.out.println("Transferred: $" + amount + " to User ID: " + recipientId);
                transactionHistory += "Transferred: $" + amount + " to User ID: " + recipientId
                        + ". Remaining Balance: $" + balance + "\n";
                accounts.get(recipientId).transactionHistory += "Received: $" + amount + " from User ID: " + userId
                        + ". New Balance: $" + accounts.get(recipientId).balance + "\n";
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Recipient account not found.");
        }
    }
}
