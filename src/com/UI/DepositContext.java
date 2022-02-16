package com.UI;

import com.DAO.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

public class DepositContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        Customer customer = CustomerMenuContextFactory.getCustomerMenuContext().customer;
        AccountDao accountDao = AccountDaoFactory.getAccountDao();
        DepositDao depositDao = DepositDaoFactory.getDepositDao();
        System.out.println("Select account to deposit to:");
        List<Account> accountList = accountDao.getApprovedAccountsForCustomer(customer);
        if(accountList.isEmpty()){
            System.out.println("You have no accounts!");
            return returnToCustomer();
        }
        //Get Accounts from customer
        //List account ids
        accountList.forEach(System.out::println);
        System.out.println("Enter account number for account or -1 to exit.");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if(id == -1)
                return returnToCustomer();
            Account account = accountList.stream().filter(a -> a.getId() == id).findFirst().get();
            System.out.println("Enter deposit amount or -1 to exit.");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount == -1)
                return returnToCustomer();
            Deposit deposit = new Deposit(1, account.getId(), amount, new Timestamp(1), Deposit.Status.PENDING);
            depositDao.insertDeposit(deposit);
        }
        catch (NoSuchElementException | NumberFormatException e){
            System.out.println("Input failed.");
            return this;
        }
        return CustomerMenuContextFactory.getCustomerMenuContext();
    }
}
