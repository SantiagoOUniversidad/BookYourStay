package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BilleteraVirtual;

public class BilleteraServicios {

    public void cargarBilletera(BilleteraVirtual billetera, float saldo){
        billetera.setSaldo(saldo);
    }

}
