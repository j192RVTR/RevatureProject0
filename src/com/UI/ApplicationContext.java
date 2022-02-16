package com.UI;

import com.DAO.Account;
import com.DAO.AccountDao;
import com.DAO.AccountDaoFactory;
import com.DAO.Customer;

public class ApplicationContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        Customer customer = CustomerMenuContextFactory.getCustomerMenuContext().customer;
        AccountDao dao = AccountDaoFactory.getAccountDao();
        System.out.println("Enter application starting balance or -1 to exit:");
        try {
            double start = Double.parseDouble(scanner.nextLine());
            if (start == -1) {
                return returnToCustomer();
            }
            dao.submitAccountApplication(customer, start);
        }
        catch (NumberFormatException numberFormatException){
            System.out.println("Invalid input.");
            return this;
        }
        return CustomerMenuContextFactory.getCustomerMenuContext();
    }
}
