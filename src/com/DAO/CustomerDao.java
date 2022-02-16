package com.DAO;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer c);
    void updateCustomer(Customer c);
    void deleteCustomer(int id);
    Customer getCustomerByUsernameAndPassword(String username, String password);
    List<Customer> getCustomers();

}
