package com.example.brief3.controllers;

import com.example.brief3.DAO.Client;
import com.example.brief3.models.Clients;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = new Client();
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        HashMap<String, Integer> chart = client.statistic();
        for (String key : chart.keySet()) {
            series.getData().add(new XYChart.Data<String, Number>(key, chart.get(key)));
        }
        lineChart.getData().add(series);
    }
}
