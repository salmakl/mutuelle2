package com.example.brief3.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionClass {

    static Connection connection=null;
    static String url="jdbc:mysql://localhost:3306/";
    static String dbName="mutuelle";
    static String driver="com.mysql.jdbc.Driver";
    static String userName="root";
    static String password="2420";

    public Connection getConnection() {


        try {

            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connection established succesfully!");
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("getConnection : Error" + e.getMessage());
            return null;

        }


    }




}

