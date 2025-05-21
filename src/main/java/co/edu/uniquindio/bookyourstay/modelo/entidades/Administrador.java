package co.edu.uniquindio.bookyourstay.modelo.entidades;

import java.util.List;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode

public class Administrador extends Persona{

    //Atributos
    private static Administrador instancia;


    //Constructor Super
    public Administrador(String cedula, String nombre, String telefono, String email, String password) {
        super(cedula, nombre, telefono, email, password);
    }

    public static Administrador getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("El administrador aún no ha sido creado");
        }
        return instancia;
    }

    public static void crearInstancia(String cedula, String nombre, String telefono, String email, String password) {
        if (instancia == null) {
            instancia = Administrador.builder().cedula(cedula).nombre(nombre).telefono(telefono).email(email).password(password).build();
        } else {
            throw new IllegalStateException("Ya existe un administrador");
        }
    }

}
