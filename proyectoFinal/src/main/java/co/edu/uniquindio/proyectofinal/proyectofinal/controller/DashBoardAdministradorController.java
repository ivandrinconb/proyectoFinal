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

public class DashBoardAdministradorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnGestionarHabitaciones;

    @FXML
    private Button btnGestionarPromociones;

    @FXML
    private Button btnGestionarReservas;

    @FXML
    private Button btnGestionarServicios;

    @FXML
    private Button btnRegistrarAdministrador;

    @FXML
    private Button btnReportes;

    @FXML
    void onActionGestionarHabitaciones(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/gestionHabitaciones.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnGestionarHabitaciones
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    @FXML
    void onActionGestionarPromociones(ActionEvent event) {

    }

    @FXML
    void onActionGestionarReservas(ActionEvent event) {

    }

    @FXML
    void onActionGestionarServicios(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/gestionServicios.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnGestionarServicios
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }



    }

    @FXML
    void onActionRegistrarAdministrador(ActionEvent event) {

    }

    @FXML
    void onActionReportes(ActionEvent event) {

    }
    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/login.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    @FXML
    void initialize() {

    }

}
