package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.ClienteTemporal;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class VerificarCodigoRegistroControlador {

    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private TextField txtCodigoVerificacion;

    @FXML
    private Button btnVerificar;

    @FXML
    void onVerificar(ActionEvent event) {
        registrarCliente();
    }

    private void registrarCliente() {
        String codigoIngresado = txtCodigoVerificacion.getText();
        ClienteTemporal clienteTemporal = ClienteTemporal.clientesTemporales.getFirst();
        try {
            Cliente cliente = bookYourStayServicio.registrarCliente(clienteTemporal, codigoIngresado);
            controladorPrincipal.crearAlerta("Cliente registrado correctamente. Inicia sesion", Alert.AlertType.INFORMATION);
            ControladorPrincipal.openView("InicioSesionCliente.fxml", "Iniciar sesion", new Stage());
            ControladorPrincipal.cerrarVentana((Stage) txtCodigoVerificacion.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
