package co.edu.uniquindio.bookyourstay.repositorio;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdministradorRepositorio {
    private final List<Administrador> administradores;

    public AdministradorRepositorio() {
        administradores = new ArrayList<>();
    }

    public void agregarAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }

    public void eliminarAdministrador(Administrador administrador) {
        administradores.remove(administrador);
    }

    public List<Administrador> listarAdministradores() {
        return administradores;
    }
}
