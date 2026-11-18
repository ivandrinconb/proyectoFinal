package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class ReportesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DateFechaFin;

    @FXML
    private BarChart<?, ?> barChartGrafica;

    @FXML
    private Button btnExportarPDF;

    @FXML
    private Button btnExportarTXT;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<?> cbTiposReporte;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TextArea txtResumen;

    @FXML
    void onActionExportarPDF(ActionEvent event) {

    }

    @FXML
    void onActionExportarTXT(ActionEvent event) {

    }

    @FXML
    void onActionGenerarReporte(ActionEvent event) {

    }

    @FXML
    void onActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
