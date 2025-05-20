package co.edu.uniquindio.bookyourstay.modelo.entidades;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder

public class Persona {

    //Atributos
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String password;

}
