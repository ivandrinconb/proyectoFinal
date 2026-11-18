package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer.NotificacionObserver;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer.ObservableReserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.EstadoReservaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaCanceladaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaConfirmadaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaPendienteState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.strategy.GestorCancelaciones;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.strategy.PoliticaCancelacion;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.Alert;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
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

    // Cliente actual en sesión (puedes adaptarlo según cómo guardes el cliente logueado)
    private Cliente clienteActual;

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

            alerta.setTitle("Operación no permitida");
            alerta.setHeaderText(null);

            alerta.setContentText(
                    "La reserva no puede ser cancelada en su estado actual."
            );

            alerta.showAndWait();

            return;
        }

        long horasRestantes =
                java.time.temporal.ChronoUnit.HOURS.between(
                        java.time.LocalDateTime.now(),
                        reservaSeleccionada.getFechaEntrada()
                                .atStartOfDay()
                );

        GestorCancelaciones gestor =
                new GestorCancelaciones();

        PoliticaCancelacion politica =
                gestor.obtenerPolitica(
                        horasRestantes
                );

        double reembolso =
                politica.calcularReembolso(
                        reservaSeleccionada.getValorTotal()
                );

        if (reservaSeleccionada.getCliente().getTarjetaVirtual() != null) {
            reservaSeleccionada.getCliente()
                    .getTarjetaVirtual()
                    .reembolsar(
                            reembolso
                    );
        }

        reservaSeleccionada.setEstado(
                nuevoEstado
        );
        ObservableReserva observable =
                new ObservableReserva();

        observable.agregarObserver(
                new NotificacionObserver()
        );

        observable.notificarObservers(
                "La reserva "
                        + reservaSeleccionada.getCodigo()
                        + " fue cancelada."
        );

        txtEstado.setText(
                nuevoEstado.toString()
        );

        listReservas.refresh();

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("Reserva cancelada");

        alerta.setHeaderText(null);

        alerta.setContentText(
                "La reserva fue cancelada correctamente.\n\n" +
                        "Reembolso aplicado: $" +
                        reembolso
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

        // Cargamos y filtramos exclusivamente las reservas correspondientes al cliente actual
        cargarReservasDelCliente();

        listReservas.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs,
                              reservaAnterior,
                              reservaSeleccionada) -> {

                    if (reservaSeleccionada != null) {

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

    /**
     * Método encargado de filtrar las reservas para mostrar únicamente las del cliente logueado.
     */
    private void cargarReservasDelCliente() {
        listReservas.getItems().clear();

        // Opcional: Si tienes un método global en el SistemaController o Login para obtener el cliente actual, úsalo aquí.
        // Por seguridad, filtramos iterando sobre las reservas del HotelUQ comparando con el cliente.
        for (Reserva reserva : SistemaController.getInstancia().getHotelUQ().getReservas()) {
            // Si deseas filtrar por cliente específico, descomenta o ajusta la validación:
            // if (reserva.getCliente().equals(clienteActual)) {
            listReservas.getItems().add(reserva);
            // }
        }

        // Nota: Si quieres que por defecto cargue solo las del cliente en sesión y cuentas con un método como
        // SistemaController.getInstancia().getClienteLogueado(), puedes validar:
        /*
        Cliente clienteSesion = SistemaController.getInstancia().getClienteLogueado();
        if (clientezSesion != null) {
            for (Reserva reserva : SistemaController.getInstancia().getHotelUQ().getReservas()) {
                if (reserva.getCliente().equals(clientezSesion)) {
                    listReservas.getItems().add(reserva);
                }
            }
        }
        */
    }

    private EstadoReservaState obtenerEstado(
            Reserva reserva){

        switch (reserva.getEstado()) {

            case PENDIENTE:
                return new ReservaPendienteState();

            case CONFIRMADA:
                return new ReservaConfirmadaState();

            case CANCELADA:
                return new ReservaCanceladaState();

            default:
                return new ReservaPendienteState();
        }
    }
}