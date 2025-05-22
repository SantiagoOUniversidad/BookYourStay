package co.edu.uniquindio.bookyourstay.utils;

import java.time.LocalDate;

public interface Factura {

    String getUuid();
    LocalDate getFecha();
    float getSubtotal();
    float getTotal();
    Factura generarFactura(LocalDate fecha, float subtotal, float total);
    void enviarFactura(Factura factura, String email);

}
