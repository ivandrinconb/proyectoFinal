package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoReserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TarjetaVirtual;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer.NotificacionObserver;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.observer.ObservableReserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.EstadoReservaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaCanceladaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaConfirmadaState;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.state.ReservaPendienteState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PagoReservaController {
    private Reserva reserva;
    private TarjetaVirtual tarjeta;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmarPago;

    @FXML
    private TextField txtCodigoReserva;

    @FXML
    private TextField txtHabitacion;

    @FXML
    private TextField txtNumeroTarjeta;

    @FXML
    private TextField txtSaldoDisponible;

    @FXML
    private TextField txtValorPagar;

    @FXML
    void onActionCancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/MisReservas.fxml"
                    )
            );
            Parent root = loader.load();

            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionConfirmarPago(ActionEvent event) {
        if (reserva == null) {
            return;
        }

        if (reserva.getEstadoEnum() == EstadoReserva.CONFIRMADA
                || reserva.getEstadoEnum() == EstadoReserva.CANCELADA) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Operación no permitida");
            alerta.setHeaderText(null);
            alerta.setContentText("La reserva no puede ser confirmada en su estado actual.");
            alerta.showAndWait();
            return;
        }

        boolean pagoExitoso = tarjeta.pagar(reserva.getValorTotal());

        if (!pagoExitoso) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Saldo insuficiente");
            alerta.setHeaderText(null);
            alerta.setContentText("La tarjeta no tiene saldo suficiente.");
            alerta.showAndWait();
            return;
        }

        EstadoReservaState estadoActual = obtenerEstado(reserva);
        estadoActual.confirmar(reserva);

        ObservableReserva observable = new ObservableReserva();
        observable.agregarObserver(new NotificacionObserver());
        observable.notificarObservers("La reserva " + reserva.getCodigo() + " fue confirmada.");

        Cliente cliente = reserva.getCliente();
        txtSaldoDisponible.setText(String.valueOf(tarjeta.getSaldo()));

        int puntosGanados = (int) (reserva.getValorTotal() / 100);
        cliente.getCuentaPuntos().agregarPuntos(puntosGanados);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Pago exitoso");
        alerta.setHeaderText(null);
        alerta.setContentText("La reserva fue confirmada correctamente.");
        alerta.showAndWait();

        onActionCancelar(event);
    }

    @FXML
    void initialize() {
        txtCodigoReserva.setEditable(false);
        txtHabitacion.setEditable(false);
        txtValorPagar.setEditable(false);
        txtNumeroTarjeta.setEditable(false);
        txtSaldoDisponible.setEditable(false);
    }

    public void cargarReserva(Reserva reserva) {
        this.reserva = reserva;
        Cliente cliente = reserva.getCliente();
        this.tarjeta = cliente.getTarjetaVirtual();

        txtCodigoReserva.setText(reserva.getCodigo());
        txtHabitacion.setText(reserva.getHabitacion().toString());
        txtValorPagar.setText(String.valueOf(reserva.getValorTotal()));
        txtNumeroTarjeta.setText(tarjeta.getNumero());
        txtSaldoDisponible.setText(String.valueOf(tarjeta.getSaldo()));
    }

    private EstadoReservaState obtenerEstado(Reserva reserva) {
        EstadoReserva estadoEnum = reserva.getEstadoEnum();
        switch (estadoEnum) {
            case CONFIRMADA:
                return new ReservaConfirmadaState();
            case CANCELADA:
                return new ReservaCanceladaState();
            case PENDIENTE:
            default:
                return new ReservaPendienteState();
        }
    }
}