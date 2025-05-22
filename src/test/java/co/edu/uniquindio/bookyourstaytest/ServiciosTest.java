package co.edu.uniquindio.bookyourstaytest;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.servicios.*;
import co.edu.uniquindio.bookyourstay.utils.EnvioEmail;
import co.edu.uniquindio.bookyourstay.utils.Factura;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiciosTest {

    Factura factura = new FacturaServicios(null, null, 0, 0);

    @Test
    public void crearFacturaTest() {
        LocalDate fecha = LocalDate.now();
        float subtotal = 5000.0f;
        float total = 5000.0f;
        factura.generarFactura(fecha, subtotal, total);
    }

    @Test
    public void enviarFacturaTest() {
        LocalDate fecha = LocalDate.now();
        float subtotal = 155000.0f;
        float total = 155000.0f;
        factura.enviarFactura(factura.generarFactura(fecha, subtotal, total), "olartebuitrago@icloud.com");
    }

}
