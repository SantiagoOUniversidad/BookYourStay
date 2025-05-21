package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;

public class BookYourStayServicio {
    private final AdministradorServicios administradorServicio;
    private final ClienteServicios clienteServicio;

    public BookYourStayServicio() {
        administradorServicio = new AdministradorServicios();
        clienteServicio = new ClienteServicios();
    }

    public Cliente validarInicioCliente(String id, String password) throws Exception {
        return clienteServicio.validarCliente(id,password);
    }

    public Administrador validarInicioAdministrador(String id, String password) throws Exception {
        return administradorServicio.validarAdministrador(id, password);
    }
}
