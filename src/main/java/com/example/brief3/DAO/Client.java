package com.example.brief3.DAO;

import com.example.brief3.models.Clients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


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
                        resultSet.getString("address"),
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
        String query = "INSERT INTO client (work_badge, company_name, hire_date, firstname, lastname, cin, passport, phone, email, address) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, client.getBadge());
            preparedStatement.setString(2, client.getCompany());
            preparedStatement.setDate(3, Date.valueOf(client.getDate().toString()));
            preparedStatement.setString(4, client.getFname());
            preparedStatement.setString(5, client.getLname());
            preparedStatement.setString(6, client.getCin());
            preparedStatement.setString(7, client.getPassport());
            preparedStatement.setString(8, client.getPhone());
            preparedStatement.setString(9, client.getEmail());
            preparedStatement.setString(10, client.getAddress());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //search client

    public ObservableList<Clients> search(String search) {
        ConnectionClass connection = new ConnectionClass();
        ObservableList<Clients> client = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM client WHERE cin LIKE'%" + search+ "%' OR firstname LIKE '%" + search + "%' OR lastname LIKE '%" + search + "%' OR email LIKE '%" + search + "%'";
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
                        resultSet.getString("address"),
                        resultSet.getDate("hire_date"),
                        resultSet.getString("company_name")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    //get company name

    public ObservableList<String> getCompany() {
        ConnectionClass connection = new ConnectionClass();
        ObservableList<String> company = FXCollections.observableArrayList();
        try {
            String query = "SELECT company_name FROM client";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                company.add(resultSet.getString("company_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    //get clients by company

    public ObservableList<Clients> getClientsByCompany(String company) {
        ConnectionClass connection = new ConnectionClass();
        ObservableList<Clients> clients = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM client WHERE company_name = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, company);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Clients(
                        resultSet.getInt("client_id"),
                        resultSet.getString("work_badge"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getDate("hire_date"),
                        resultSet.getString("company_name")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    //draw chart

    public HashMap<String,Integer> statistic (){
        ConnectionClass connection = new ConnectionClass();
        HashMap<String,Integer> chart= new HashMap<>();
        try {
            String query = "SELECT date(created_at) as creation, COUNT(*) FROM client GROUP BY date(created_at)";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chart.put(resultSet.getDate("creation").toString(),
                        resultSet.getInt("COUNT(*)"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chart;
    }

    }



