package com.UI;

import com.DAO.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PostTransferContext extends ConsoleContext{

    @Override
    public ConsoleContext eval() {
        Customer customer = CustomerMenuContextFactory.getCustomerMenuContext().customer;
        AccountDao dao = AccountDaoFactory.getAccountDao();
        TransferDao transferDao = TransferDaoFactory.getTransferDao();
        List<Account> accountList = dao.getApprovedAccountsForCustomer(customer);
        if(accountList.isEmpty()){
            System.out.println("You have no accounts!");
            return returnToCustomer();
        }
        //Get Accounts from customer
        //List account ids
        accountList.forEach(System.out::println);
        System.out.println("Enter account number for account.");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            //Show balance of account?
            Account account = accountList.stream().filter(a -> a.getId() == id).findFirst().get();
            System.out.println("Enter account number that is being transferred to.");
            int id2 = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter amount of transfer.");
            double amount = Double.parseDouble(scanner.nextLine());
            Transfer transfer = new Transfer(1, account.getId(), id2, Transfer.Status.OPEN, amount, new Timestamp(1));
            transferDao.insertTransfer(transfer);
        }
        catch (NoSuchElementException | NumberFormatException e){
            System.out.println("Input failed.");
            return this;
        }
        return CustomerMenuContextFactory.getCustomerMenuContext();
    }
}
