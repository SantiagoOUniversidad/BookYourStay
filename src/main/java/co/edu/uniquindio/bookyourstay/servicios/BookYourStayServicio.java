package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.ClienteTemporal;

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

    public ClienteTemporal registrarClienteTemporal(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        return clienteServicio.crearClienteSinVerificacion(cedula, nombre, telefono, email, password);
    }

    public Cliente registrarCliente(ClienteTemporal clienteTemporal, String codigoIngresado) throws Exception {
        return clienteServicio.crearClienteVerificado(clienteTemporal, codigoIngresado);
    }

    public void eliminarCliente(String cedula) throws Exception {
        clienteServicio.eliminarCliente(cedula);
    }

    public Cliente actualizarCliente(Cliente clienteActualizar, String nombre, String telefono, String email) throws Exception {
        return clienteServicio.actualizarCliente(clienteActualizar, nombre, telefono, email);
    }

    public Administrador validarInicioAdministrador(String id, String password) throws Exception {
        return administradorServicio.validarAdministrador(id, password);
    }
}
