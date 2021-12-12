package com.example.brief3.DAO;

import com.example.brief3.models.Clients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Client {

    //get users
    public static ObservableList getClients() {
        ConnectionClass connection  = new ConnectionClass();
        ObservableList<Clients> client = FXCollections.observableArrayList();
        try{
            String query = "SELECT * FROM client";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                client.add(new Clients(
                        resultSet.getInt("client_id"),
                        resultSet.getString("work_badge"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("addresse"),
                        resultSet.getDate("hire_date"),
                        resultSet.getString("company_name")
                ));

            }
        }catch ( SQLException e){
            e.printStackTrace();

        }
        return client;
    }
}
