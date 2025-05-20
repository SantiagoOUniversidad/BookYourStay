package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;

public class ClienteServicios {

    private final BookYourStay bookYourStay = BookYourStay.getInstancia();

    //CONSTANTES
    Exception camposVacios = new Exception("Ningún campo puede estar vacío");
    Exception clienteNoExistente = new Exception("Cliente no existe");

    public Cliente crearCliente(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        if (cedula == null || nombre == null || telefono == null || email == null || password == null || cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw camposVacios;
        }
        try {
            buscarCliente(cedula);
            throw new Exception("El cliente ya existe");
        } catch (Exception adminNoEncontrado) {
        }
        Cliente nuevoAdministrador = Cliente.builder().cedula(cedula).nombre(nombre).telefono(telefono).email(email).password(password).build();
        bookYourStay.clientes.add(nuevoAdministrador);
        return nuevoAdministrador;
    }

    public Cliente buscarCliente(String cedula) throws Exception{
        Exception clienteNoEncontrado = new Exception("Cliente no encontrado");
        if (cedula == null || cedula.isEmpty()) {
            throw camposVacios;
        }
        Cliente clienteFiltrado = bookYourStay.clientes.stream()
                .filter(cliente -> cliente.getCedula().equals(cedula))
                .findFirst()
                .orElseThrow(() -> new Exception(clienteNoEncontrado));
        return clienteFiltrado;
    }

    public Cliente actualizarCliente(String cedula, String nombre, String telefono, String email) throws Exception {
        if (cedula == null || nombre == null || telefono == null || email == null || cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw camposVacios;
        }
        Cliente clienteActualizar = buscarCliente(cedula);
        if (clienteActualizar == null) {
            throw clienteNoExistente;
        }
        clienteActualizar.setCedula(cedula);
        clienteActualizar.setNombre(nombre);
        clienteActualizar.setTelefono(telefono);
        clienteActualizar.setEmail(email);
        return clienteActualizar;
    }

    public boolean eliminarCliente(String cedula) throws Exception {
        if (cedula == null || cedula.isEmpty()) {
            throw camposVacios;
        }
        Cliente clienteEliminar = buscarCliente(cedula);
        if (clienteEliminar == null) {
            throw clienteNoExistente;
        }
        bookYourStay.clientes.remove(clienteEliminar);
        return true;
    }

}
