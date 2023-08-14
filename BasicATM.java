import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BasicATM {
    private static Map<String, String> userAccounts = new HashMap<>();
    private static String currentUser;
    private static int balance = 0;

    public static void main(String[] args) {
        userAccounts.put("user1", "1234"); // Replace with real user credentials
        userAccounts.put("user2", "5678");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (validateUser(userId, pin)) {
            currentUser = userId;
            System.out.println("Login successful!");
            displayMenu(scanner);
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }

        scanner.close();
    }

    private static boolean validateUser(String userId, String pin) {
        return userAccounts.containsKey(userId) && userAccounts.get(userId).equals(pin);
    }

    private static void displayMenu(Scanner scanner) {
        int option;

        do {
            System.out.println("\nWelcome, " + currentUser + "!");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private static void checkBalance() {
        System.out.println("Your balance: $" + balance);
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        int amount = scanner.nextInt();
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        int amount = scanner.nextInt();
        balance += amount;
        System.out.println("Deposit successful. Current balance: $" + balance);
    }

    private static void transfer(Scanner scanner) {
        System.out.print("Enter the user ID to transfer: ");
        String targetUser = scanner.next();
        System.out.print("Enter the amount to transfer: $");
        int amount = scanner.nextInt();

        if (amount <= balance) {
            balance -= amount;
            System.out.println("Transfer to " + targetUser + " successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}
