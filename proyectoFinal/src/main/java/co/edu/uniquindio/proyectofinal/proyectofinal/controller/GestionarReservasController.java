package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.HotelUQ;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private ComboBox<Reserva> cbSeleccionarReserva;

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
        // Validamos si la lista de reservas está vacía
        if (HotelUQ.getInstancia().getReservas().isEmpty()) {
            Alert alertaVacia = new Alert(Alert.AlertType.WARNING);
            alertaVacia.setTitle("Sin Reservas");
            alertaVacia.setHeaderText(null);
            alertaVacia.setContentText("No hay reservas registradas en el sistema para cancelar.");
            alertaVacia.showAndWait();
            return;
        }

        var reservaSeleccionada = cbSeleccionarReserva.getValue();

        if (reservaSeleccionada != null) {
            HotelUQ.getInstancia().getReservas().remove(reservaSeleccionada);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Reserva Cancelada");
            alerta.setHeaderText(null);
            alerta.setContentText("La reserva fue cancelada y eliminada exitosamente del sistema.");
            alerta.showAndWait();

            cargarReservas();
            onActionLimpiar(event);
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Selección Requerida");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, seleccione una reserva de la lista para cancelar.");
            alerta.showAndWait();
        }
    }

    @FXML
    void onActionGuardarCambios(ActionEvent event) {
        // 1. Validamos si la lista de reservas está vacía
        if (HotelUQ.getInstancia().getReservas().isEmpty()) {
            Alert alertaVacia = new Alert(Alert.AlertType.WARNING);
            alertaVacia.setTitle("Sin Reservas");
            alertaVacia.setHeaderText(null);
            alertaVacia.setContentText("No hay reservas registradas en el sistema para modificar. Por favor, crea una reserva primero.");
            alertaVacia.showAndWait();
            return;
        }

        var reservaSeleccionada = cbSeleccionarReserva.getValue();
        String nuevoEstado = cbEstado.getValue();
        var fechaEntrada = dateFechaEntrada.getValue();
        var fechaSalida = dateFechaSalida.getValue();
        String valorTotal = txtValorTotal.getText();

        if (reservaSeleccionada != null) {

            // 2. Validamos si hay campos vacíos o sin seleccionar
            if (nuevoEstado == null || fechaEntrada == null || fechaSalida == null || valorTotal.isEmpty()) {
                Alert alertaCampos = new Alert(Alert.AlertType.WARNING);
                alertaCampos.setTitle("Campos Incompletos");
                alertaCampos.setHeaderText(null);
                alertaCampos.setContentText("Por favor, debes completar todos los campos obligatorios para guardar los cambios.");
                alertaCampos.showAndWait();
                return;
            }

            // Lógica para actualizar los datos de la reserva en el hotel
            Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
            alertaExito.setTitle("Cambios Guardados");
            alertaExito.setHeaderText(null);
            alertaExito.setContentText("¡Cambios guardados correctamente para la reserva seleccionada!");
            alertaExito.showAndWait();

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Selección Requerida");
            alerta.setHeaderText(null);
            alerta.setContentText("Error: Debo seleccionar una reserva para guardar los cambios.");
            alerta.showAndWait();
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

        // Cargamos las reservas reales desde el Singleton de HotelUQ
        cargarReservas();

        // Configuro el listener para autocompletar los campos al seleccionar una reserva real
        cbSeleccionarReserva.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                // Si tu clase Reserva tiene métodos para obtener estos datos, los asignas aquí:
                // txtCliente.setText(newValue.getCliente().getNombre());
                // txtHabitacion.setText(newValue.getHabitacion().getNumero());
                // txtValorTotal.setText(String.valueOf(newValue.getValorTotal()));
                // cbEstado.setValue(newValue.getEstado().toString());
            }
        });
    }

    private void cargarReservas() {
        cbSeleccionarReserva.getItems().clear();
        cbSeleccionarReserva.getItems().addAll(HotelUQ.getInstancia().getReservas());
    }

}