package bank;

import java.time.LocalDate;

public class Savings extends Account {

    private double interestRate;
    private int withdrawalLimit;
    private int depositLimit;
    private Integer accountTier;

    public Savings(double acctBalance, int accountTier) {
        super(acctBalance, "Savings");
        this.interestRate = 0.05;
        this.accountTier = accountTier;
    }

    @Override
    public void deposit(double amount) {
        depositIntoSavings(amount);
    }

    @Override
    public void withdraw(double amount) {
        withdrawFromSavings(amount);
    }

    private void depositIntoSavings(double amount) {
        int dLimit = buildDepositLimit();
        if (amount > dLimit) {
            System.out.println("Deposit amount exceeds limit");
            return;
        }

        String acctNumber = super.getAccountNumber();
        updateAccountBalance(amount);
        LocalDate date = LocalDate.now();
        double newBalance = super.getBalance();
        newTransaction("Credit", amount, date, newBalance);

        System.out.printf("""
        Deposit Successful
        ----------------------
        Account Number : %s
        Amount Deposited: $%.2f
        New Balance     : $%.2f
        Date            : %s
        """, acctNumber, amount, newBalance, date);
    }

    private void withdrawFromSavings(double amount) {
        int wLimit = buildWithdrawalLimit();
        if (amount > wLimit) {
            System.out.println("Withdrawal amount exceeds limit");
            return;
        }
        double balance = super.getBalance();
        String acctNumber = super.getAccountNumber();
        if (balance < amount) {
            System.out.println("Insufficient funds");
            return;
        }
        deductAccountBalance(amount);
        LocalDate date = LocalDate.now();
        double newBalance = super.getBalance();
        newTransaction("Debit", amount, date, newBalance);
        System.out.printf("""
        Withdrawal Successful
        ----------------------
        Account Number : %s
        Amount Withdrawn: $%.2f
        New Balance     : $%.2f
        Date            : %s
        """, acctNumber, amount, newBalance, date);
    }

    public int buildWithdrawalLimit() {
        int tier = getAccountTier();
        switch (tier) {
            case 1:
                this.withdrawalLimit = 200_000;
                break;
            case 2:
                this.withdrawalLimit = 500_000;
                break;
            default:
                this.withdrawalLimit = 1_000_000;
                break;
        }
        return withdrawalLimit;
    }

    public int buildDepositLimit() {
        int tier = getAccountTier();
        switch (tier) {
            case 1:
                this.depositLimit = 200_000;
                break;
            case 2:
                this.depositLimit = 500_000;
                break;
            default:
                this.depositLimit = 1_000_000;
                break;
        }
        return depositLimit;
    }

    public int getAccountTier() {
        return accountTier;
    }

}
