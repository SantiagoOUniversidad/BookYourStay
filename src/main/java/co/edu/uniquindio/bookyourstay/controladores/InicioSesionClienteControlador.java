package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionClienteControlador {
    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsuario;

    @FXML
    void onIniciarAdministradorAction(ActionEvent event) throws IOException {
        iniciarAdministrador();
    }

    @FXML
    void onIniciarSesionAction(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    void onRealizarRegistroAction(ActionEvent event) throws IOException {
        abrirRegistro();
    }

    // Funcion para validar y manejar inicio de sesion para Cliente
    private void iniciarSesion() {
        try{
            Cliente cliente = bookYourStayServicio.validarInicioCliente(txtUsuario.getText(), txtPassword.getText());
            controladorPrincipal.crearAlerta("Inicio de sesion exitoso", Alert.AlertType.INFORMATION);
            Sesion sesion = Sesion.getInstancia();
            sesion.setCliente(cliente);
            ControladorPrincipal.openView("PanelCliente.fxml","Bienvenido",new Stage());
            ControladorPrincipal.cerrarVentana((Stage) txtUsuario.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Funcion cambiar pantalla de registro
    private void abrirRegistro() throws IOException {
        ControladorPrincipal.openView("registroCliente.fxml", "Registro", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) txtUsuario.getScene().getWindow());
    }

    // Funcion cambiar pantalla administrador
    private void iniciarAdministrador() throws IOException {
        ControladorPrincipal.openView("InicioSesionAdministrador.fxml", "Inicio Sesion", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) txtUsuario.getScene().getWindow());
    }
}

