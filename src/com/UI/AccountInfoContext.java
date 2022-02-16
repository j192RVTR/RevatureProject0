package com.UI;

import com.DAO.Account;
import com.DAO.AccountDao;
import com.DAO.AccountDaoFactory;
import com.DAO.Customer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AccountInfoContext extends ConsoleContext{

    Customer customer;
    ConsoleContext nextContext;

    public AccountInfoContext(){
        customer = CustomerMenuContextFactory.getCustomerMenuContext().customer;
        nextContext = CustomerMenuContextFactory.getCustomerMenuContext();
    }
    public AccountInfoContext(Customer customer, ConsoleContext nextContext){
        this.customer = customer;
        this.nextContext = nextContext;
    }

    @Override
    public ConsoleContext eval() {
        AccountDao dao = AccountDaoFactory.getAccountDao();
        List<Account> accountList = dao.getAccountsForCustomer(customer);
        if(accountList.isEmpty()){
            System.out.println("No Accounts found!");
            return nextContext;
        }
        //Get Accounts from customer
        //List account ids
        accountList.forEach(System.out::println);
        System.out.println("Enter account number for account or -1 to exit.");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (id == -1) {
                return nextContext;
            }
            //Show balance of account?
            Account account = accountList.stream().filter(a -> a.getId() == id).findFirst().get();
            System.out.println("Account Balance: " + account.getAmount());
        }
        catch (NumberFormatException | NoSuchElementException e){
            System.out.println("Invalid input.");
            return this;
        }
        return nextContext;
    }
}
