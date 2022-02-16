package com.UI;

import com.DAO.Account;
import com.DAO.AccountDao;
import com.DAO.AccountDaoFactory;

import java.util.List;
import java.util.Optional;

public class ApplicationDecisionContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        AccountDao dao = AccountDaoFactory.getAccountDao();
        List<Account> pendingAccounts = dao.getPendingAccounts();
        if(pendingAccounts.isEmpty()){
            System.out.println("No pending accounts found!");
            return returnToEmployee();
        }
        pendingAccounts.forEach(System.out::println);
        System.out.println("Enter an account number.");
        try {
            int chosenID = Integer.parseInt(scanner.nextLine());
            Account account = pendingAccounts.stream().filter(a -> a.getId() == chosenID).findFirst().get();
            System.out.println("Chose Account: " + account);
            System.out.println("Press 1 to accept.");
            System.out.println("Press 2 to reject.");
            System.out.println("Press any other number to cancel.");
            int next = Integer.parseInt(scanner.nextLine());
            if (next == 1) {
                account.setStatus(Account.Status.APPROVED);
                account.setApproval_id(EmployeeMenuContextFactory.getEmployeeMenuContext().employee.getId());
                dao.updateAccount(account);
            }

            if (next == 2) {
                account.setApproval_id(EmployeeMenuContextFactory.getEmployeeMenuContext().employee.getId());
                account.setStatus(Account.Status.DENIED);
                dao.updateAccount(account);
            }
        }
        catch(Exception e){
            System.out.println("Error in input!");
            return this;
        }

        return EmployeeMenuContextFactory.getEmployeeMenuContext();
    }
}
