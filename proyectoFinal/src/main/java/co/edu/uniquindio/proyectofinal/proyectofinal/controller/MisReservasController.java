package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;
import javafx.scene.control.Alert;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Reserva reservaSeleccionada =
                listReservas.getSelectionModel()
                        .getSelectedItem();

        if (reservaSeleccionada == null) {

            Alert alerta = new Alert(
                    Alert.AlertType.WARNING
            );

            alerta.setTitle("Sin selección");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "Debe seleccionar una reserva."
            );

            alerta.showAndWait();

            return;
        }

        EstadoReservaState estadoActual =
                obtenerEstado(reservaSeleccionada);

        EstadoReserva nuevoEstado =
                estadoActual.cancelar();

        if (nuevoEstado ==
                reservaSeleccionada.getEstado()) {

            Alert alerta = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alerta.setTitle("Reserva cancelada");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "La reserva ya se encuentra cancelada."
            );

            alerta.showAndWait();

            return;
        }

        reservaSeleccionada.setEstado(
                EstadoReserva.CANCELADA
        );

        txtEstado.setText(
                reservaSeleccionada.getEstado()
                        .toString()
        );

        listReservas.refresh();

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("Reserva cancelada");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "La reserva ha sido cancelada correctamente."
        );

        alerta.showAndWait();

    }

    @FXML
    void onActionPagarReserva(ActionEvent event) {
        Reserva reservaSeleccionada =
                listReservas.getSelectionModel()
                        .getSelectedItem();

        if (reservaSeleccionada == null) {

            Alert alerta = new Alert(
                    Alert.AlertType.WARNING
            );

            alerta.setTitle("Sin selección");

            alerta.setHeaderText(null);

            alerta.setContentText(
                    "Debe seleccionar una reserva."
            );

            alerta.showAndWait();

            return;
        }

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/proyectofinal/proyectofinal/pagoReserva.fxml"
                            )
                    );

            Parent root = loader.load();

            PagoReservaController controller =
                    loader.getController();

            controller.cargarReserva(
                    reservaSeleccionada
            );

            Stage stage = (Stage)
                    btnPagarReserva
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root)
            );

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    void onActionVolver(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/DashboardCliente.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage)
                    btnVolver.getScene().getWindow();

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
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getReservas()
        );

        listReservas.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs,
                              reservaAnterior,
                              reservaSeleccionada) -> {

                    if(reservaSeleccionada != null){

                        txtCodigo.setText(
                                reservaSeleccionada.getCodigo()
                        );

                        txtHabitacion.setText(
                                reservaSeleccionada
                                        .getHabitacion()
                                        .toString()
                        );

                        txtEstado.setText(
                                reservaSeleccionada
                                        .getEstado()
                                        .toString()
                        );

                        txtFechaEntrada.setText(
                                reservaSeleccionada
                                        .getFechaEntrada()
                                        .toString()
                        );

                        txtFechaSalida.setText(
                                reservaSeleccionada
                                        .getFechaSalida()
                                        .toString()
                        );

                        txtValorTotal.setText(
                                String.valueOf(
                                        reservaSeleccionada
                                                .getValorTotal()
                                )
                        );
                    }
                });
    }



}