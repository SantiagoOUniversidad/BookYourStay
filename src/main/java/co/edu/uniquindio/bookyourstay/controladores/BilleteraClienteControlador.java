package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.CambiarContrasenaServicios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BilleteraClienteControlador {

    private final Cliente cliente = Sesion.getInstancia().getCliente();
    CambiarContrasenaServicios generarCodigo = new CambiarContrasenaServicios();

    @FXML
    private Label lblNumeroBilletera;

    @FXML
    private Label lblSaldoBilletera;

    @FXML
    private Button btnRecargar;

    @FXML
    void onRecargar(ActionEvent event) {
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

}
