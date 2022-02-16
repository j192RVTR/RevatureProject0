package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositDaoImpl implements DepositDao{

    Connection connection;

    public DepositDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insertDeposit(Deposit deposit) {
        String sql = "insert into deposit (amount, account_id) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, deposit.getAmount());
            statement.setInt(2, deposit.getAccount_id());
            int count = statement.executeUpdate();
            if(count<=0)
                throw new SQLException();
            ResultSet genKeys = statement.getGeneratedKeys();
            if(!genKeys.next())
                throw new SQLException();
            int status_id = genKeys.getInt(1);
            String sql2 = "select status from deposit where id = ?";
            PreparedStatement status_statement = connection.prepareStatement(sql2);
            status_statement.setInt(1, status_id);
            ResultSet rs = status_statement.executeQuery();
            if(!rs.next())
                throw new SQLException();


            if(rs.getString("status").equals("SUCCESS"))
                System.out.println("Deposit Success!");
            else
                throw new SQLException();

        } catch (SQLException e) {
            System.out.println("Deposit Attempt Failed!");
        }


    }

    @Override
    public List<Deposit> getDeposits() {
        List<Deposit> deposits = new ArrayList<>();
        String sql = "select * from deposit";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                double amount = rs.getDouble(2);
                Timestamp timestamp = rs.getTimestamp(3);
                int acc_id = rs.getInt(4);
                Deposit.Status status = Deposit.Status.valueOf(rs.getString(5));
                Deposit deposit = new Deposit(id, acc_id, amount, timestamp, status);
                deposits.add(deposit);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get deposits from Database!");
        }
        return deposits;
    }
}
