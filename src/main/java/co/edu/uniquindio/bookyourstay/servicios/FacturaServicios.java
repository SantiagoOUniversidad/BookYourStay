package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.utils.EnvioEmailConImagen;
import co.edu.uniquindio.bookyourstay.utils.Factura;
import co.edu.uniquindio.bookyourstay.utils.GenerarQr;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class FacturaServicios implements Factura {

    private LocalDate fecha;
    private String uuid;
    private float subtotal;
    private float total;

    public Factura generarFactura(LocalDate fecha, float subtotal, float total) {
        String uuid = UUID.randomUUID().toString();
        try {
            GenerarQr.generarCodigoQR(uuid, uuid, 300, 300);
            Factura factura = new FacturaServicios(fecha, uuid, subtotal, total);
            return factura;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void enviarFactura(Factura factura, String email) {
        EnvioEmailConImagen envio = new EnvioEmailConImagen();
        String rutaQR = "qrCodes/" + factura.getUuid() + ".png";
        LocalDate fecha = factura.getFecha();
        float subtotal = factura.getSubtotal();
        float total = factura.getTotal();
        String mensaje = "Gracias por tu reserva.\n Adjuntamos tu código QR con los detalles. \n\n FACTURA: \n " + "Fecha: " + fecha + "\n" + "Subtotal: " + subtotal + "\n" + "Total: " + total + "\n\n";
        try {
            envio.enviarCorreo(email, "Confirmacion de tu reserva", mensaje, rutaQR);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
