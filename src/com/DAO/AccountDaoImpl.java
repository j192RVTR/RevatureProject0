package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao{

    Connection connection;

    public AccountDaoImpl(){
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void submitAccountApplication(Customer customer, double startingBalance) {
        String sql = "insert into account (cust_id, amount) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            statement.setDouble(2, startingBalance);
            int count = statement.executeUpdate();
            if (count <= 0){
                throw new SQLException();
            }
            System.out.println("Submitted account application.");
        } catch (SQLException e) {
            System.out.println("Failed to submit account application.");
        }
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "update account set amount=?, status=?, approval_id=? where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, account.getAmount());
            statement.setString(2, account.getStatus().toString());
            statement.setInt(3, account.getApproval_id());
            statement.setInt(4, account.getId());
            int count = statement.executeUpdate();
            if(count <= 0)
                throw new SQLException();
            System.out.println("Successfully updated account.");
        } catch (SQLException e) {
            System.out.println("Failed to update account.");
        }

    }

    @Override
    public List<Account> getAccountsForCustomer(Customer customer) {
        String sql = "select * from account where cust_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            ResultSet rs = statement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                Account.Status status = Account.Status.valueOf(rs.getString(2));
                int cust_id = rs.getInt(3);
                int approval_id = rs.getInt(4);
                double amount = rs.getDouble(5);
                Timestamp dat = rs.getTimestamp(6);
                Account account = new Account(id, cust_id, amount, approval_id, status, dat);
                accounts.add(account);
            }
            System.out.println("Returning list of accounts...");
            return accounts;
        } catch (SQLException e) {
            System.out.println("Failed to build list of accounts.");
        }
        return new ArrayList<>();
    }

    @Override
    public List<Account> getApprovedAccountsForCustomer(Customer customer) {
        String sql = "select * from account where cust_id = ? and status = 'APPROVED'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            ResultSet rs = statement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                Account.Status status = Account.Status.valueOf(rs.getString(2));
                int cust_id = rs.getInt(3);
                int approval_id = rs.getInt(4);
                double amount = rs.getDouble(5);
                Timestamp dat = rs.getTimestamp(6);
                Account account = new Account(id, cust_id, amount, approval_id, status, dat);
                accounts.add(account);
            }
            System.out.println("Returning list of approved accounts...");

            return accounts;
        } catch (SQLException e) {
            System.out.println("Failed to build list of approved accounts.");
        }
        return new ArrayList<>();
    }

    public List<Account> getPendingAccounts(){
        String sql = "select * from account where status = 'PENDING'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Account> accounts = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt(1);
                int cust_id = rs.getInt(3);
                int approval_id = rs.getInt(4);
                double amount = rs.getDouble(5);
                Timestamp dat = rs.getTimestamp(6);
                Account account = new Account(id, cust_id, amount, approval_id, Account.Status.PENDING, dat);
                accounts.add(account);
            }
            System.out.println("Returning list of pending accounts...");
            return accounts;
        } catch (SQLException e) {
            System.out.println("Failed to build list of pending accounts.");
        }
        return new ArrayList<>();
    }


}
