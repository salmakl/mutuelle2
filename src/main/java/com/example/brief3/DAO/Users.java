package com.example.brief3.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Users {


    public boolean Login(String email, String password) {
        ConnectionClass connectionClass = new ConnectionClass();

        try {
            String sql = "SELECT * FROM officials WHERE email ='" + email +"' and password = '" + password + "'";
            System.out.println(sql);
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            //System.out.println(resultSet.getString("password" ));
            if (resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public String getPassword(String email){

        ConnectionClass connection = new ConnectionClass();
        String password = null;
        try {
            String query = "SELECT password FROM officials WHERE email = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }


}
