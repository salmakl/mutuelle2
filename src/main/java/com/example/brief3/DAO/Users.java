package com.example.brief3.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Users {


    public boolean Login(String email, String password) {
        ConnectionClass connectionClass = new ConnectionClass();

        try {
            String sql = "SELECT * FROM officials WHERE email ='" + email +"' and password = '" + password + "'";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
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


}
