package co.edu.uniquindio.bookyourstay.modelo;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode

public class Cliente extends Persona {

    //sin atributos

    //Constructor Super
    public Cliente(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }
}
