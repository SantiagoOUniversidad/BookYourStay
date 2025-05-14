package co.edu.uniquindio.bookyourstaytest;

import co.edu.uniquindio.bookyourstay.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BookYourStayTest {

    BookYourStay bookYourStay =  new BookYourStay(null, null, null, null, null);

    @Test
    public void crearAdministradorTest() throws Exception{
        String cedula = "1234567890";
        String nombre = "Jose";
        String telefono = "1234567890";
        String email = "jose@gmail.com";
        String password = "password";
        Administrador adminTest = new Administrador(cedula, nombre, telefono, email, password);
        bookYourStay.crearAdministrador(cedula, nombre, telefono, email, password);
        List<Administrador> adminListTest = new ArrayList<>();
        adminListTest.add(adminTest);
        assertEquals(adminListTest, bookYourStay.getAdministradores());
    }

    @Test
    public void crearAdministradorExceptionTest() throws Exception{
        Exception ex = assertThrows(Exception.class, () -> {
            bookYourStay.crearAdministrador("", "Nombre", "123", "correo@mail.com", "clave");
        });
        assertEquals("Ningún campo puede estar vacío", ex.getMessage());
    }

}
