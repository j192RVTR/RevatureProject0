package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDaoImpl implements TransferDao{

    Connection connection;

    public TransferDaoImpl(){
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insertTransfer(Transfer transfer) {
        String sql = "insert into transfer (amount, from_id, to_id) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, transfer.getAmount());
            statement.setInt(2, transfer.getFrom_id());
            statement.setInt(3, transfer.getTo_id());
            int count = statement.executeUpdate();
            if(count <= 0)
                throw new SQLException();
            System.out.println("Posted new transfer.");
        } catch (SQLException e) {
            System.out.println("Transfer post failed!");
        }

    }

    @Override
    public void updateTransfer(Transfer transfer) {
        String sql = "update transfer set status = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, transfer.getStatus().toString());
            statement.setInt(2, transfer.getId());
            int count = statement.executeUpdate();
            if(count <= 0)
                throw new SQLException();

            if(!transfer.getStatus().equals(Transfer.Status.ACCEPTED)) {
                System.out.println("Transfer update complete.");
                return;
            }

            String sql2 = "select status from transfer where id = ?";
            PreparedStatement status_statement = connection.prepareStatement(sql2);
            status_statement.setInt(1, transfer.getId());
            ResultSet rs = status_statement.executeQuery();
            if(!rs.next())
                throw new SQLException();

            if(rs.getString("status").equals("SUCCESS"))
                System.out.println("Transfer Success!");
            else
                throw new SQLException();

        } catch (SQLException e) {
            System.out.println("Failed to update transfer!");
        }
    }

    @Override
    public List<Transfer> getTransfersToCustomer(Customer customer) {
        //String sql = "select * from account join transfer on account.id = to_id where cust_id = ? and status = 'OPEN'";
        String sql = "select transfer.id, transfer.amount, trans_date, transfer.status, transfer.from_id, transfer.to_id, cust_id " +
                "from account join transfer on account.id = to_id " +
                "where cust_id = ? and transfer.status = 'OPEN' and account.status = 'APPROVED'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            ResultSet rs = statement.executeQuery();
            List<Transfer> transfers = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                int from_id = rs.getInt(5);
                double amount = rs.getDouble(2);
                Timestamp trans_date = rs.getTimestamp(3);
                Transfer transfer = new Transfer(id, from_id, customer.getId(), Transfer.Status.OPEN, amount, trans_date);
                transfers.add(transfer);
            }
            return transfers;
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("Failed to build list of transfers!");
        }
        return new ArrayList<>();
    }

    @Override
    public List<Transfer> getTransfers() {
        String sql = "select * from transfer";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Transfer> transfers = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                int from_id = rs.getInt(5);
                int to_id = rs.getInt(6);
                double amount = rs.getDouble(2);
                Timestamp trans_date = rs.getTimestamp(3);
                Transfer.Status status = Transfer.Status.valueOf(rs.getString(4));

                Transfer transfer = new Transfer(id, from_id, to_id, status, amount, trans_date);
                transfers.add(transfer);
            }
            return transfers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
