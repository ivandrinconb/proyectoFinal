package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MisReservasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnPagarReserva;

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
        Reserva reservaSeleccionada = listReservas.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin selección", "Debe seleccionar una reserva.");
            return;
        }

        if (reservaSeleccionada.getEstadoEnum() == EstadoReserva.CANCELADA) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva cancelada", "La reserva ya se encuentra cancelada.");
            return;
        }

        reservaSeleccionada.cancelar();
        txtEstado.setText(reservaSeleccionada.getEstado().getNombreEstado());
        listReservas.refresh();

        mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva cancelada", "La reserva ha sido cancelada correctamente.");
    }

    @FXML
    void onActionPagarReserva(ActionEvent event) {
        Reserva reservaSeleccionada = listReservas.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin selección", "Debe seleccionar una reserva.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinal/proyectofinal/pagoReserva.fxml")
            );
            Parent root = loader.load();

            PagoReservaController controller = loader.getController();
            controller.cargarReserva(reservaSeleccionada);

            Stage stage = (Stage) btnPagarReserva.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/proyectofinal/proyectofinal/DashboardCliente.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        txtCodigo.setEditable(false);
        txtHabitacion.setEditable(false);
        txtEstado.setEditable(false);
        txtFechaEntrada.setEditable(false);
        txtFechaSalida.setEditable(false);
        txtValorTotal.setEditable(false);

        listReservas.getItems().addAll(
                SistemaController.getInstancia().getHotelUQ().getReservas()
        );

        listReservas.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, reservaAnterior, reservaSeleccionada) -> {
                    if (reservaSeleccionada != null) {
                        txtCodigo.setText(reservaSeleccionada.getCodigo());
                        txtHabitacion.setText(reservaSeleccionada.getHabitacion().toString());
                        txtEstado.setText(reservaSeleccionada.getEstado().getNombreEstado());
                        txtFechaEntrada.setText(reservaSeleccionada.getFechaEntrada().toString());
                        txtFechaSalida.setText(reservaSeleccionada.getFechaSalida().toString());
                        txtValorTotal.setText(String.valueOf(reservaSeleccionada.getValorTotal()));
                    }
                });
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}