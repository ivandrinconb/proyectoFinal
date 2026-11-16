package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MisPuntosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listHistorialPuntos;

    @FXML
    private Button btnCanjearPuntos;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtPuntosDisponibles;

    @FXML
    void onActionCanjearPuntos(ActionEvent event) {

    }

    @FXML
    void onActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {
        txtPuntosDisponibles.setEditable(false);

    }


}
