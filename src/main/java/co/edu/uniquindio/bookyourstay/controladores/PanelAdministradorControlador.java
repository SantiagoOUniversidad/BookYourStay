package co.edu.uniquindio.bookyourstay.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PanelAdministradorControlador {
    @FXML
    private StackPane panelPrincipal;

    @FXML
    void cambiarContrasena(ActionEvent event) {

    }

    @FXML
    void gestionarAlojamientos(ActionEvent event) {
        Parent node = cargarPanel("/co/edu/uniquindio/bookyourstay/GestionarAlojamientos.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    void gestionarOfertas(ActionEvent event) {

    }

    @FXML
    void obtenerEstadisticas(ActionEvent event) {

    }

    @FXML
    private Button btnVolver;

    @FXML
    void onVolver(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml", "Iniciar Sesion", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnVolver.getScene().getWindow());
    }

    // Metodo para actualizar el panel principal
    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
