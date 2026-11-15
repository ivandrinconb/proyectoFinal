package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TarjetaVisualController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRecargarSaldo;

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<String> listMovimientos;

    @FXML
    private TextField txtSaldoDisponible;

    @FXML
    private TextField txtMontoRecarga;

    @FXML
    private TextField txtNumeroTarjeta;

    @FXML
    void onActionRecargarSaldo(ActionEvent event) {

    }

    @FXML
    void onActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {
        txtNumeroTarjeta.setEditable(false);
        txtSaldoDisponible.setEditable(false);

    }

}
