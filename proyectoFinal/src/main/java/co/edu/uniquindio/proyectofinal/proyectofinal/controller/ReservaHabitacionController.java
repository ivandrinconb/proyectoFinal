package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.proyectofinal.model.*;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.builder.ReservaBuilder;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator.ReservaComponent;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator.ReservaBase;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.decorator.ServicioAdicionalDecorator;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservaHabitacionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCrearReserva;

    @FXML
    private VBox contenedorServicios;

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

    private Map<CheckBox, ServicioAdicional> serviciosMap =
            new HashMap<>();

    @FXML
    void onActionCrearReserva(ActionEvent event) {
        Cliente cliente = cbCliente.getValue();
        Habitacion habitacion = cbHabitacion.getValue();

        LocalDate fechaEntrada = dateFechaEntrada.getValue();
        LocalDate fechaSalida = dateFechaSalida.getValue();

        if (cliente == null ||
                habitacion == null ||
                fechaEntrada == null ||
                fechaSalida == null) {

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

        if (noches <= 0) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Fechas incorrectas");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "La fecha de salida debe ser posterior a la fecha de entrada."
            );
            alerta.showAndWait();

            return;
        }

        double precioBase =
                noches * habitacion.getTarifaBase();

        ReservaComponent reservaDecorada =
                new ReservaBase(precioBase);

        for (javafx.scene.Node nodo :
                contenedorServicios.getChildren()) {

            if (nodo instanceof CheckBox checkBox
                    && checkBox.isSelected()) {

                ServicioAdicional servicio =
                        serviciosMap.get(checkBox);

                reservaDecorada =
                        new ServicioAdicionalDecorator(
                                reservaDecorada,
                                servicio
                        );
            }
        }

        double valorTotal =
                reservaDecorada.calcularPrecio();

        txtValorTotal.setText(
                String.valueOf(valorTotal)
        );
        for (Reserva reservaExistente :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getReservas()) {

            if (reservaExistente.getHabitacion()
                    .equals(habitacion)

                    &&

                    reservaExistente.getEstad!= EstadoReserva.CANCELADA) {

                boolean fechasSeCruzan =
                        fechaEntrada.isBefore(
                                reservaExistente.getFechaSalida())
                                &&
                                fechaSalida.isAfter(
                                        reservaExistente.getFechaEntrada());

                if (fechasSeCruzan) {

                    Alert alerta = new Alert(
                            Alert.AlertType.WARNING
                    );

                    alerta.setTitle("Habitación ocupada");

                    alerta.setHeaderText(null);

                    alerta.setContentText(
                            "La habitación ya tiene una reserva para esas fechas."
                    );

                    alerta.showAndWait();

                    return;
                }
            }

            Reserva reserva = new ReservaBuilder()
                    .setCodigo(generarCodigoReserva())
                    .setCliente(cliente)
                    .setHabitacion(habitacion)
                    .setFechaEntrada(fechaEntrada)
                    .setFechaSalida(fechaSalida)
                    .setValorTotal(valorTotal)
                    .build();

            for (javafx.scene.Node nodo :
                    contenedorServicios.getChildren()) {

                if (nodo instanceof CheckBox checkBox
                        && checkBox.isSelected()) {

                    ServicioAdicional servicio =
                            serviciosMap.get(checkBox);

                    reserva.getServicios()
                            .add(servicio);
                }
            }

            SistemaController.getInstancia()
                    .getHotelUQ()
                    .getReservas()
                    .add(reserva);

            Alert alerta = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alerta.setTitle("Reserva creada");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "La reserva fue creada correctamente."
            );

            alerta.showAndWait();

            limpiarCampos();
        }
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

        cbHabitacion.getItems().addAll(
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getHabitaciones()
        );

        txtValorTotal.setEditable(false);

        cbHabitacion.setOnAction(
                e -> actualizarValorTotal()
        );

        dateFechaEntrada.setOnAction(
                e -> actualizarValorTotal()
        );

        dateFechaSalida.setOnAction(
                e -> actualizarValorTotal()
        );

        for (ServicioAdicional servicio :
                SistemaController.getInstancia()
                        .getHotelUQ()
                        .getServicios()) {

            CheckBox checkBox =
                    new CheckBox(servicio.toString());

            serviciosMap.put(
                    checkBox,
                    servicio
            );

            checkBox.setOnAction(
                    e -> actualizarValorTotal()
            );

            contenedorServicios
                    .getChildren()
                    .add(checkBox);
        }
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

        contenedorServicios.getChildren().forEach(
                nodo -> {
                    if(nodo instanceof CheckBox checkBox){
                        checkBox.setSelected(false);
                    }
                }
        );
    }

    private void actualizarValorTotal() {

        Habitacion habitacion =
                cbHabitacion.getValue();

        LocalDate fechaEntrada =
                dateFechaEntrada.getValue();

        LocalDate fechaSalida =
                dateFechaSalida.getValue();

        if (habitacion == null ||
                fechaEntrada == null ||
                fechaSalida == null) {

            txtValorTotal.clear();
            return;
        }

        long noches =
                ChronoUnit.DAYS.between(
                        fechaEntrada,
                        fechaSalida
                );

        if (noches <= 0) {

            txtValorTotal.clear();
            return;
        }

        double precioBase =
                noches * habitacion.getTarifaBase();

        ReservaComponent reservaDecorada =
                new ReservaBase(precioBase);

        for (javafx.scene.Node nodo :
                contenedorServicios.getChildren()) {

            if (nodo instanceof CheckBox checkBox
                    && checkBox.isSelected()) {

                ServicioAdicional servicio =
                        serviciosMap.get(checkBox);

                reservaDecorada =
                        new ServicioAdicionalDecorator(
                                reservaDecorada,
                                servicio
                        );
            }
        }

        double valorTotal =
                reservaDecorada.calcularPrecio();

        txtValorTotal.setText(
                String.valueOf(valorTotal)
        );
    }

}