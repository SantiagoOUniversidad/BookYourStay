package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.*;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorio.AlojamientoRepositorio;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.List;

public class BookYourStayServicio {
    private final AdministradorServicios administradorServicio;
    private final ClienteServicios clienteServicio;
    private final AlojamientoServicio alojamientoServicio;
    private final OfertaServicio ofertaServicio;

    public BookYourStayServicio() {
        administradorServicio = new AdministradorServicios();
        clienteServicio = new ClienteServicios();
        alojamientoServicio = new AlojamientoServicio();
        ofertaServicio = new OfertaServicio();
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

    public void actualizarAlojamiento(String nombre, Alojamiento actualizado) throws Exception {
        alojamientoServicio.actualizarAlojamiento(nombre,actualizado);
    }

    public List<Oferta> getListaOfertas(){
        return ofertaServicio.listarOfertas();
    }

    public Oferta agregarOferta(Alojamiento alojamiento, double descuento, LocalDate fechaInicio, LocalDate fechaFin)throws Exception{
        return ofertaServicio.crearOferta(alojamiento,descuento,fechaInicio,fechaFin);
    }

    public Alojamiento buscarAlojamientoNombre(String nombre) throws Exception {
        return alojamientoServicio.buscarAlojamientoNombre(nombre);
    }

    public void eliminarOferta(Oferta oferta) throws Exception {
        ofertaServicio.eliminarOferta(oferta);
    }

    public void actualizarOferta(Oferta oferta, Oferta actualizado) throws Exception {
        ofertaServicio.actualizarOferta(oferta,actualizado);
    }

    public Alojamiento buscarAlojamientoPorNombre(String nombre) throws Exception { return alojamientoServicio.buscarAlojamiento(nombre); }

    public List<Alojamiento> buscarAlojamientoPorTipo(TipoAlojamiento tipoAlojamiento) throws Exception { return alojamientoServicio.buscarAlojamientoPorTipo(tipoAlojamiento); }

    public List<Alojamiento> buscarAlojamientoPorCiudad(String ciudad){ return alojamientoServicio.buscarAlojamientoPorCiudad(ciudad); }

    public List<Alojamiento> buscarAlojamientoPorPrecio(float precioMin, float precioMax){ return alojamientoServicio.buscarAlojamientoPorPrecio(precioMin, precioMax); }

    public void agregarCliente(Cliente cliente){
        clienteServicio.agregarCliente(cliente);
    }
}

