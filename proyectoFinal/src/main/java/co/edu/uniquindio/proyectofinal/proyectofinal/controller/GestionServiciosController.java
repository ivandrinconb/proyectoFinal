package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.ServicioAdicional;
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

public class GestionServiciosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<ServicioAdicional> listServiciosAdicionales;

    @FXML
    private Button btnCrearServicio;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtNombreServicioAdicional;

    @FXML
    private TextField txtPrecio;

    @FXML
    void onActionCrearServicio(ActionEvent event) {
        String nombre =
                txtNombreServicioAdicional.getText();

        String precioTexto =
                txtPrecio.getText();

        if(nombre.isEmpty() ||
                precioTexto.isEmpty()){

            Alert alert = new Alert(
                    Alert.AlertType.WARNING
            );

            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Debe completar todos los campos."
            );

            alert.showAndWait();

            return;
        }

        for(ServicioAdicional servicio :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getServicios()){

            if(servicio.getNombre()
                    .equalsIgnoreCase(nombre)){

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle("Servicio Existente");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Ya existe un servicio con ese nombre."
                );

                alert.showAndWait();

                return;
            }
        }

        double precio;

        try {

            precio = Double.parseDouble(
                    precioTexto
            );

        } catch (NumberFormatException e) {

            Alert alert = new Alert(
                    Alert.AlertType.ERROR
            );

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    "El precio debe ser numérico."
            );

            alert.showAndWait();

            return;
        }

        ServicioAdicional servicio =
                new ServicioAdicional(
                        nombre,
                        precio,
                        true
                );

        SistemaController.getInstancia()
                .getHotelUQ()
                .getServicios()
                .add(servicio);

        listServiciosAdicionales
                .getItems()
                .add(servicio);

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle("Servicio Creado");
        alert.setHeaderText(null);
        alert.setContentText(
                "Servicio creado correctamente."
        );

        alert.showAndWait();

        limpiarCampos();
    }

    @FXML
    void onActionVolver(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/DashboardAdministrador.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnVolver
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
        listServiciosAdicionales.getItems().addAll(
                SistemaController
                        .getInstancia()
                        .getHotelUQ()
                        .getServicios()
        );

    }

    private void limpiarCampos(){

        txtNombreServicioAdicional.clear();

        txtPrecio.clear();

    }

}