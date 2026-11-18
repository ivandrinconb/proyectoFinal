package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.CuentaPuntos;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.MovimientoPuntos;
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

public class MisPuntosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private ListView<String> listHistorialPuntos;

    @FXML
    private Button btnCanjearPuntos;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtPuntosDisponibles;

    @FXML
    void onActionCanjearPuntos(ActionEvent event) {
        Cliente cliente =
                SistemaController.getInstancia()
                        .getClienteSesion();

        CuentaPuntos cuenta =
                cliente.getCuentaPuntos();

        int puntosDisponibles =
                cuenta.calcularPuntosDisponibles();

        if (puntosDisponibles < 100) {

            Alert alerta = new Alert(
                    Alert.AlertType.WARNING
            );

            alerta.setTitle("Puntos insuficientes");

            alerta.setHeaderText(null);

            alerta.setContentText(
                    "Debe tener al menos 100 puntos para realizar un canje."
            );

            alerta.showAndWait();

            return;
        }

        cuenta.consumirPuntos(100);

        cliente.getTarjetaVirtual()
                .recargarSaldo(1000);

        txtPuntosDisponibles.setText(
                String.valueOf(
                        cuenta.calcularPuntosDisponibles()
                )
        );

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("Canje exitoso");

        alerta.setHeaderText(null);

        alerta.setContentText(
                "Se acreditaron $1000 a su Tarjeta Virtual."
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
        txtPuntosDisponibles.setEditable(false);
        Cliente cliente =
                SistemaController.getInstancia()
                        .getClienteSesion();

        CuentaPuntos cuenta =
                cliente.getCuentaPuntos();

        txtPuntosDisponibles.setText(
                String.valueOf(
                        cuenta.calcularPuntosDisponibles()
                )
        );

        listHistorialPuntos.getItems().clear();

        for (MovimientoPuntos movimiento :
                cuenta.getMovimientos()) {

            listHistorialPuntos.getItems().add(
                    movimiento.toString()
            );

            cargarDatos();

        }

    }
    private void cargarDatos() {

        Cliente cliente =
                SistemaController.getInstancia()
                        .getClienteSesion();

        CuentaPuntos cuenta =
                cliente.getCuentaPuntos();

        txtPuntosDisponibles.setText(
                String.valueOf(
                        cuenta.calcularPuntosDisponibles()
                )
        );

        listHistorialPuntos.getItems().clear();

        for (MovimientoPuntos movimiento :
                cuenta.getMovimientos()) {

            listHistorialPuntos.getItems().add(
                    movimiento.toString()
            );
        }
    }


}
