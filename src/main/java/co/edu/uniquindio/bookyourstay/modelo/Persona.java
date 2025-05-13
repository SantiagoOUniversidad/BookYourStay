package co.edu.uniquindio.bookyourstay.modelo;

import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class Persona {

    //Atributos
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String password;

}
