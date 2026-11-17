package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Administrador;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    void onActionIniciarSesion(ActionEvent event) {

        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        if (correo.isBlank() || contrasena.isBlank()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos vacíos");
            alerta.setHeaderText(null);
            alerta.setContentText("Debe ingresar correo y contraseña.");
            alerta.showAndWait();

            return;
        }

        for (Cliente cliente :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getClientes()) {
            SistemaController.getInstancia()
                    .setClienteSesion(cliente);

            if (cliente.getCorreo().equals(correo) &&
                    cliente.getPassword().equals(contrasena)) {

                try {

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/proyectofinal/proyectofinal/DashboardCliente.fxml"
                            )
                    );

                    Parent root = loader.load();

                    Stage stage = (Stage) btnIniciarSesion
                            .getScene()
                            .getWindow();

                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return;
            }
        }
        for (Administrador administrador :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getAdministradores()) {

            if (administrador.getCorreo().equals(correo)
                    &&
                    administrador.getPassword().equals(contrasena)) {

                try {

                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/proyectofinal/proyectofinal/DashboardAdministrador.fxml"
                            )
                    );

                    Parent root = loader.load();

                    Stage stage = (Stage) btnIniciarSesion
                            .getScene()
                            .getWindow();

                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                return;
            }
            }

        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de inicio de sesión");
        alerta.setHeaderText(null);
        alerta.setContentText("Correo o contraseña incorrectos.");
        alerta.showAndWait();
    }

    @FXML
    void onActionRegistrarse(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/RegistroCliente.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnRegistrarse.getScene().getWindow();

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
