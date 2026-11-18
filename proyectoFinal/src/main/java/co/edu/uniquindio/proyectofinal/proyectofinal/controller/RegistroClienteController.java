package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.CuentaPuntos;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TarjetaVirtual;
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

public class RegistroClienteController {

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
        String identificacion = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();

        if (identificacion.isEmpty() ||
                nombre.isEmpty() ||
                correo.isEmpty() ||
                contrasena.isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos Vacíos");
            alerta.setHeaderText(null);
            alerta.setContentText("Debe completar todos los campos.");
            alerta.showAndWait();

            return;
        }
        for (Cliente clienteExistente :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getClientes()) {

            if (clienteExistente.getCorreo()
                    .equalsIgnoreCase(correo)) {

                Alert alerta = new Alert(
                        Alert.AlertType.WARNING
                );

                alerta.setTitle("Correo existente");

                alerta.setHeaderText(null);

                alerta.setContentText(
                        "Ya existe un cliente con ese correo."
                );

                alerta.showAndWait();

                return;
            }
        }

        TarjetaVirtual tarjetaVirtual =
                new TarjetaVirtual(
                        "TV" + identificacion,
                        0
                );

        CuentaPuntos cuentaPuntos =
                new CuentaPuntos();

        Cliente cliente = new Cliente(
                identificacion,
                nombre,
                correo,
                contrasena,
                tarjetaVirtual,
                cuentaPuntos
        );

        SistemaController.getInstancia()
                .getHotelUQ()
                .getClientes()
                .add(cliente);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Registro Exitoso");
        alerta.setHeaderText(null);
        alerta.setContentText("Cliente registrado correctamente.");
        alerta.showAndWait();

        limpiarCampos();
    }

    @FXML
    void onActionVolver(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/Login.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    @FXML
    void initialize() {

    }

    private void limpiarCampos(){

        txtNombre.clear();
        txtIdentificacion.clear();
        txtCorreo.clear();
        txtContrasena.clear();

    }



}