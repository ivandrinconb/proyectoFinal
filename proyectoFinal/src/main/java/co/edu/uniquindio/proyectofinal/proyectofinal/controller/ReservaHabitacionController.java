package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ReservaHabitacionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<Cliente> cbCliente;

    @FXML
    private ComboBox<Habitacion> cbHabitacion;

    @FXML
    private DatePicker dateFechaEntrada;

    @FXML
    private DatePicker dateFechaSalida;

    @FXML
    private TextField txtValorTotal;

    @FXML
    void onActionCrearReserva(ActionEvent event) {

    }

    @FXML
    void onActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}