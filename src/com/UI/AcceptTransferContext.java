package com.UI;

import com.DAO.Customer;
import com.DAO.Transfer;
import com.DAO.TransferDao;
import com.DAO.TransferDaoFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AcceptTransferContext extends ConsoleContext{

    @Override
    public ConsoleContext eval() {
        TransferDao dao = TransferDaoFactory.getTransferDao();
        Customer c = CustomerMenuContextFactory.getCustomerMenuContext().customer;
        List<Transfer> transfers = dao.getTransfersToCustomer(c);
        if(transfers.isEmpty()){
            System.out.println("No Transfers found!");
            return CustomerMenuContextFactory.getCustomerMenuContext();
        }
        System.out.println("List of open transfers:");
        transfers.forEach(System.out::println);

        //List transfers? from customer
        try {
            System.out.println("Enter a transfer id or -1 to exit.");
            int chosenID = Integer.parseInt(scanner.nextLine());
            if (chosenID == -1) {
                return returnToCustomer();
            }
            Transfer transfer = transfers.stream().filter(t -> t.getId() == chosenID).findFirst().get();
            System.out.println("Chose Transfer: \n" + transfer);
            System.out.println("Press 1 to Accept.");
            System.out.println("Press 2 to Reject.");
            System.out.println("Press anything else to cancel.");
            String choice = scanner.nextLine();
            int value = Integer.parseInt(choice);
            if (value == 1) {
                transfer.setStatus(Transfer.Status.ACCEPTED);
                dao.updateTransfer(transfer);
            }
            if (value == 2) {
                transfer.setStatus(Transfer.Status.REJECTED);
                dao.updateTransfer(transfer);
            }
        }
        catch (NoSuchElementException | NumberFormatException e){
            System.out.println("Input failed.");
            return this;
        }

        return CustomerMenuContextFactory.getCustomerMenuContext();
    }
}
