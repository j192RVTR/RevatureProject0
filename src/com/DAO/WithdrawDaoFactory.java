package com.DAO;

public class WithdrawDaoFactory {
    private static WithdrawDao withdrawDao;
    private WithdrawDaoFactory(){}
    public static WithdrawDao getWithdrawDao(){
        if (withdrawDao == null){
            withdrawDao = new WithdrawDaoImpl();
            return withdrawDao;
        }
        return withdrawDao;
    }
}
