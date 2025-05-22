package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.ClienteTemporal;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private TextField txtTelefono;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onRegistrarse(ActionEvent event) { registrarCliente(); }

    @FXML
    void onVolverAction(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml", "Inicio sesion", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnVolver.getScene().getWindow());
    }

    // Registrar cliente temporalmente (con codigo enviado)
    private void registrarCliente() {
        String cedula = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String email = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String password = txtPassword.getText();
        try {
            ClienteTemporal clienteTemporal = bookYourStayServicio.registrarClienteTemporal(cedula, nombre, telefono, email, password);
            ControladorPrincipal.openView("VerificarCodigoRegistro.fxml", "Verifica Tu Codigo", new Stage());
            ControladorPrincipal.cerrarVentana((Stage) txtPassword.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}

