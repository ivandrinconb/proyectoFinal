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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class VerPromocionesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<Promocion> listPromocionesCliente;

    @FXML
    void onActionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/proyectofinal/proyectofinal/DashboardCliente.fxml"
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
        // Carga dinámicamente las promociones que el administrador creó y guardó en el hotel
        listPromocionesCliente.getItems().clear();
        listPromocionesCliente.getItems().addAll(
                HotelUQ.getInstancia().getPromociones()
        );
    }
}