package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MisReservasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<Reserva> listReservas;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtFechaEntrada;

    @FXML
    private TextField txtFechaSalida;

    @FXML
    private TextField txtHabitacion;

    @FXML
    private TextField txtValorTotal;

    @FXML
    void onActionCancelarReserva(ActionEvent event) {

    }

    @FXML
    void onActionVolver(ActionEvent event) {

    }

    @FXML
    void initialize() {
        txtCodigo.setEditable(false);
        txtHabitacion.setEditable(false);
        txtEstado.setEditable(false);
        txtFechaEntrada.setEditable(false);
        txtFechaSalida.setEditable(false);
        txtValorTotal.setEditable(false);
    }

}