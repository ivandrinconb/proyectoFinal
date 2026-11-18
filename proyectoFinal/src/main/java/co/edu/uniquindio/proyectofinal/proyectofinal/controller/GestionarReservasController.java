package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class GestionarReservasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<?> cbEstado;

    @FXML
    private ComboBox<?> cbSeleccionarReserva;

    @FXML
    private DatePicker dateFechaEntrada;

    @FXML
    private DatePicker dateFechaSalida;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtHabitacion;

    @FXML
    private TextField txtValorTotal;

    @FXML
    void onActionCancelarReserva(ActionEvent event) {

    }

    @FXML
    void onActionGuardarCambios(ActionEvent event) {

    }

    @FXML
    void onActionLimpiar(ActionEvent event) {

    }

    @FXML
    void onActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}