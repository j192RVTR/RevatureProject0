package com.DAO;

import java.util.List;

public interface TransferDao {
    void insertTransfer(Transfer transfer);
    void updateTransfer(Transfer transfer);
    List<Transfer> getTransfersToCustomer(Customer customer);
    List<Transfer> getTransfers();
}
