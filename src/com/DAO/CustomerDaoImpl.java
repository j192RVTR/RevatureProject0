package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    Connection connection;

    public CustomerDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Customer addCustomer(Customer c) {
        String sql = "insert into customer (name, username, password) values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, c.getName());
            statement.setString(2, c.getUsername());
            statement.setString(3, c.getPassword());
            int count = statement.executeUpdate();
            if (count <= 0)
                throw new SQLException();
            return getCustomerByUsernameAndPassword(c.getUsername(), c.getPassword());
        } catch (SQLException e) {
            System.out.println("Insert into customer table failed!");
        }
        return null;

    }

    @Override
    public void updateCustomer(Customer c) {
        String sql = "update customer set name=?, username=?, password=? where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, c.getName());
            statement.setString(2, c.getUsername());
            statement.setString(3, c.getPassword());
            statement.setInt(4, c.getId());
            int count = statement.executeUpdate();
            if(count <= 0)
                throw new SQLException();
            System.out.println("Update customer table success!");

        }
        catch (SQLException sqlException){
            System.out.println("Update customer table failed!");
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "delete from customer where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int count = statement.executeUpdate();
            if(count <= 0)
                throw new SQLException();
            System.out.println("Delete from customer table success!");

        } catch (SQLException e) {
            System.out.println("Delete from customer table failed!");
        }

    }

    @Override
    public Customer getCustomerByUsernameAndPassword(String username, String password) {
        String sql = "select * from customer where username = ? and password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(!rs.next())
                throw new SQLException();
            int id = rs.getInt(1);
            String name = rs.getString(4);
            return new Customer(id, username, password, name);
        } catch (SQLException e) {
            System.out.println("Failed to find customer with given username and password.");
        }
        return null;

    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString(4);
                int id = rs.getInt(1);
                String username = rs.getString(2);
                Customer customer = new Customer(id, username, "****", name);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
