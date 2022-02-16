package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao{

    Connection connection;

    public EmployeeDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addEmployee(Employee e) {

    }

    @Override
    public void updateEmployee(Employee e) {

    }

    @Override
    public void deleteEmployee(int id) {

    }

    @Override
    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        String sql = "select * from employee where username = ? and password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(!rs.next())
                throw new SQLException();
            int id = rs.getInt(1);
            String name = rs.getString(4);
            return new Employee(id, username, password, name);
        } catch (SQLException e) {
            System.out.println("Failed to find employee with given username and password.");
        }
        return null;
    }
}
