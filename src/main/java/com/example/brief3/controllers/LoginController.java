package com.example.brief3.controllers;

import com.example.brief3.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileReader;
import java.io.IOException;


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
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\admin\\Desktop\\Brief3\\src\\main\\resources\\com\\example\\brief3\\json\\fanctionnaire.json")) {
            //Read JSON file
            Object obj = parser.parse(reader);

            JSONArray funList = (JSONArray) obj;
            //System.out.println(funList);

            for(int i = 0; i < funList.size(); i++) {
                JSONObject fonctionnaire = (JSONObject) funList.get(i);
                String email = (String) fonctionnaire.get("email");
                String password = (String) fonctionnaire.get("password");

                if((this.email.getText().isEmpty() || this.password.getText().isEmpty())){
                    wrong.setText("Please enter your email and password");
                    break;
                } else if (email.equals(this.email.getText()) && password.equals(this.password.getText())){
                    wrong.setText("Success!");
                    main.chaneScene("dashboard.fxml");
                    break;
                } else {
                    wrong.setText("Wrong email or password");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }





    }


}