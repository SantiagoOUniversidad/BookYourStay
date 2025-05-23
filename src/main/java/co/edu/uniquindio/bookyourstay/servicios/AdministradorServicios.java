package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.repositorio.AdministradorRepositorio;

import java.util.List;

public class AdministradorServicios {
    private final AdministradorRepositorio administradorRepositorio;

    public AdministradorServicios(){
        administradorRepositorio = new AdministradorRepositorio();
    }
    // CONSTANTES
    Exception camposVacios = new Exception("Ningún campo puede estar vacío");

    // Funcion para validar inicio de administrador
    public Administrador validarAdministrador(String id, String password) throws Exception {
        if (id == null || id.isEmpty() || password == null || password.isEmpty()) throw camposVacios;
        for (Administrador administrador : administradorRepositorio.listarAdministradores()){
            if (administrador.getCedula().equals(id) && administrador.getPassword().equals(password)){
                return administrador;
            }
        }
        throw new Exception("No se encontro el administrador");
    }

    public List<Administrador> listarAdministradores() throws Exception {
        return administradorRepositorio.listarAdministradores();
    }

    public void agregarAdministrador(Administrador administrador){
        administradorRepositorio.agregarAdministrador(administrador);
    }
}
