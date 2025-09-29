import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Transaction {
    private String type;
    private double amount;
    private double balanceAfter;
    private LocalDateTime timestamp;
    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: %.2f | Balance: %.2f", 
            timestamp.format(formatter), type, amount, balanceAfter);
    }
}

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<Transaction> transactionHistory;
    public Account(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }
    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this(accountNumber, accountHolderName);
        if (initialBalance > 0) {
            this.balance = initialBalance;
            transactionHistory.add(new Transaction("INITIAL_DEPOSIT", initialBalance, balance));
        }
    }
    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount, balance));
        return true;
    }
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        transactionHistory.add(new Transaction("WITHDRAWAL", amount, balance));
        return true;
    }
    public double getBalance() { return balance; }
    public void displayAccountInfo() {
        System.out.println("Account: " + accountNumber + ", Holder: " + accountHolderName + ", Balance: " + balance);
    }
    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) System.out.println("No transactions found.");
        else for (Transaction t : transactionHistory) System.out.println(t);
    }
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
}

public class BankAccount {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, Account> accounts = new HashMap<>();
            while (true) {
                System.out.println("Bank Menu:");
                System.out.println("1. Open account");
                System.out.println("2. Deposit money");
                System.out.println("3. Withdraw money");
                System.out.println("4. Check balance");
                System.out.println("5. Show transaction history");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        if (accounts.containsKey(accNum)) {
                            System.out.println("Account number already exists.");
                            break;
                        }
                        System.out.print("Enter account holder name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter initial deposit : ");
                        double init = scanner.nextDouble();
                        scanner.nextLine();
                        Account acc = new Account(accNum, name, init);
                        accounts.put(accNum, acc);
                        System.out.println("Account created!");
                        acc.displayAccountInfo();
                        break;
                    case 2:
                        System.out.print("Enter account number: ");
                        accNum = scanner.nextLine();
                        if (!accounts.containsKey(accNum)) {
                            System.out.println("Account not found.");
                            break;
                        }
                        System.out.print("Enter amount to deposit: ");
                        double dep = scanner.nextDouble();
                        scanner.nextLine();
                        if (accounts.get(accNum).deposit(dep))
                            System.out.println("Deposit successful.");
                        else
                            System.out.println("Invalid amount.");
                        break;
                    case 3:
                        System.out.print("Enter account number: ");
                        accNum = scanner.nextLine();
                        if (!accounts.containsKey(accNum)) {
                            System.out.println("Account not found.");
                            break;
                        }
                        System.out.print("Enter amount to withdraw: ");
                        double wd = scanner.nextDouble();
                        scanner.nextLine();
                        if (accounts.get(accNum).withdraw(wd))
                            System.out.println("Withdrawal successful.");
                        else
                            System.out.println("Invalid amount or insufficient balance.");
                        break;
                    case 4:
                        System.out.print("Enter account number: ");
                        accNum = scanner.nextLine();
                        if (!accounts.containsKey(accNum)) {
                            System.out.println("Account not found.");
                            break;
                        }
                        System.out.println("Balance: " + accounts.get(accNum).getBalance());
                        break;
                    case 5:
                        System.out.print("Enter account number: ");
                        accNum = scanner.nextLine();
                        if (!accounts.containsKey(accNum)) {
                            System.out.println("Account not found.");
                            break;
                        }
                        accounts.get(accNum).displayTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the bank application.Closing now.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}