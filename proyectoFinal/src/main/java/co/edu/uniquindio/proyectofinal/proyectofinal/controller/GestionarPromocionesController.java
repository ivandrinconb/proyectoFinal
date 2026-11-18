package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionarPromocionesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<?> listPromociones;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDsecuento;

    @FXML
    private TextField txtNombre;

    @FXML
    void onActionCrear(ActionEvent event) {
        String nombre = txtNombre.getText();
        String descuento = txtDsecuento.getText();
        String descripcion = txtDescripcion.getText();

        if (!nombre.isEmpty() && !descuento.isEmpty() && !descripcion.isEmpty()) {
            // Aquí integro la lógica para crear mi promoción en el sistema del hotel
            System.out.println("¡Promoción creada con éxito: " + nombre + " (" + descuento + "%)!");
            onActionLimpiar(event);
        } else {
            System.out.println("Error: Debo completar todos los campos para crear la promoción.");
        }
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        // Aquí se programa la lógica para eliminar la promoción seleccionada de mi lista
        System.out.println("Promoción eliminada correctamente.");
    }

    @FXML
    void onActionLimpiar(ActionEvent event) {
        txtNombre.clear();
        txtDsecuento.clear();
        txtDescripcion.clear();
    }

    @FXML
    void onActionModificar(ActionEvent event) {
        String nombre = txtNombre.getText();
        if (!nombre.isEmpty()) {
            // Aquí integro la lógica para actualizar los datos de la promoción
            System.out.println("Promoción modificada correctamente: " + nombre);
        } else {
            System.out.println("Error: Selecciono o ingreso el nombre de la promoción a modificar.");
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