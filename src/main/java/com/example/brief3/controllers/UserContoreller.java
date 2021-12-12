package com.example.brief3.controllers;

import com.example.brief3.DAO.Client;
import com.example.brief3.models.Clients;
import com.example.brief3.models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserContoreller implements Initializable {


    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField mail;
    @FXML
    private TextField phone;
    @FXML
    private TextArea adress;
    @FXML
    private TextField company;
    @FXML
    private DatePicker date;
    @FXML
    private TextField idC;

    @FXML
    private TableView<Users> table;


    @FXML
    private Label eCompany;

    @FXML
    private Label eFname;

    @FXML
    private Label eLname;

    @FXML
    private Label eEmail;

    @FXML
    private Label eID;

    @FXML
    private Label ePhone;

    @FXML
    private Label eDate;

    @FXML
    private Label eAddress;

    @FXML
    private ChoiceBox<String> country = new ChoiceBox<String>();
    @FXML
    private TableColumn<Users, String> cAdress;

    @FXML
    private TableColumn<Users, Date> cDate;

    @FXML
    private TableColumn<Users, String> cId;

    @FXML
    private TableColumn<Users, String> cLname;

    @FXML
    private TableColumn<Users, String> cFname;

    @FXML
    private TableColumn<Users, String> cPhone;

    @FXML
    private TableColumn<Users, String> cmail;

    @FXML
    private TableColumn<Users, String> comp;

    @FXML
    private RadioButton passport;

    @FXML
    private RadioButton cin;

    @FXML
    private ToggleGroup identifiant;

    @FXML
    private Button add;

    public void save() {


        String regexPhone = "[0-9]+";
        String regexEmail = "^(.+)@(\\S+)$";
        String regexCin = "[a-zA-Z]{2}\\d{6}";
        String regexPassport = "[a-zA-Z]{2}\\d{7}";

        Pattern p = Pattern.compile(regexPhone);
        Pattern e = Pattern.compile(regexEmail);
        Pattern c = Pattern.compile(regexCin);
        Pattern ps = Pattern.compile(regexPassport);

        Matcher mp = p.matcher(this.phone.getText());
        Matcher me = e.matcher(this.mail.getText());
        Matcher mc = c.matcher(this.idC.getText());
        Matcher mps = ps.matcher(this.idC.getText());

        RadioButton selectedRadioButton = (RadioButton) identifiant.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        boolean error = false;

        Clients client = new Clients();
        Client client1 = new Client();

        if (this.company.getLength() > 50 || this.company.getText().isEmpty()) {
            this.eCompany.setText("Should not be empty should be less than 50 character");
            error = true;
        } else {
            this.eCompany.setText("");
            client.setCompany(this.company.getText());
        }

        if (this.fname.getLength() > 50 || this.fname.getText().isEmpty()) {
            this.eFname.setText("Should not be empty should be less than 50 character");
            error = true;
        } else {
            this.eFname.setText("");
            client.setFname(this.fname.getText());
        }

        if (this.lname.getLength() > 50 || this.lname.getText().isEmpty()) {
            this.eLname.setText("Should not be empty should be less than 50 character");
            error = true;
        } else {
            this.eLname.setText("");
            client.setLname(this.lname.getText());
        }

        if (this.phone.getText().isEmpty() || this.phone.getLength() < 9 || !mp.matches()) {
            this.ePhone.setText("Should not be empty should be less than 9 character and only containe numbers");
            error = true;
        } else {
            this.ePhone.setText("");
            client.setPhone(this.country.getSelectionModel().getSelectedItem() + "" + this.phone.getText());
        }

        if (this.mail.getText().isEmpty() || !me.matches()) {
            this.eEmail.setText("Should not be empty should be an email");
            error = true;
        } else {
            this.eEmail.setText("");
            client.setEmail(this.mail.getText());
        }

        if (cin.isSelected()) {
            if (this.idC.getText().isEmpty() || !mc.matches()) {
                this.eID.setText("Should contain two alphabets and 6 digit");
                error = true;
            } else {
                client.setCin(this.idC.getText());
                client.setPassport(null);
                this.eID.setText("");
            }
        } else if (passport.isSelected()) {
            if (this.idC.getText().isEmpty() || !mps.matches()) {
                this.eID.setText("Should contain 2 alphabets and 7 digit");
                error = true;
            } else {
                client.setCin(null);
                client.setPassport(this.idC.getText());
                this.eID.setText("");
            }
        }

        if (this.adress.getText().isEmpty()) {
            this.eAddress.setText("Should not be empty");
            error = true;
        } else {
            this.eAddress.setText("");
            client.setAddress(this.adress.getText());
        }

        if (this.date.getValue() == null) {
            this.eDate.setText("Should not be empty");
            error = true;
        } else {
            this.eDate.setText("");
            client.setDate(java.sql.Date.valueOf(this.date.getValue()));
        }

        if (!error) {
            client1.save(client);

        }

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\admin\\Desktop\\Brief3\\src\\main\\resources\\com\\example\\brief3\\json\\ref.json")) {
            //Read JSON file
            Object obj = parser.parse(reader);

            JSONArray list = (JSONArray) obj;

            for (Object o : list) {
                JSONObject country_obj = (JSONObject) o;
                String country_code = (String) country_obj.get("dial_code");

                country.getItems().add(country_code);
            }

        }catch(Exception e1) {
        e1.printStackTrace();
    }

}
    public ObservableList<Users> getAll() {
        Client client = new Client();
        return client.getClients();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        cId.setCellValueFactory(new PropertyValueFactory<Users, String>("id"));
        comp.setCellValueFactory(new PropertyValueFactory<Users, String>("company"));
        cPhone.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        cLname.setCellValueFactory(new PropertyValueFactory<Users, String>("lname"));
        cFname.setCellValueFactory(new PropertyValueFactory<Users, String>("fname"));
        cAdress.setCellValueFactory(new PropertyValueFactory<Users, String>("address"));
        cmail.setCellValueFactory(new PropertyValueFactory<Users, String>("phone"));
        cDate.setCellValueFactory(new PropertyValueFactory<Users, Date>("date"));

        table.setItems(getAll());
    }
}