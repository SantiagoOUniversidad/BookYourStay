package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.CambiarContrasenaServicios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BilleteraClienteControlador {

    private final Cliente cliente = Sesion.getInstancia().getCliente();
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    CambiarContrasenaServicios generarCodigo = new CambiarContrasenaServicios();

    @FXML
    private Label lblNumeroBilletera;

    @FXML
    private Label lblSaldoBilletera;

    @FXML
    private Button btnRecargar;

    @FXML
    private TextField txtRecargar;

    @FXML
    void onRecargar(ActionEvent event) throws IOException {
        recargarBilletera(Float.parseFloat(txtRecargar.getText()));
    }

    @FXML
    public void initialize(){
        if (cliente.getBilleteraVirtual() == null) {
            BilleteraVirtual billeteraVirtual = BilleteraVirtual.builder().numero(generarCodigo.generarCodigo()).saldo(0).build();
            cliente.setBilleteraVirtual(billeteraVirtual);
            lblNumeroBilletera.setText(String.valueOf(cliente.getBilleteraVirtual().getNumero()));
            lblSaldoBilletera.setText(String.valueOf(cliente.getBilleteraVirtual().getSaldo()));
        } else {
            lblNumeroBilletera.setText(String.valueOf(cliente.getBilleteraVirtual().getNumero()));
            lblSaldoBilletera.setText(String.valueOf(cliente.getBilleteraVirtual().getSaldo()));
        }

    }

    public void recargarBilletera(float recarga) throws IOException {
        BilleteraVirtual billetera = cliente.getBilleteraVirtual();
        billetera.setSaldo(billetera.getSaldo() + recarga);
        lblSaldoBilletera.setText(String.valueOf(billetera.getSaldo()));
        controladorPrincipal.crearAlerta("Recarga exitosa", Alert.AlertType.INFORMATION);
        ControladorPrincipal.openView("PanelCliente.fxml", "Inicio", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnRecargar.getScene().getWindow());
    }
}
