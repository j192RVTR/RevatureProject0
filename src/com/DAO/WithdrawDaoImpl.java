package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WithdrawDaoImpl implements WithdrawDao{

    Connection connection;

    public WithdrawDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insertWithdraw(Withdraw withdraw) {
        String sql = "insert into withdraw (amount, account_id) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, withdraw.getAmount());
            statement.setInt(2, withdraw.getAccount_id());
            int count = statement.executeUpdate();
            if(count<=0)
                throw new SQLException();
            ResultSet genKeys = statement.getGeneratedKeys();
            if(!genKeys.next())
                throw new SQLException();
            int status_id = genKeys.getInt(1);
            String sql2 = "select status from withdraw where id = ?";
            PreparedStatement status_statement = connection.prepareStatement(sql2);
            status_statement.setInt(1, status_id);
            ResultSet rs = status_statement.executeQuery();
            if(!rs.next())
                throw new SQLException();


            if(rs.getString("status").equals("SUCCESS"))
                System.out.println("Withdraw Success!");
            else
                throw new SQLException();
        } catch (SQLException e) {
            System.out.println("Withdraw attempt failed!");
        }
    }

    @Override
    public List<Withdraw> getWithdraws() {
        List<Withdraw> withdraws = new ArrayList<>();
        String sql = "select * from withdraw";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                double amount = rs.getDouble(2);
                Timestamp timestamp = rs.getTimestamp(3);
                int acc_id = rs.getInt(4);
                Withdraw.Status status = Withdraw.Status.valueOf(rs.getString(5));
                Withdraw withdraw = new Withdraw(id, acc_id, amount, timestamp, status);
                withdraws.add(withdraw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return withdraws;

    }
}
