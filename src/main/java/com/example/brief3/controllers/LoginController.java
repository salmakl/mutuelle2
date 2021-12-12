package com.example.brief3.controllers;

import com.example.brief3.DAO.ConnectionClass;
import com.example.brief3.DAO.Users;
import com.example.brief3.Mutuelle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.PreparedStatement;


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

        Mutuelle main = new Mutuelle();
        String email = this.email.getText();
        String password = this.password.getText();
        try {

            ConnectionClass connectionClass = new ConnectionClass();
            connectionClass.getConnection();

            Users users = new Users();





            if (email.isEmpty() || password.isEmpty()) {
                wrong.setText("Please fill in all fields");
            }
            else if (users.Login(email, password)) {
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