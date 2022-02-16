package com.UI;

import com.DAO.Customer;
import com.DAO.CustomerDao;
import com.DAO.CustomerDaoFactory;

public class RegistrationContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        System.out.println("Enter username.");
        String username = scanner.nextLine();
        System.out.println("Enter password.");
        String password = scanner.nextLine();
        System.out.println("Enter full name.");
        String name = scanner.nextLine();
        CustomerDao dao = CustomerDaoFactory.getCustomerDao();
        Customer customer = dao.addCustomer(new Customer(1, username, password, name));
        if(customer == null){
            System.out.println("Username is already taken!");
            return new StartContext();
        }
        return new CustomerMenuContext(customer);
    }
}
