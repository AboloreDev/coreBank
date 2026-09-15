package bank;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Account {

    private String accountNumber;
    private double balance;
    private LocalDate dateOpened;
    private String accountType;
    private LinkedList<Transaction> transactionHistory;

    public Account(double balance, String accountType) {
        this.accountNumber = generateAccountNumber();
        if (balance < 0.0) {
            this.balance = 0.0;
        } else {
            this.balance = balance;
        }
        if (accountType.equalsIgnoreCase("Savings")
                || accountType.equalsIgnoreCase("Current")) {
            this.accountType = accountType;
        } else {
            System.out.println("Invalid account type");
        }
        this.dateOpened = LocalDate.now();
        this.transactionHistory = new LinkedList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public String getAccountType() {
        return accountType;
    }

    public String generateAccountNumber() {
        String accID = "ACC-" + System.currentTimeMillis();
        return accID;
    }

    public void newTransaction(String type, double amount, LocalDate date, double balanceAfter) {
        Transaction transaction = new Transaction(type, amount, date, balanceAfter);
        transactionHistory.add(transaction);
    }

    public void updateAccountBalance(double amount) {
        balance += amount;
    }

    public void deductAccountBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Cannot deduct more than the current balance");
        }
        balance -= amount;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account Number: " + accountNumber);
        for (Transaction transaction : transactionHistory) {
            System.out.println("Type: " + transaction.getType()
                    + ", Amount: " + transaction.getAmount()
                    + ", Date: " + transaction.getDate()
                    + ", Balance After: " + transaction.getBalanceAfter());
        }
    }

    public void printAcct() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance        : " + balance);
        System.out.println("Date Opened    : " + dateOpened);
        System.out.println("Account Type   : " + accountType);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

}
