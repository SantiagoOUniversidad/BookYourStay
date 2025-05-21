package co.edu.uniquindio.bookyourstay.modelo.vo;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import lombok.Getter;
import lombok.Setter;

public class Sesion {
    // Singleton
    public static Sesion INSTANCIA;
    @Getter @Setter
    private Cliente cliente;
    private Sesion() {}

    // Getter de nuestra instancia
    public static Sesion getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Sesion();
        }
        return INSTANCIA;
    }

    // Metodo para cerrar sesion
    public void cerrarSesion(){
        cliente = null;
    }

}
