package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.ClienteTemporal;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import javafx.scene.image.Image;

import java.util.List;

public class BookYourStayServicio {
    private final AdministradorServicios administradorServicio;
    private final ClienteServicios clienteServicio;
    private final AlojamientoServicio alojamientoServicio;

    public BookYourStayServicio() {
        administradorServicio = new AdministradorServicios();
        clienteServicio = new ClienteServicios();
        alojamientoServicio = new AlojamientoServicio();
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

    public void agregarAdministrador(Administrador administrador) throws Exception {
        administradorServicio.agregarAdministrador(administrador);
    }

    public List<Alojamiento> getAlojamientos(){
        return alojamientoServicio.listarAlojamientos();
    }

    public Alojamiento agregarAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, Image imagen, float precioPorNoche, int capacidadMaxima, List<TipoServicio> servicios, float costoExtra, List<Habitacion> habitaciones) throws Exception {
        return alojamientoServicio.crearAlojamiento(tipo, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra, habitaciones);
    }

    public void eliminarAlojamiento (Alojamiento alojamiento) {
        alojamientoServicio.eliminarAlojamiento(alojamiento);
    }

    public void actualizarAlojamiento(String nombre, Alojamiento actualizado){
        alojamientoServicio.actualizarAlojamiento(nombre,actualizado);
    }
}
