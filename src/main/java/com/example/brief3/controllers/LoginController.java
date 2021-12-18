package com.example.brief3.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.brief3.DAO.ConnectionClass;
import com.example.brief3.DAO.Users;
import com.example.brief3.Mutuelle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;



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

    public void crypt(){
        String password = "5B2yubZU";

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        // $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        // result.verified == true
        System.out.println(bcryptHashString);
    }

    public void checkLogin() throws IOException {

        Mutuelle main = new Mutuelle();
        String email = this.email.getText();
        String password = this.password.getText();
        crypt();

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