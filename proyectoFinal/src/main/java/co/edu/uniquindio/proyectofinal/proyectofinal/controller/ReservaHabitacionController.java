package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Habitacion;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Reserva;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.builder.ReservaBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReservaHabitacionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<Cliente> cbCliente;

    @FXML
    private ComboBox<Habitacion> cbHabitacion;

    @FXML
    private DatePicker dateFechaEntrada;

    @FXML
    private DatePicker dateFechaSalida;

    @FXML
    private TextField txtValorTotal;

    @FXML
    void onActionCrearReserva(ActionEvent event) {
        Cliente cliente = cbCliente.getValue();
        Habitacion habitacion = cbHabitacion.getValue();

        LocalDate fechaEntrada = dateFechaEntrada.getValue();
        LocalDate fechaSalida = dateFechaSalida.getValue();

        if(cliente == null ||
                habitacion == null ||
                fechaEntrada == null ||
                fechaSalida == null){

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "Debe completar todos los campos."
            );
            alerta.showAndWait();

            return;
        }

        long noches =
                ChronoUnit.DAYS.between(
                        fechaEntrada,
                        fechaSalida
                );

        if(noches <= 0){

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Fechas incorrectas");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "La fecha de salida debe ser posterior a la fecha de entrada."
            );
            alerta.showAndWait();

            return;
        }

        double valorTotal =
                noches * habitacion.getTarifaBase();

        txtValorTotal.setText(
                String.valueOf(valorTotal)
        );

        Reserva reserva = new ReservaBuilder()
                .setCodigo(generarCodigoReserva())
                .setCliente(cliente)
                .setHabitacion(habitacion)
                .setFechaEntrada(fechaEntrada)
                .setFechaSalida(fechaSalida)
                .setValorTotal(valorTotal)
                .build();

        SistemaController.getInstancia()
                .getHotelUQ()
                .getReservas()
                .add(reserva);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Reserva creada");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "La reserva fue creada correctamente."
        );
        alerta.showAndWait();

        limpiarCampos();

    }

    @FXML
    void onActionVolver(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/dashBoardCliente.fxml"
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
        cbCliente.getItems().addAll(
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getClientes()
        );

    }

    private String generarCodigoReserva() {

        int cantidad = SistemaController.getInstancia()
                .getHotelUQ()
                .getReservas()
                .size() + 1;

        return "R" + cantidad;
    }

    private void limpiarCampos() {

        cbCliente.setValue(null);
        cbHabitacion.setValue(null);

        dateFechaEntrada.setValue(null);
        dateFechaSalida.setValue(null);

        txtValorTotal.clear();
    }

}