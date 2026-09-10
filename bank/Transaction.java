package bank;

import java.time.LocalDate;

public class Transaction {

    private String type;
    private double amount;
    private LocalDate date;
    private double balanceAfter;
    private String transactionID;

    public Transaction(String type, double amount, LocalDate date, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.balanceAfter = balanceAfter;
        this.transactionID = generateTransactionID();
    }

    public String generateTransactionID() {
        String txID = "TXN-" + System.currentTimeMillis();
        return txID;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getTransactionID() {
        return transactionID;
    }

}
