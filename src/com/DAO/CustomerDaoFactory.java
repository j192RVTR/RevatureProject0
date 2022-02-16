package com.DAO;

public class CustomerDaoFactory {
    private static CustomerDao dao = null;
    private CustomerDaoFactory(){}
    public static CustomerDao getCustomerDao(){
        if(dao == null){
            dao = new CustomerDaoImpl();
        }
        return dao;
    }
}
