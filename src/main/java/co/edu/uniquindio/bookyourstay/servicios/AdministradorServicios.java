package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.BookYourStay;


public class AdministradorServicios {

    private final BookYourStay bookYourStay = BookYourStay.getInstancia();

    //CONSTANTES
    Exception camposVacios = new Exception("Ningún campo puede estar vacío");

    //CRUD ADMINISTRADOR

    public Administrador crearAdministrador(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        if (cedula == null || nombre == null || telefono == null || email == null || password == null || cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw camposVacios;
        }
        try {
            Administrador.getInstancia();
            throw new Exception("El administrador ya existe");
        } catch (IllegalStateException e) {
            Administrador.crearInstancia(cedula, nombre, telefono, email, password);
            return Administrador.getInstancia();
        }
    }

    public Administrador buscarAdministrador() throws Exception {
        return Administrador.getInstancia();
    }
}
