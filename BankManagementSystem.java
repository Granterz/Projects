import java.util.ArrayList;
import java.util.Scanner;

// Class for managing bank accounts
public class BankManagementSystem {
    private static final int MAX_ACCOUNTS = 30;
    private static ArrayList<Account> accounts = new ArrayList<>(); // ArrayList to store accounts

    // Main method to run the bank management system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Display menu and handle user choices
        do {
            displayMenu(); // Display menu options
            System.out.print("Please enter menu choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllAccountDetails(); // Display details of all accounts
                    break;
                case 2:
                    displaySingleAccountDetails(scanner); // Display details of a single account
                    break;
                case 3:
                    addNewAccount(scanner); // Add a new account
                    break;
                case 4:
                    deposit(scanner); // Deposit funds into an account
                    break;
                case 5:
                    withdraw(scanner); // Withdraw funds from an account
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!"); // Exit program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid input
            }
        } while (choice != 6);

        scanner.close(); // Close scanner
    }

    // Display the main menu
    private static void displayMenu() {
        System.out.println("OOP (NI) Bank");
        System.out.println("Account Menu");
        System.out.println("1. Display All Account Details");
        System.out.println("2. Display Single Account Details");
        System.out.println("3. Add New Account");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Exit");
    }

    // Display details of all accounts
    private static void displayAllAccountDetails() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("All Account Details:");
            for (Account account : accounts) {
                account.printDetails(); // Print details of each account
                System.out.println("---------------------");
            }
        }
    }

    // Display details of a single account
    private static void displaySingleAccountDetails(Scanner scanner) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.print("Enter account number: ");
            int accNumber = scanner.nextInt();
            boolean found = false;
            for (Account account : accounts) {
                if (account.getAccountNumber() == accNumber) {
                    account.printDetails(); // Print details of the found account
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Account not found.");
            }
        }
    }

    // Add a new account
    private static void addNewAccount(Scanner scanner) {
        if (accounts.size() >= MAX_ACCOUNTS) {
            System.out.println("Maximum accounts limit reached.");
        } else {
            scanner.nextLine(); // Consume newline character left by previous nextInt()
            System.out.print("Enter account name: ");
            String name = scanner.nextLine().trim();

            while (name.isEmpty()) {
                System.out.println("Account name cannot be empty. Please enter a valid name.");
                System.out.print("Enter account name: ");
                name = scanner.nextLine().trim(); // Read account name again and trim leading/trailing spaces
            }

            // Prompt for account details and validate inputs
            String type;
            do {
                System.out.print("Enter account type (Personal/Business): ");
                type = scanner.nextLine().trim();
                if (!type.equalsIgnoreCase("Personal") && !type.equalsIgnoreCase("Business")) {
                    System.out.println("Invalid account type. Please enter 'Personal' or 'Business'.");
                }
            } while (!type.equalsIgnoreCase("Personal") && !type.equalsIgnoreCase("Business"));

            // Validate and limit address length
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            while (address.length() > 50) {
                System.out.println("Address exceeds 50 characters. Please enter a shorter address.");
                System.out.print("Enter address: ");
                address = scanner.nextLine();
            }

            // Validate initial balance
            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            while (balance < -5000 || balance > 50000) {
                System.out.println("Invalid balance. Balance must be between -5000 and 50000.");
                System.out.print("Enter initial balance: ");
                balance = scanner.nextDouble();
            }

            // Validate sort code format
            System.out.print("Enter sort code: ");
            String sortCode = scanner.next();
            while (!sortCode.matches("\\d{2}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid sort code format. Please enter in the format 11-22-33.");
                System.out.print("Enter sort code: ");
                sortCode = scanner.next();
            }

            // Validate overdraft limit
            System.out.print("Enter overdraft limit: ");
            double overdraft = scanner.nextDouble();
            while (overdraft < 500 || overdraft > 5000) {
                System.out.println("Invalid overdraft limit. Overdraft limit must be between 500 and 5000.");
                System.out.print("Enter overdraft limit: ");
                overdraft = scanner.nextDouble();
            }

            // Create and add new account to the list
            Account newAccount = new Account(name, type, address, balance, sortCode, overdraft);
            accounts.add(newAccount);
            System.out.println("Account added successfully.");
        }
    }

    // Deposit funds into an account
    private static void deposit(Scanner scanner) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.print("Enter account number: ");
            int accNumber = scanner.nextInt();
            boolean found = false;
            for (Account account : accounts) {
                if (account.getAccountNumber() == accNumber) {
                    System.out.print("Enter deposit amount: £");
                    double amount = scanner.nextDouble();
                    account.deposit(amount); // Deposit amount into the found account
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Account not found.");
            }
        }
    }

    // Withdraw funds from an account
    private static void withdraw(Scanner scanner) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.print("Enter account number: ");
            int accNumber = scanner.nextInt();
            boolean found = false;
            for (Account account : accounts) {
                if (account.getAccountNumber() == accNumber) {
                    System.out.print("Enter withdrawal amount: £");
                    double amount = scanner.nextDouble();
                    account.withdraw(amount); // Withdraw amount from the found account
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Account not found.");
            }
        }
    }
}
