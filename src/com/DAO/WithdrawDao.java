package com.DAO;

import java.util.List;

public interface WithdrawDao {
    void insertWithdraw(Withdraw withdraw);
    List<Withdraw> getWithdraws();
}
