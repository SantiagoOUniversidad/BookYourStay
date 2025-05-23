package co.edu.uniquindio.bookyourstay.modelo.entidades;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode

public class Cliente extends Persona {

    //Sin atributos
    private BilleteraVirtual billeteraVirtual;

    //Constructor Super
    public Cliente(String cedula, String nombre, String telefono, String email, String password, BilleteraVirtual billeteraVirtual) {
        super(cedula, nombre, telefono, email, password);
        this.billeteraVirtual = billeteraVirtual;
    }
}
