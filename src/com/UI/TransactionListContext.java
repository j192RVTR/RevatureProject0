package com.UI;

import com.DAO.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TransactionListContext extends ConsoleContext{
    @Override
    public ConsoleContext eval() {
        WithdrawDao withdrawDao = WithdrawDaoFactory.getWithdrawDao();
        DepositDao depositDao = DepositDaoFactory.getDepositDao();
        TransferDao transferDao = TransferDaoFactory.getTransferDao();
        List<Withdraw> withdraws = withdrawDao.getWithdraws();
        List<Deposit> deposits = depositDao.getDeposits();
        List<Transfer> transfers = transferDao.getTransfers();
        printTransactions(withdraws, deposits, transfers);

        System.out.println("Press 1 to write the above to a file or any other input to return to the employee menu.");
        String hold = scanner.nextLine();
        try{
            int in = Integer.parseInt(hold);
            if (in == 1){
                PrintStream console = new PrintStream(System.out);
                System.setOut(new PrintStream("access.log"));
                printTransactions(withdraws, deposits, transfers);
                System.out.flush();
                System.setOut(console);
            }
        }
        catch (IOException ioe){
            System.out.println("Failed to write to file.");
        }
        catch (NumberFormatException nfe){

        }
        return EmployeeMenuContextFactory.getEmployeeMenuContext();
    }

    private void printTransactions(List<Withdraw> withdraws, List<Deposit> deposits, List<Transfer> transfers) {
        System.out.println("Listing transactions:");
        System.out.println("\n----------WITHDRAWS----------\n");
        withdraws.forEach(System.out::println);
        System.out.println("\n----------END WITHDRAWS----------");

        System.out.println("\n----------DEPOSITS----------\n");
        deposits.forEach(System.out::println);
        System.out.println("\n----------END DEPOSITS----------");

        System.out.println("\n----------TRANSFERS----------\n");
        transfers.forEach(System.out::println);
        System.out.println("\n----------END TRANSFERS----------\n");
    }
}
