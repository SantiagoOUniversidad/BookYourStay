package co.edu.uniquindio.bookyourstay.modelo;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@Builder

public class Administrador extends Persona{

    //Atributos
    private List<Alojamiento> alojamientosAdministrador;


    //Constructor Super
    public Administrador(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }
}
