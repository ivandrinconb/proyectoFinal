package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.HotelUQ;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Promocion;
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
    private ListView<Promocion> listPromociones;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDsecuento;

    @FXML
    private TextField txtNombre;

    @FXML
    void onActionCrear(ActionEvent event) {
        String nombre = txtNombre.getText();
        String descuentoTexto = txtDsecuento.getText();
        String descripcion = txtDescripcion.getText();

        // Validamos si todos los campos están llenos
        if (!nombre.isEmpty() && !descuentoTexto.isEmpty() && !descripcion.isEmpty()) {
            try {
                double porcentajeDescuento = Double.parseDouble(descuentoTexto);

                // Creamos la promoción y la guardamos en la lista global del HotelUQ
                Promocion nuevaPromocion = new Promocion(nombre, descripcion, porcentajeDescuento);
                HotelUQ.getInstancia().getPromociones().add(nuevaPromocion);

                // Actualizamos la vista de la lista
                cargarPromociones();

                // Alerta gráfica de éxito
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Promoción Creada");
                alerta.setHeaderText(null);
                alerta.setContentText("¡La promoción '" + nombre + "' fue creada con éxito (" + porcentajeDescuento + "%)!");
                alerta.showAndWait();

                onActionLimpiar(event);

            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de formato");
                alerta.setHeaderText(null);
                alerta.setContentText("El descuento debe ser un valor numérico válido.");
                alerta.showAndWait();
            }
        } else {
            // Alerta gráfica de advertencia tipo ventana si falta algún campo
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos Incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, debes completar todos los campos para crear la promoción.");
            alerta.showAndWait();
        }
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        Promocion seleccionada = listPromociones.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            HotelUQ.getInstancia().getPromociones().remove(seleccionada);
            cargarPromociones();
            onActionLimpiar(event);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Promoción Eliminada");
            alerta.setHeaderText(null);
            alerta.setContentText("Promoción eliminada correctamente.");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Selección Requerida");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, seleccione una promoción de la lista para eliminar.");
            alerta.showAndWait();
        }
    }

    @FXML
    void onActionLimpiar(ActionEvent event) {
        txtNombre.clear();
        txtDsecuento.clear();
        txtDescripcion.clear();
        listPromociones.getSelectionModel().clearSelection();
    }

    @FXML
    void onActionModificar(ActionEvent event) {
        Promocion seleccionada = listPromociones.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            String nombre = txtNombre.getText();
            String descuentoTexto = txtDsecuento.getText();
            String descripcion = txtDescripcion.getText();

            if (!nombre.isEmpty() && !descuentoTexto.isEmpty() && !descripcion.isEmpty()) {
                try {
                    double porcentaje = Double.parseDouble(descuentoTexto);

                    seleccionada.setNombre(nombre);
                    seleccionada.setPorcentajeDescuento(porcentaje);
                    seleccionada.setDescripcion(descripcion);

                    cargarPromociones();
                    onActionLimpiar(event);

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Promoción Modificada");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Promoción modificada correctamente.");
                    alerta.showAndWait();

                } catch (NumberFormatException e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de formato");
                    alerta.setHeaderText(null);
                    alerta.setContentText("El descuento debe ser un valor numérico.");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Campos Incompletos");
                alerta.setHeaderText(null);
                alerta.setContentText("Complete todos los campos para modificar.");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Selección Requerida");
            alerta.setHeaderText(null);
            alerta.setContentText("Seleccione una promoción de la lista para modificar.");
            alerta.showAndWait();
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
        cargarPromociones();

        // Listener para autocompletar campos al seleccionar una promoción de la lista
        listPromociones.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtNombre.setText(newVal.getNombre());
                txtDsecuento.setText(String.valueOf(newVal.getPorcentajeDescuento()));
                txtDescripcion.setText(newVal.getDescripcion());
            }
        });
    }

    private void cargarPromociones() {
        listPromociones.getItems().clear();
        listPromociones.getItems().addAll(HotelUQ.getInstancia().getPromociones());
    }

}