package co.edu.uniquindio.bookyourstay.controladores;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionAdministradorControlador {
    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    void onIniciarClienteAction(ActionEvent event) throws IOException {
        cambiarACliente();
    }

    @FXML
    void onIniciarSesionAction(ActionEvent event) {
        iniciarSesion();
    }

    // Funcion para validar y manejar el inicio de sesion para administrador
    private void iniciarSesion() {
        try{
            Administrador administrador = bookYourStayServicio.validarInicioAdministrador(txtUsuario.getText(), txtPassword.getText());
            controladorPrincipal.crearAlerta("Inicio de sesion exitoso", Alert.AlertType.INFORMATION);
            ControladorPrincipal.openView("panelAdministrador.fxml", "Bienvenido Administrador", new Stage());
            ControladorPrincipal.cerrarVentana((Stage) txtPassword.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Funcion para cambiar a inicio Cliente
    private void cambiarACliente() throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml", "Inicio sesion", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) txtPassword.getScene().getWindow());
    }
}
