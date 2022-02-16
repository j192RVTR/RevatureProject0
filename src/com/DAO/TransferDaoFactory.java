package com.DAO;

public class TransferDaoFactory {
    private static TransferDao dao;
    private TransferDaoFactory(){}
    public static TransferDao getTransferDao() {
        if(dao == null)
        {
            dao = new TransferDaoImpl();
            return dao;
        }
        return dao;
    }
}
