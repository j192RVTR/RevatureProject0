package com.UI;

import com.DAO.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) {
        ConsoleContext context = new StartContext();
        try(Connection connection = ConnectionFactory.getConnection()){
            while(context.getFlag()){
                context = context.eval();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed...");
        }
        System.out.println("Exiting...");
    }
}
