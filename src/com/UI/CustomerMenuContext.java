package com.UI;

import com.DAO.CustomerDao;
import com.DAO.Customer;
import com.DAO.CustomerDaoFactory;

import java.util.Arrays;

public class CustomerMenuContext extends ConsoleContext{

    Customer customer;

    public CustomerMenuContext(){
        super();
    }

    public CustomerMenuContext(Customer customer){
        this.customer = customer;
        CustomerMenuContextFactory.setCustomerMenuContext(this);
    }

    @Override
    public ConsoleContext eval() {
        System.out.println("\nYou are in the customer main menu.");
        System.out.println("Press 1 to apply for a new account.");
        System.out.println("Press 2 to view account balances.");
        System.out.println("Press 3 to make a deposit or withdrawal.");
        System.out.println("Press 4 to post a money transfer to another account.");
        System.out.println("Press 5 to accept a money transfer from another account.");
        System.out.println("Press 6 to logout.");

        return ConsoleContext.parseNextContextFromScanner(Arrays.asList(
                this,
                new ApplicationContext(),
                new AccountInfoContext(),
                new TransactionContext(),
                new PostTransferContext(),
                new AcceptTransferContext(),
                new StartContext()

        ));
    }

    @Override
    public boolean validate() {
        if(customer != null){
            return true;
        }
        CustomerDao dao = CustomerDaoFactory.getCustomerDao();
        System.out.println("Enter username.");
        String username = scanner.nextLine();
        System.out.println("Enter password.");
        String password = scanner.nextLine();
        Customer c = dao.getCustomerByUsernameAndPassword(username, password);
        if(c == null){
            return false;
        }
        customer = c;
        CustomerMenuContextFactory.setCustomerMenuContext(this);
        System.out.println("Welcome " + customer.getName() + "!");
        return true;
    }
}
