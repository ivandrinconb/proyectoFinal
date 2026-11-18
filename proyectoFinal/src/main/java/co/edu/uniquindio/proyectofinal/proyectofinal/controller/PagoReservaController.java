package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TarjetaVirtual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

            Stage stage = (Stage)
                    btnCancelar.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    void onActionConfirmarPago(ActionEvent event) {

    }

    @FXML
    void initialize() {
        txtCodigoReserva.setEditable(false);

        txtHabitacion.setEditable(false);

        txtValorPagar.setEditable(false);

        txtNumeroTarjeta.setEditable(false);

        txtSaldoDisponible.setEditable(false);

    }
    public void cargarReserva(
            Reserva reserva) {

        this.reserva = reserva;

        Cliente cliente =
                reserva.getCliente();

        TarjetaVirtual tarjeta =
                cliente.getTarjetaVirtual();

        txtCodigoReserva.setText(
                reserva.getCodigo()
        );

        txtHabitacion.setText(
                reserva.getHabitacion()
                        .toString()
        );

        txtValorPagar.setText(
                String.valueOf(
                        reserva.getValorTotal()
                )
        );

        txtNumeroTarjeta.setText(
                tarjeta.getNumero()
        );

        txtSaldoDisponible.setText(
                String.valueOf(
                        tarjeta.getSaldo()
                )
        );
    }

}
