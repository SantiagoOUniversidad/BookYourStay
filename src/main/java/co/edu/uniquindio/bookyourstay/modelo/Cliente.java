package co.edu.uniquindio.bookyourstay.modelo;

import lombok.*;
@Getter
@Setter
@ToString
@Builder

public class Cliente extends Persona {

    //sin atributos

    //Constructor Super
    public Cliente(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }
}
