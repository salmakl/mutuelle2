package com.example.brief3.DAO;

import com.example.brief3.models.Clients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Client {

    //get users
    public  ObservableList getClients() {
        ConnectionClass connection = new ConnectionClass();
        ObservableList<Clients> client = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM client";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    //insert user

    public void save(Clients client) {
        ConnectionClass connection = new ConnectionClass();
        String query = "INSERT INTO client (work_badge, firstname, lastname, email, phone, addresse, hire_date, company_name) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, client.getBadge());
            preparedStatement.setString(2, client.getFname());
            preparedStatement.setString(3, client.getLname());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.setString(6, client.getAddress());
            preparedStatement.setDate(7, Date.valueOf(client.getDate().toString()));
            preparedStatement.setString(8, client.getCompany());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}