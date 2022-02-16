package com.DAO;

import java.util.List;

public interface DepositDao {
    void insertDeposit(Deposit deposit);
    List<Deposit> getDeposits();
}
