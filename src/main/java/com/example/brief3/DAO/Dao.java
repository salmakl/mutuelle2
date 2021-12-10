package com.example.brief3.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    static Connection connection=null;
    static String url="jdbc:mysql://localhost:3306/";
    static String dbName="mutuelle";
    static String driver="com.mysql.jdbc.Driver";
    static String userName="root";
    static String password="2420";

    Connection getConnection() {

        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connection established succesfully!");
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("getConnection : Error" + e.getMessage());
            return null;

        }


    }


}

