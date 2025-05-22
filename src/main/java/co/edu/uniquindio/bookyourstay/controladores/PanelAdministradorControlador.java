package co.edu.uniquindio.bookyourstay.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
public class PanelAdministradorControlador {
    @FXML
    private StackPane panelPrincipal;

    @FXML
    void cambiarContrasena(ActionEvent event) {

    }

    @FXML
    void gestionarAlojamientos(ActionEvent event) {
        Parent node = cargarPanel("GestionarAlojamientos.fxml");
        panelPrincipal.getChildren().setAll(node);
    }

    @FXML
    void gestionarOfertas(ActionEvent event) {

    }

    @FXML
    void obtenerEstadisticas(ActionEvent event) {

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
