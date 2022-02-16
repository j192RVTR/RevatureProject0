package com.UI;

import com.DAO.*;

public class TransactionListContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        WithdrawDao withdrawDao = WithdrawDaoFactory.getWithdrawDao();
        DepositDao depositDao = DepositDaoFactory.getDepositDao();
        TransferDao transferDao = TransferDaoFactory.getTransferDao();
        System.out.println("Listing transactions:");
        System.out.println("\n----------WITHDRAWS----------\n");
        withdrawDao.getWithdraws().forEach(System.out::println);
        System.out.println("\n----------END WITHDRAWS----------");

        System.out.println("\n----------DEPOSITS----------\n");
        depositDao.getDeposits().forEach(System.out::println);
        System.out.println("\n----------END DEPOSITS----------");

        System.out.println("\n----------TRANSFERS----------\n");
        transferDao.getTransfers().forEach(System.out::println);
        System.out.println("\n----------END TRANSFERS----------\n");

        System.out.println("Enter any character to return to the employee menu.");
        String hold = scanner.nextLine();
        return EmployeeMenuContextFactory.getEmployeeMenuContext();
    }
}
