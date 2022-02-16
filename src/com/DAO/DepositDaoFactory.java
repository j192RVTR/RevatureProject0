package com.DAO;

public class DepositDaoFactory {
    private static DepositDao dao;
    private DepositDaoFactory(){}
    public static DepositDao getDepositDao(){
        if(dao == null){
            dao = new DepositDaoImpl();
            return dao;
        }
        return dao;
    }
}
