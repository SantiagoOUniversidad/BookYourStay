package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class EditarDatosControlador {

    private final Cliente cliente = Sesion.getInstancia().getCliente();
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    void onVolver(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("PanelCliente.fxml", "Inicio", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) txtNombre.getScene().getWindow());
    }

    @FXML
    void onGuardar(ActionEvent event) {
        editarDatos();
    }

    public void editarDatos(){
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        try {
            bookYourStayServicio.actualizarCliente(cliente, nombre, telefono, correo);
            controladorPrincipal.crearAlerta("Tu cuenta ha sido editada correctamente", Alert.AlertType.INFORMATION);
            ControladorPrincipal.openView("PanelCliente.fxml", "Inicio", new Stage());
            ControladorPrincipal.cerrarVentana((Stage) txtNombre.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
