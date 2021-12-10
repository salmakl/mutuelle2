package com.example.brief3.controllers;

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

    List<Users> users = new ArrayList<>();


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
    private TextField id;

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

    ObservableList<Users> list = FXCollections.observableArrayList();

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
        Matcher mc = c.matcher(this.id.getText());
        Matcher mps = ps.matcher(this.id.getText());

        RadioButton selectedRadioButton = (RadioButton) identifiant.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        boolean error = false;

        Users user = new Users();
        if (this.company.getLength() > 50 || this.company.getText().isEmpty()) {
            this.eCompany.setText("Should not be empty should be less than 50 character");
            error = true;
        } else {
            this.eCompany.setText("");
            user.setCompany(this.company.getText());
        }

        if (this.fname.getLength() > 50 || this.fname.getText().isEmpty()) {
            this.eFname.setText("Should not be empty should be less than 50 character");
            error = true;
        } else {
            this.eFname.setText("");
            user.setFname(this.fname.getText());
        }

        if (this.lname.getLength() > 50 || this.lname.getText().isEmpty()) {
            this.eLname.setText("*should not be empty should be less than 50 character");
            error = true;
        } else {
            this.eLname.setText("");
            user.setLname(this.lname.getText());
        }

        if (this.phone.getText().isEmpty() || this.phone.getLength() < 9 || !mp.matches()) {
            this.ePhone.setText("*should not be empty should be less than 9 character and only containe numbers");
            error = true;
        } else {
            this.ePhone.setText("");
            user.setPhone(this.country.getSelectionModel().getSelectedItem() + "" + this.phone.getText());
        }

        if (this.mail.getText().isEmpty() || !me.matches()) {
            this.eEmail.setText("Should not be empty should be an email");
            error = true;
        } else {
            this.eEmail.setText("");
            user.setEmail(this.mail.getText());
        }

        if (Objects.equals(toogleGroupValue, "CIN")) {
            if (this.id.getText().isEmpty() || !mc.matches()) {
                this.eID.setText("*should contain two alphabets and 6 digit");
                error = true;
            } else {
                user.setId("CIN: " + this.id.getText());
                this.eID.setText("");
            }
        } else if (Objects.equals(toogleGroupValue, "Passport")) {
            if (this.id.getText().isEmpty() || !mps.matches()) {
                this.eID.setText("Should contain 2 alphabets and 7 digit");
                error = true;
            } else {
                user.setId("Passport: " + this.id.getText());
                this.eID.setText("");
            }
        }

        if (this.adress.getText().isEmpty()) {
            this.eAddress.setText("Should not be empty");
            error = true;
        } else {
            this.eAddress.setText("");
            user.setAdress(this.adress.getText());
        }

        if (this.date.getValue() == null) {
            this.eDate.setText("Should not be empty");
            error = true;
        } else {
            this.eDate.setText("");
            user.setDate(this.date.getValue());
        }

        if (!error) {
            list.add(user);
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comp.setCellValueFactory(new PropertyValueFactory<Users, String>("company"));
        cId.setCellValueFactory(new PropertyValueFactory<Users, String>("id"));
        cPhone.setCellValueFactory(new PropertyValueFactory<Users, String>("phone"));
        cLname.setCellValueFactory(new PropertyValueFactory<Users, String>("lname"));
        cFname.setCellValueFactory(new PropertyValueFactory<Users, String>("fname"));
        cAdress.setCellValueFactory(new PropertyValueFactory<Users, String>("adress"));
        cmail.setCellValueFactory(new PropertyValueFactory<Users, String>("mail"));
        cDate.setCellValueFactory(new PropertyValueFactory<Users, Date>("date"));

        table.setItems(list);

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\admin\\Desktop\\Brief3\\src\\main\\resources\\com\\example\\brief3\\json\\ref.json")){
            //Read JSON file
            Object obj = parser.parse(reader);

            JSONArray list = (JSONArray) obj;

            for (Object o : list) {
                JSONObject country_obj = (JSONObject) o;
                String country_code = (String) country_obj.get("dial_code");

                country.getItems().add(country_code);
            }


    }catch (Exception e){
            e.printStackTrace();
        }
    }
}