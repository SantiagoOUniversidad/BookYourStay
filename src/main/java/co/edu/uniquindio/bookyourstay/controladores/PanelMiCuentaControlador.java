package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PanelMiCuentaControlador {

    private final Cliente cliente = Sesion.getInstancia().getCliente();
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private Button btnEditarDatos;

    @FXML
    private Button btnEliminarCuenta;

    @FXML
    private Button btnVolver;

    @FXML
    void onEditarDatosAction(ActionEvent event) {
    }

    @FXML
    void onEliminarCuentaAction(ActionEvent event) {
        eliminarCliente();
    }

    @FXML
    void onVolver(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("PanelCliente.fxml", "Inicio", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnEditarDatos.getScene().getWindow());
    }

    private void eliminarCliente() {
        String cedula = cliente.getCedula();
        try {
            bookYourStayServicio.eliminarCliente(cedula);
            controladorPrincipal.crearAlerta("Tu cuenta ha sido eliminada correctamente", Alert.AlertType.INFORMATION);
            ControladorPrincipal.openView("InicioSesionCliente.fxml", "Iniciar sesion", new Stage());
            ControladorPrincipal.cerrarVentana((Stage) btnEditarDatos.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
