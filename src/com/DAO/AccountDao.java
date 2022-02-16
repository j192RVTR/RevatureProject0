package com.DAO;

import java.util.List;

public interface AccountDao {
    void submitAccountApplication(Customer customer, double startingBalance);
    void updateAccount(Account account);
    List<Account> getAccountsForCustomer(Customer customer);
    List<Account> getApprovedAccountsForCustomer(Customer customer);
    List<Account> getPendingAccounts();
}
