package bank;

import java.util.LinkedList;

public class Customer {

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNo;
    private LinkedList<Account> myAccounts;

    public Customer(String customerName, String customerAddress, String customerPhoneNo) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNo = customerPhoneNo;
        this.customerID = generateCustomerID();
        this.myAccounts = new LinkedList<>();
    }

    public Account findAccount(Account acct) {
        for (Account account : myAccounts) {
            if (account.getAccountNumber().equalsIgnoreCase(acct.getAccountNumber())) {
                return account;
            }
        }

        return null;
    }

    public void addAccount(Account acct) {
        if (findAccount(acct) == null) {
            myAccounts.add(acct);
            System.out.println("Account added successfully");
        } else {
            System.out.println("Account already exists");
        }
    }

    public void withdraw(Account account, double amount) {
        if (findAccount(account) == null) {
            System.out.println("Account not found");
            return;
        }

        if (account instanceof Savings savings) {
            savings.withdrawFromSavings(amount);

        } else if (account instanceof Current current) {
            current.withdrawFromCurrent(amount);
        }

    }

    public void deposit(Account account, double amount) {
        if (findAccount(account) == null) {
            System.out.println("Account not found");
            return;
        }

        if (account instanceof Savings savings) {
            savings.depositIntoSavings(amount);

        } else if (account instanceof Current current) {
            current.depositIntoCurrent(amount);
        }
    }

    public void printCustomer() {
        System.out.println("Customer ID : " + customerID);
        System.out.println("Name        : " + customerName);
        System.out.println("Address     : " + customerAddress);
        System.out.println("Phone       : " + customerPhoneNo);
        System.out.println("Accounts    : " + myAccounts.size());

        int count = 1;
        for (Account account : myAccounts) {
            System.out.println(count++ + ".");
            account.printAcct();
        }
    }

    public String generateCustomerID() {
        String txID = "USER-" + System.currentTimeMillis();
        return txID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

}
