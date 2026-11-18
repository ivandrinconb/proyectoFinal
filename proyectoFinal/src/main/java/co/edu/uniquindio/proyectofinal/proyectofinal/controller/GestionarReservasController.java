package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionarReservasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelarReserva;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private ComboBox<String> cbSeleccionarReserva;

    @FXML
    private DatePicker dateFechaEntrada;

    @FXML
    private DatePicker dateFechaSalida;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtHabitacion;

    @FXML
    private TextField txtValorTotal;

    @FXML
    void onActionCancelarReserva(ActionEvent event) {
        // Aquí programo la lógica para cancelar la reserva seleccionada en mi sistema
        System.out.println("Reserva cancelada exitosamente.");
    }

    @FXML
    void onActionGuardarCambios(ActionEvent event) {
        String reservaSeleccionada = cbSeleccionarReserva.getValue();
        String nuevoEstado = cbEstado.getValue();
        var fechaEntrada = dateFechaEntrada.getValue();
        var fechaSalida = dateFechaSalida.getValue();

        if (reservaSeleccionada != null) {
            // Aquí integro la lógica para actualizar los datos de mi reserva en el hotel
            System.out.println("¡Cambios guardados correctamente para la reserva seleccionada!");
        } else {
            System.out.println("Error: Debo seleccionar una reserva para guardar los cambios.");
        }
    }

    @FXML
    void onActionLimpiar(ActionEvent event) {
        txtCliente.clear();
        txtHabitacion.clear();
        txtValorTotal.clear();
        dateFechaEntrada.setValue(null);
        dateFechaSalida.setValue(null);
        cbEstado.setValue(null);
        cbSeleccionarReserva.setValue(null);
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
        // Cargo los estados posibles para una reserva en el ComboBox correspondiente
        cbEstado.getItems().addAll("Activa", "Pendiente", "Cancelada", "Completada");

        // Cargo los datos iniciales necesarios para demostrar el funcionamiento operativo de la aplicación
        cbSeleccionarReserva.getItems().addAll("Reserva #001 - Juan Pérez", "Reserva #002 - María Gómez");

        // Configuro el listener para que al seleccionar una reserva de la lista se autocompleten los campos
        cbSeleccionarReserva.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.contains("Juan Pérez")) {
                    txtCliente.setText("Juan Pérez");
                    txtHabitacion.setText("Habitación 102");
                    txtValorTotal.setText("250000");
                    cbEstado.setValue("Activa");
                } else if (newValue.contains("María Gómez")) {
                    txtCliente.setText("María Gómez");
                    txtHabitacion.setText("Habitación 205");
                    txtValorTotal.setText("400000");
                    cbEstado.setValue("Pendiente");
                }
            }
        });
    }

}