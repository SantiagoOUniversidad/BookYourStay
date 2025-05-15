package co.edu.uniquindio.bookyourstaytest;

import co.edu.uniquindio.bookyourstay.modelo.*;
import co.edu.uniquindio.bookyourstay.servicios.AdministradorServicios;
import co.edu.uniquindio.bookyourstay.servicios.ClienteServicios;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ServiciosTest {

    AdministradorServicios administradorServicios = new AdministradorServicios();
    ClienteServicios clienteServicios = new ClienteServicios();
    private final BookYourStay bookYourStay = BookYourStay.getInstancia();

    @Test
    public void crearAdministradorTest() throws Exception{
        String cedula = "1234567890";
        String nombre = "Jose";
        String telefono = "1234567890";
        String email = "jose@gmail.com";
        String password = "password";
        Administrador adminTest = new Administrador(cedula, nombre, telefono, email, password);
        administradorServicios.crearAdministrador(cedula, nombre, telefono, email, password);
        assertEquals(adminTest, Administrador.getInstancia());
    }

    @Test
    public void crearAdministradorNullExceptionTest() throws Exception{
        Exception ex = assertThrows(Exception.class, () -> {
            administradorServicios.crearAdministrador("", "Nombre", "123", "correo@mail.com", "clave");
        });
        assertEquals("Ningún campo puede estar vacío", ex.getMessage());
    }

    @Test
    public void crearAdministradorRepetidoExceptionTest() throws Exception{
        administradorServicios.crearAdministrador("123", "Nombre", "123", "correo@mail.com", "clave");
        Exception ex = assertThrows(Exception.class, () -> {
            administradorServicios.crearAdministrador("123", "Nombre", "123", "correo@mail.com", "clave");
        });
        assertEquals("El administrador ya existe", ex.getMessage());
    }

    @Test
    public void buscarAdministradorTest() throws Exception{
        administradorServicios.crearAdministrador("123", "Nombre", "123", "correo@mail.com", "clave");
        assertEquals(Administrador.getInstancia(), administradorServicios.buscarAdministrador());
    }

    @Test
    public void crearClienteTest() throws Exception{
        String cedula = "1234567890";
        String nombre = "Jose";
        String telefono = "1234567890";
        String email = "jose@gmail.com";
        String password = "password";
        Cliente clienteTest = new Cliente(cedula, nombre, telefono, email, password);
        clienteServicios.crearCliente(cedula, nombre, telefono, email, password);
        List<Cliente> clienteListTest = new ArrayList<>();
        clienteListTest.add(clienteTest);
        assertEquals(clienteListTest, bookYourStay.getClientes());
    }

    @Test
    public void crearClienteExceptionTest() throws Exception{
        Exception ex = assertThrows(Exception.class, () -> {
            clienteServicios.crearCliente("", "Nombre", "123", "correo@mail.com", "clave");
        });
        assertEquals("Ningún campo puede estar vacío", ex.getMessage());
    }

    @Test
    public void buscarClienteTest() throws Exception{
        String cedula = "1234567890";
        String nombre = "Jose";
        String telefono = "1234567890";
        String email = "jose@gmail.com";
        String password = "password";
        Cliente clienteTest = new Cliente(cedula, nombre, telefono, email, password);
        bookYourStay.clientes.add(clienteTest);
        assertEquals(clienteServicios.buscarCliente(cedula), clienteTest);
    }

    @Test
    public void actualizarClienteTest() throws Exception{
        String cedula = "1234567890";
        String nombre = "Jose";
        String telefono = "1234567890";
        String email = "jose@gmail.com";
        String password = "password";
        Cliente clienteTest = new Cliente(cedula, nombre, telefono, email, password);
        bookYourStay.clientes.add(clienteTest);
        String cedula2 = "1234567890";
        String nombre2 = "Andres";
        String telefono2 = "0987654321";
        String email2 = "andres@gmail.com";
        Cliente clienteTest2 = new Cliente(cedula2, nombre2, telefono2, email2, password);
        assertEquals(clienteServicios.actualizarCliente(cedula2, nombre2, telefono2, email2), clienteTest2);
    }

    @Test
    public void eliminarClienteTest() throws Exception{
        clienteServicios.crearCliente("1234567890", "Nombre", "123", "correo@mail.com", "clave");
        assertTrue(clienteServicios.eliminarCliente("1234567890"));
    }

}
