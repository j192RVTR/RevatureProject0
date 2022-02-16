package com.UI;

import com.DAO.Customer;
import com.DAO.CustomerDao;
import com.DAO.CustomerDaoFactory;

import java.util.List;

public class CustomerSelectionContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        System.out.println("Customer List: ");
        CustomerDao dao = CustomerDaoFactory.getCustomerDao();
        List<Customer> customers = dao.getCustomers();
        if(customers.isEmpty())
        {
            System.out.println("No Customers Found!");
            return returnToEmployee();
        }
        customers.forEach(System.out::println);
        System.out.println("Choose customer by id or -1 to exit:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if(id == -1){
                returnToEmployee();
            }
            Customer customer = customers.stream().filter(c -> c.getId() == id).findFirst().get();
            return new AccountInfoContext(customer, EmployeeMenuContextFactory.getEmployeeMenuContext());

        }
        catch (Exception e){
            System.out.println("Invalid input.");
            return this;
        }
    }
}
