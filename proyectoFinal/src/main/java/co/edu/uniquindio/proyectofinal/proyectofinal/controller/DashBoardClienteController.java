package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashBoardClienteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnMisPuntos;

    @FXML
    private Button btnMisReservas;

    @FXML
    private Button btnReservarHabitacion;

    @FXML
    private Button btnTarjetaVirtual;

    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        abrirVentana("Login.fxml");

    }

    @FXML
    void onActionMisPuntos(ActionEvent event) {
        abrirVentana("misPuntos.fxml");

    }

    @FXML
    void onActionMisReservas(ActionEvent event) {
        abrirVentana("misReservas.fxml");

    }

    @FXML
    void onActionReservarHabitacion(ActionEvent event) {
        abrirVentana("reservaHabitacion.fxml");

    }

    @FXML
    void onActionTarjetaVirtual(ActionEvent event) {
        abrirVentana("tarjetaVirtual.fxml");

    }

    @FXML
    void initialize() {

    }
    private void abrirVentana(String nombreFXML) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/" + nombreFXML
                    )
            );

            Parent root = loader.load();

            Stage stage =
                    (Stage) btnReservarHabitacion
                            .getScene()
                            .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
