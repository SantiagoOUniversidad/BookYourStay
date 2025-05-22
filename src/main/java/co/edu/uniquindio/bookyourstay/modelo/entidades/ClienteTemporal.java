package co.edu.uniquindio.bookyourstay.modelo.entidades;

import lombok.*;
import lombok.experimental.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode

public class ClienteTemporal extends Persona {
    public static List<ClienteTemporal> clientesTemporales = new ArrayList<>();

    //Constructor Super
    public ClienteTemporal(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }
}
