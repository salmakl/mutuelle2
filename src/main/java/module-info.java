module com.example.brief3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.sql;
    requires mysql.connector.java;
    requires org.junit.jupiter.api;


    opens com.example.brief3 to javafx.fxml,javafx.base;
    exports com.example.brief3;
    exports com.example.brief3.models;
    opens com.example.brief3.models to javafx.base, javafx.fxml;
    exports com.example.brief3.controllers;
    opens com.example.brief3.controllers to javafx.base, javafx.fxml;
}