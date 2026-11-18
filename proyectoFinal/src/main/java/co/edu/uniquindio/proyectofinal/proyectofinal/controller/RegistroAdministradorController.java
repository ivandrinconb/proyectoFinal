package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        if (!nombre.isEmpty() && !identificacion.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {

            // TODO se conecta aquí con el modelo o gestor principal del HotelUQ si se requiere
            // HotelUQ.getInstancia().registrarAdministrador(nombre, identificacion, correo, contrasena);

            System.out.println("¡Administrador registrado con éxito: " + nombre + " (" + correo + ") !");

            // Limpiamos los campos después de registrar
            txtNombre.clear();
            txtIdentificacion.clear();
            txtCorreo.clear();
            txtContrasena.clear();

        } else {
            System.out.println("Error: Todos los campos son obligatorios para registrar un administrador.");
        }
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

    @FXML
    void initialize() {

    }

}
