package com.example.brief3.controllers;

import com.example.brief3.DAO.ConnectionClass;
import com.example.brief3.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LoginController{

    public LoginController() {
    }
    @FXML
    private Button button;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrong;


    public void checkLogin() throws IOException {

        LoginApplication main = new LoginApplication();
        String email = this.email.getText();
        String password = this.password.getText();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);



            statement.setString(1, email);
            statement.setString(2, password);
            statement.execute();
            if (statement.getResultSet().next()) {
                main.chaneScene("dashboard.fxml");
            }
            else {
                wrong.setText("Wrong email or password");
            }


        }catch (Exception e) {
            e.printStackTrace();
        }





    }


}