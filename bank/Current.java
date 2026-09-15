package bank;

import java.time.LocalDate;

public class Current extends Account {

    private int accountTier;
    private int overdraftLimit;
    private double interestRate;

    public Current(double acctBalance, int accountTier) {
        super(acctBalance, "Current");
        this.accountTier = accountTier;
        this.interestRate = 0.05;
    }

    @Override
    public void deposit(double amount) {
        depositIntoCurrent(amount);
    }

    @Override
    public void withdraw(double amount) {
        withdrawFromCurrent(amount);
    }

    private void withdrawFromCurrent(double amount) {
        int oLimit = buildOverdraftLimit();
        double balance = super.getBalance();
        String acctNumber = super.getAccountNumber();
        LocalDate date = LocalDate.now();
        if (balance + oLimit < amount) {
            System.out.println("Withdrawal exceeds available overdraft limit");
            return;
        }

        if (amount > balance) {
            // Dipping into overdraft
            double overdrawnAmount = amount - balance;
            double interestAmount = overdraftInterestRate(overdrawnAmount);
            double totalOwed = overdrawnAmount + interestAmount;

            balance = 0 - totalOwed;
            setBalance(balance);
            double newBalance = balance;
            newTransaction("Debit", amount, date, newBalance);

            System.out.printf(
                    "Withdrawal exceeded balance — overdraft applied with interest.%n"
                    + "New balance for %s = $%,.2f%n",
                    acctNumber, newBalance
            );
            return;
        }

        deductAccountBalance(amount);
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

    private void depositIntoCurrent(double amount) {
        String acctNumber = super.getAccountNumber();
        double balance = super.getBalance();
        LocalDate date = LocalDate.now();

        updateAccountBalance(amount);
        double newBalance = super.getBalance();
        newTransaction("Credit", balance, date, newBalance);

        System.out.printf("""
            Deposit Successful
            ----------------------
            Account Number : %s
            Amount Deposited: $%.2f
            New Balance     : $%.2f
            Date            : %s
            """, acctNumber, balance, newBalance, date);
    }

    public int buildOverdraftLimit() {
        int tier = getAccountTier();

        switch (tier) {
            case 1:
                this.overdraftLimit = 50_000;
                break;
            case 2:
                this.overdraftLimit = 100_000;
                break;
            default:
                this.overdraftLimit = 200_000;
                break;
        }
        return overdraftLimit;
    }

    public double overdraftInterestRate(double remAmount) {
        double interestAmount = remAmount + (remAmount * interestRate);
        return interestAmount;
    }

    public int getAccountTier() {
        return accountTier;
    }

}
