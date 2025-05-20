package co.edu.uniquindio.bookyourstay.controladores;

import javafx.scene.control.Alert;

public class ControladorPrincipal {
    // Crear alerta
    public void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
