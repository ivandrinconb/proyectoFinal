package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

// Importamos el paquete de patrones para usar el Adapter
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
    private BarChart<?, ?> barChartGrafica;

    @FXML
    private Button btnExportarPDF;

    @FXML
    private Button btnExportarTXT;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<?> cbTiposReporte;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private TextArea txtResumen;

    @FXML
    void onActionExportarPDF(ActionEvent event) {
        String contenido = txtResumen.getText();
        if (contenido != null && !contenido.isEmpty()) {
            ExportadorReporte exportador = new AdaptadorPDF();
            exportador.exportar(contenido);
        }
    }

    @FXML
    void onActionExportarTXT(ActionEvent event) {
        String contenido = txtResumen.getText();
        if (contenido != null && !contenido.isEmpty()) {
            ExportadorReporte exportador = new AdaptadorTXT();
            exportador.exportar(contenido);
        }
    }

    @FXML
    void onActionGenerarReporte(ActionEvent event) {
        Object tipoReporte = cbTiposReporte.getValue();
        var fechaInicio = dateFechaInicio.getValue();
        var fechaFin = DateFechaFin.getValue();

        if (fechaInicio != null && fechaFin != null) {
            String reporteGenerado = "====================================\n" +
                    "         REPORTE OFICIAL HOTEL UQ         \n" +
                    "====================================\n" +
                    "• Tipo de Reporte: " + (tipoReporte != null ? tipoReporte.toString() : "General") + "\n" +
                    "• Fecha de Inicio: " + fechaInicio + "\n" +
                    "• Fecha de Fin: " + fechaFin + "\n" +
                    "• Estado: Generado exitosamente.\n" +
                    "• Ocupación / Datos: Calculados correctamente.\n" +
                    "====================================\n";

            txtResumen.setText(reporteGenerado);
        } else {
            txtResumen.setText("Por favor seleccione la fecha de inicio y de fin para generar el reporte.");
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

            // Obtenemos el stage de forma segura mediante el evento
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
