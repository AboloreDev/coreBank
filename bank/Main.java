package bank;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        Account acct = new Savings(0.0, 1);
        Account acct2 = new Current(0.0, 1);

        Customer customer = new Customer("John", "USA", "9876543210");

        bank.addCustomer(customer);
        customer.addAccount(acct);
        customer.addAccount(acct2);

        System.out.println(acct.getAccountNumber());
        System.out.println(customer.getCustomerID());

        bank.deposit(customer.getCustomerID(), acct, 20_000);
        bank.withdraw(customer.getCustomerID(), acct, 10_000);

        bank.deposit(customer.getCustomerID(), acct2, 20_000);
        bank.withdraw(customer.getCustomerID(), acct2, 25_000);

        bank.printBank();
        acct.printTransactionHistory();
        acct2.printTransactionHistory();
    }

}
