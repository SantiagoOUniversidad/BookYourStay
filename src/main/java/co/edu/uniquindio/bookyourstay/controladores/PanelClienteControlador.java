package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelClienteControlador implements Initializable {

    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    private final Cliente cliente = Sesion.getInstancia().getCliente();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTxtBienvenido();
    }

    @FXML
    private Button btnMiCuenta;

    @FXML
    private Label txtBienvenido;

    @FXML
    void onMiCuentaAction(ActionEvent event) {
        try {
            ControladorPrincipal.openView("PanelMiCuenta.fxml", "Mi Cuenta", new Stage());
            ControladorPrincipal.cerrarVentana((Stage) btnMiCuenta.getScene().getWindow());
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onMisReservasAction(ActionEvent event) {

    }

    @FXML
    void onReservarAction(ActionEvent event) {

    }

    @FXML
    void onReseñaAction(ActionEvent event) {

    }

    @FXML
    void onVolver(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml", "Inicio Sesion", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnMiCuenta.getScene().getWindow());
    }

    @FXML
    void setTxtBienvenido(){
        txtBienvenido.setText("Bienvenido, " + cliente.getNombre());
    }

}
