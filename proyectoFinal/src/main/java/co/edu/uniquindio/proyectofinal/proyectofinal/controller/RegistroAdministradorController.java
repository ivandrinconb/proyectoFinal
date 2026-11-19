package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Administrador;
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

public class RegistroAdministradorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVolver;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    void onActionRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        // Validamos que los campos no estén vacíos
        if (nombre.isBlank() || identificacion.isBlank() || correo.isBlank() || contrasena.isBlank()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos Vacíos");
            alerta.setHeaderText(null);
            alerta.setContentText("Debe completar todos los campos para registrar un administrador.");
            alerta.showAndWait();
            return;
        }

        // Creamos el objeto Administrador real
        Administrador nuevoAdmin = new Administrador(identificacion, nombre, correo, contrasena);

        // Lo guardamos en la lista global del hotel usando el SistemaController
        SistemaController.getInstancia()
                .getHotelUQ()
                .getAdministradores()
                .add(nuevoAdmin);

        // Mostramos la ventana de éxito (Alert en lugar de consola)
        Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
        alertaExito.setTitle("Registro Exitoso");
        alertaExito.setHeaderText(null);
        alertaExito.setContentText("¡Administrador registrado con éxito: " + nombre + " (" + correo + ") !");
        alertaExito.showAndWait();

        // Limpiamos los campos después de registrar
        limpiarCampos();
    }

    @FXML
    void onActionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/dashBoardAdministrador.fxml"
                    )
            );
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtCorreo.clear();
        txtContrasena.clear();
    }

    @FXML
    void initialize() {

    }

}
