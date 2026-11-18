package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.MovimientoTarjeta;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TarjetaVirtual;
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

public class TarjetaVirtualController {
    private Cliente cliente;

    private TarjetaVirtual tarjeta;

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
        String montoTexto =
                txtMontoRecarga.getText();

        if (montoTexto.isEmpty()) {

            Alert alerta = new Alert(
                    Alert.AlertType.WARNING
            );

            alerta.setTitle("Campo vacío");

            alerta.setHeaderText(null);

            alerta.setContentText(
                    "Debe ingresar un monto."
            );

            alerta.showAndWait();

            return;
        }

        double monto;

        try {

            monto =
                    Double.parseDouble(
                            montoTexto
                    );

        } catch (NumberFormatException e) {

            Alert alerta = new Alert(
                    Alert.AlertType.ERROR
            );

            alerta.setTitle("Error");

            alerta.setHeaderText(null);

            alerta.setContentText(
                    "El monto debe ser numérico."
            );

            alerta.showAndWait();

            return;
        }

        tarjeta.recargarSaldo(monto);

        txtSaldoDisponible.setText(
                String.valueOf(
                        tarjeta.getSaldo()
                )
        );

        cargarMovimientos();

        txtMontoRecarga.clear();

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("Recarga Exitosa");

        alerta.setHeaderText(null);

        alerta.setContentText(
                "Saldo recargado correctamente."
        );

        alerta.showAndWait();

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

            stage.setScene(
                    new Scene(root)
            );

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    void initialize() {
        txtNumeroTarjeta.setEditable(false);

        txtSaldoDisponible.setEditable(false);

        cliente = SistemaController
                .getInstancia()
                .getClienteSesion();

        tarjeta = cliente.getTarjetaVirtual();

        txtNumeroTarjeta.setText(
                tarjeta.getNumero()
        );

        txtSaldoDisponible.setText(
                String.valueOf(
                        tarjeta.getSaldo()
                )
        );

        cargarMovimientos();
    }

    private void cargarMovimientos() {

        listMovimientos.getItems().clear();

        for (MovimientoTarjeta movimiento :
                tarjeta.getMovimientos()) {

            listMovimientos.getItems().add(
                    movimiento.toString()
            );
        }
    }

}
