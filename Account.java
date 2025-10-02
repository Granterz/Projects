// Class representing a bank account
class Account {
    private static int nextAccountNumber = 10000000; // Starting account number
    private final int accountNumber; // Unique account number
    private String accountName; // Name of the account holder
    private String accountType; // Type of account (e.g., savings, checking)
    private String address; // Address of the account holder
    private double accountBalance; // Current balance in the account
    private String sortCode; // Sort code of the bank
    private double overdraftLimit; // Maximum negative balance allowed

    // Constructor with full details
    public Account(String accountName, String accountType, String address, double accountBalance, String sortCode, double overdraftLimit) {
        this.accountNumber = nextAccountNumber++; // Assign unique account number
        this.accountName = accountName;
        this.accountType = accountType;
        this.address = address;
        this.accountBalance = accountBalance;
        this.sortCode = sortCode;
        this.overdraftLimit = overdraftLimit;
    }

    // Constructor without sort code and overdraft limit
    public Account(String accountName, String accountType, String address, double accountBalance) {
        this(accountName, accountType, address, accountBalance, "", 0); // Assuming sort code and overdraft limit not provided
    }

    // Getters and setters for account details
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Deposit method to add funds to the account
    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("Deposit successful. New balance: £" + accountBalance);
    }

    // Withdraw method to deduct funds from the account
    public void withdraw(double amount) {
        if (accountBalance - amount >= -overdraftLimit) {
            accountBalance -= amount;
            System.out.println("Withdrawal successful. New balance: £" + accountBalance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Method to print account details
    public void printDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Name: " + accountName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Address: " + address);
        System.out.println("Account Balance: £" + accountBalance);
        System.out.println("Sort Code: " + sortCode);
        System.out.println("Overdraft Limit: £" + overdraftLimit);
    }
}
