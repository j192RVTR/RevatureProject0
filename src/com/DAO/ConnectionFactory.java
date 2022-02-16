package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection;


    private ConnectionFactory(){

    }

    public static Connection getConnection() {
        if(connection == null){
            ResourceBundle bundle = ResourceBundle.getBundle("com/DAO/dbconfig");
            String url = bundle.getString("url");
            String username = bundle.getString("username");
            String password = bundle.getString("password");
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
