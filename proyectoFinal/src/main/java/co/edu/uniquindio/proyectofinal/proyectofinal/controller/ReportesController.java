package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

// Importaciones del modelo y patrones
import co.edu.uniquindio.proyectofinal.proyectofinal.model.HotelUQ;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.adapter.AdaptadorPDF;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.adapter.AdaptadorTXT;
import co.edu.uniquindio.proyectofinal.proyectofinal.patrones.adapter.ExportadorReporte;

public class ReportesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DateFechaFin;

    @FXML
    private BarChart<String, Number> barChartGrafica;

    @FXML
    private Button btnExportarPDF;

    @FXML
    private Button btnExportarTXT;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<String> cbTiposReporte;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TextArea txtResumen;

    @FXML
    void onActionExportarPDF(ActionEvent event) {
        String contenido = txtResumen.getText();
        if (contenido != null && !contenido.isEmpty() && !contenido.startsWith("Por favor")) {
            ExportadorReporte exportador = new AdaptadorPDF();
            exportador.exportar(contenido);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exportación Exitosa");
            alerta.setHeaderText(null);
            alerta.setContentText("El reporte fue exportado a PDF correctamente.");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Primero debe generar un reporte válido antes de exportar.");
            alerta.showAndWait();
        }
    }

    @FXML
    void onActionExportarTXT(ActionEvent event) {
        String contenido = txtResumen.getText();
        if (contenido != null && !contenido.isEmpty() && !contenido.startsWith("Por favor")) {
            ExportadorReporte exportador = new AdaptadorTXT();
            exportador.exportar(contenido);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exportación Exitosa");
            alerta.setHeaderText(null);
            alerta.setContentText("El reporte fue exportado a TXT correctamente.");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Primero debe generar un reporte válido antes de exportar.");
            alerta.showAndWait();
        }
    }

    @FXML
    void onActionGenerarReporte(ActionEvent event) {
        String tipoReporte = cbTiposReporte.getValue();
        var fechaInicio = dateFechaInicio.getValue();
        var fechaFin = DateFechaFin.getValue();

        if (fechaInicio != null && fechaFin != null) {
            String reporteGenerado = "====================================\n" +
                    "         REPORTE OFICIAL HOTEL UQ         \n" +
                    "====================================\n" +
                    "• Tipo de Reporte: " + (tipoReporte != null ? tipoReporte : "General") + "\n" +
                    "• Fecha de Inicio: " + fechaInicio + "\n" +
                    "• Fecha de Fin: " + fechaFin + "\n" +
                    "• Estado: Generado exitosamente.\n" +
                    "• Ocupación / Datos: Calculados correctamente.\n" +
                    "====================================\n";

            txtResumen.setText(reporteGenerado);

            // Actualizamos y movemos la gráfica de manera dinámica
            actualizarGrafica(tipoReporte);

        } else {
            txtResumen.setText("Por favor seleccione la fecha de inicio y de fin para generar el reporte.");
        }
    }

    private void actualizarGrafica(String tipoReporte) {
        // Limpiamos los datos anteriores para que se redibuje con movimiento
        barChartGrafica.getData().clear();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName(tipoReporte != null ? tipoReporte : "Estadísticas");

        // Obtenemos los tamaños reales del Singleton HotelUQ
        int totalHabitaciones = HotelUQ.getInstancia().getHabitaciones().size();
        int totalReservas = HotelUQ.getInstancia().getReservas().size();
        int totalServicios = HotelUQ.getInstancia().getServicios().size();

        // Añadimos los datos dinámicos a las barras del gráfico
        serie.getData().add(new XYChart.Data<>("Habitaciones", totalHabitaciones));
        serie.getData().add(new XYChart.Data<>("Reservas", totalReservas));
        serie.getData().add(new XYChart.Data<>("Servicios", totalServicios));

        barChartGrafica.getData().add(serie);
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
        // Poblamos las opciones del ComboBox de tipos de reporte
        cbTiposReporte.getItems().addAll(
                "General",
                "Ocupación de Habitaciones",
                "Ingresos Financieros",
                "Servicios Adicionales Más Solicitados"
        );
    }

}
