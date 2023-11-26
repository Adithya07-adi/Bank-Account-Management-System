import java.util.ArrayList;
import java.util.List;

// Base class for Bank Account
class Account {
    private int accountNumber;
    private double balance;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

// Subclass for Savings Account
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accountNumber, double interestRate) {
        super(accountNumber);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("Interest applied. New balance: $" + getBalance());
    }
}

// Class for Customer
class Customer {
    private int customerId;
    private String name;
    private List<Account> accounts;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Customer Name: " + name);
        System.out.println("Accounts:");
        for (Account account : accounts) {
            account.displayInfo();
        }
        System.out.println("-------------------------");
    }
}

// Class for Bank
class Bank {
    private List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount != null && toAccount != null) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Invalid accounts for transfer.");
        }
    }
}

public class BankAccountManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Customer customer1 = new Customer(1, "John Doe");
        Customer customer2 = new Customer(2, "Jane Smith");

        SavingsAccount account1 = new SavingsAccount(101, 0.02);
        Account account2 = new Account(102);

        customer1.addAccount(account1);
        customer2.addAccount(account2);

        bank.addCustomer(customer1);
        bank.addCustomer(customer2);

        // Deposits and withdrawals
        account1.deposit(1000);
        account1.applyInterest();
        account2.deposit(500);
        account1.withdraw(200);
        account2.withdraw(100);

        // Display customer information
        for (Customer customer : bank.getCustomers()) {
            customer.displayInfo();
        }

        // Transfer between accounts
        bank.transfer(account1, account2, 50);

        // Display updated customer information
        for (Customer customer : bank.getCustomers()) {
            customer.displayInfo();
        }
    }
}
