package co.edu.uniquindio.bookyourstay.modelo.entidades;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode

public class Administrador extends Persona{

    //Constructor Super
    public Administrador(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }

}
