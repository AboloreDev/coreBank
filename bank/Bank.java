package bank;

import java.util.LinkedList;

public class Bank {

    private String name;
    private String address;
    private String valuation;
    private LinkedList<Customer> myCustomers;

    public Bank() {
        this.name = "Afrexim Bank";
        this.address = "Lagos, Nigeria";
        this.valuation = "NGN 1.5 Billion";
        this.myCustomers = new LinkedList<>();
    }

    public Customer findCustomer(Customer customer) {
        for (Customer c : myCustomers) {
            if (c.getCustomerID().equalsIgnoreCase(customer.getCustomerID())) {
                return c;
            }
        }
        return null;
    }

    public Customer findCustomerByID(String customerID) {
        for (Customer c : myCustomers) {
            if (c.getCustomerID().equalsIgnoreCase(customerID)) {
                return c;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        if (findCustomer(customer) == null) {
            myCustomers.add(customer);
            System.out.println("Customer added successfully");
        } else {
            System.out.println("Customer already exists");
        }
    }

    public void withdraw(String customerID, Account account, double amount) {
        Customer validCustomer = findCustomerByID(customerID);
        if (validCustomer == null) {
            System.out.println("Customer not found");
            return;
        }
        validCustomer.withdraw(account, amount);
    }

    public void deposit(String customerID, Account account, double amount) {
        Customer validCustomer = findCustomerByID(customerID);
        if (validCustomer == null) {
            System.out.println("Customer not found");
            return;
        }
        validCustomer.deposit(account, amount);
    }

    public void printBank() {
        System.out.println("Bank Name : " + name);
        System.out.println("Address   : " + address);
        System.out.println("Valuation : " + valuation);
        System.out.println("Customers : " + myCustomers.size());

        int count = 1;
        for (Customer customer : myCustomers) {
            System.out.println(count++ + ". Customer:");
            customer.printCustomer();
        }
    }

}
