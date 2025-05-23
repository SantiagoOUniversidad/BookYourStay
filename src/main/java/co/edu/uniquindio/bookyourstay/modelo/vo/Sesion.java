package co.edu.uniquindio.bookyourstay.modelo.vo;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.factory.Hotel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Sesion {
    // Singleton
    public static Sesion INSTANCIA;
    @Getter @Setter
    private Cliente cliente;
    @Getter @Setter
    private List<Habitacion> habitaciones = new ArrayList<>();
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

    public void limpiarLista(){
        habitaciones.clear();
    }
}
