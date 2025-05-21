package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroClienteControlador {
    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onRegistrarse(ActionEvent event) {
       // registrarse();
    }

    @FXML
    void onVolverAction(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml", "Inicio sesion", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnVolver.getScene().getWindow());
    }

}

