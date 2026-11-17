package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.EstadoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.TipoHabitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory.HabitacionSuiteFactory;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory.HabitacionFactory;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory.HabitacionEstandarFactory;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.factory.HabitacionFamiliarFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GestionHabitacionesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrearHabitacion;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<EstadoHabitacion> cbEstado;

    @FXML
    private ComboBox<TipoHabitacion> cbTipoHabitacion;

    @FXML
    private ListView<Habitacion> listHabitaciones;

    @FXML
    private TextField txtNumeroHabitacion;

    @FXML
    private TextField txtTarifaBase;

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
    void onActionlCrearHabitacion(ActionEvent event) {
        String numero = txtNumeroHabitacion.getText();

        String tarifaTexto = txtTarifaBase.getText();

        EstadoHabitacion estado = cbEstado.getValue();

        TipoHabitacion tipoHabitacion =
                cbTipoHabitacion.getValue();

        if(numero.isBlank()
                || tarifaTexto.isBlank()
                || estado == null
                || tipoHabitacion == null){

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

        double tarifaBase;

        try {

            tarifaBase =
                    Double.parseDouble(
                            tarifaTexto
                    );

        } catch (NumberFormatException e) {

            Alert alert = new Alert(
                    Alert.AlertType.ERROR
            );

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    "La tarifa debe ser numérica."
            );

            alert.showAndWait();

            return;
        }

        HabitacionFactory factory = null;

        switch (tipoHabitacion){

            case ESTANDAR:

                factory =
                        new HabitacionEstandarFactory();

                break;

            case FAMILIAR:

                factory =
                        new HabitacionFamiliarFactory();

                break;

            case SUITE:

                factory =
                        new HabitacionSuiteFactory();

                break;
        }
        for (Habitacion habitacionExistente :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getHabitaciones()) {

            if (habitacionExistente.getNumero()
                    .equalsIgnoreCase(numero)) {

                Alert alerta = new Alert(
                        Alert.AlertType.WARNING
                );

                alerta.setTitle("Habitación existente");

                alerta.setHeaderText(null);

                alerta.setContentText(
                        "Ya existe una habitación con ese número."
                );

                alerta.showAndWait();

                return;
            }
        }

        Habitacion habitacion =
                factory.crearHabitacion(
                        numero,
                        tarifaBase,
                        estado
                );

        SistemaController
                .getInstancia()
                .getHotelUQ()
                .getHabitaciones()
                .add(habitacion);

        listHabitaciones.getItems()
                .add(habitacion);

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle("Habitación Creada");
        alert.setHeaderText(null);
        alert.setContentText(
                "La habitación fue creada correctamente."
        );

        alert.showAndWait();

        limpiarCampos();

    }

    @FXML
    void initialize() {
        cbEstado.getItems().addAll(
                EstadoHabitacion.values()
        );

        cbTipoHabitacion.getItems().addAll(
                TipoHabitacion.values()
        );

        listHabitaciones.getItems().addAll(
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getHabitaciones()
        );
    }


    private void limpiarCampos(){

        txtNumeroHabitacion.clear();

        txtTarifaBase.clear();

        cbEstado.setValue(null);

        cbTipoHabitacion.setValue(null);

    }
}
