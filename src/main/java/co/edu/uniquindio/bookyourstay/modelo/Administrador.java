package co.edu.uniquindio.bookyourstay.modelo;

import java.util.List;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode

public class Administrador extends Persona{

    //Atributos
    private List<Alojamiento> alojamientosAdministrador;


    //Constructor Super
    public Administrador(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }

}
