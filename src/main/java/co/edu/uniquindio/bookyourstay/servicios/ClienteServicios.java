package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.ClienteTemporal;
import co.edu.uniquindio.bookyourstay.repositorio.ClienteRepositorio;

public class ClienteServicios {
    private final ClienteRepositorio clienteRepositorio;
    private EnviarCodigoVerificacionServicios enviarCodigoVerificacionServicios = new EnviarCodigoVerificacionServicios();

    public ClienteServicios() {
        this.clienteRepositorio = new ClienteRepositorio();
    }

    //CONSTANTES
    Exception camposVacios = new Exception("Ningún campo puede estar vacío");
    Exception clienteNoExistente = new Exception("Cliente no existe");

    // Crea cliente sin verificacion
    public ClienteTemporal crearClienteSinVerificacion(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        if (cedula == null || nombre == null || telefono == null || email == null || password == null || cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw camposVacios;
        }
        if (buscarCliente(cedula) == null) {
            throw new Exception("El cliente ya existe");
        }
        ClienteTemporal nuevoClienteTemporal = ClienteTemporal.builder().cedula(cedula).nombre(nombre).telefono(telefono).email(email).password(password).build();
        ClienteTemporal.clientesTemporales.add(nuevoClienteTemporal);
        enviarCodigoVerificacionServicios.solicitarClave(email);
        return nuevoClienteTemporal;
    }

    public void agregarCliente(Cliente cliente){
        clienteRespositorio.agregarCliente(cliente);
    }

    // Crea cliente verificado que si ingresa a la lista de clientes
    public Cliente crearClienteVerificado(ClienteTemporal clienteTemporal, String codigoIngresado) throws Exception {
        String cedula = clienteTemporal.getCedula();
        String nombre = clienteTemporal.getNombre();
        String telefono = clienteTemporal.getTelefono();
        String email = clienteTemporal.getEmail();
        String password = clienteTemporal.getPassword();
        try {
            enviarCodigoVerificacionServicios.verificarCodigo(email, codigoIngresado);
            Cliente nuevoCliente = Cliente.builder().cedula(cedula).nombre(nombre).telefono(telefono).email(email).password(password).build();
            clienteRepositorio.agregarCliente(nuevoCliente);
            ClienteTemporal.clientesTemporales.remove(clienteTemporal);
            return nuevoCliente;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Cliente buscarCliente(String cedula) throws Exception{
        if (cedula == null || cedula.isEmpty()) {
            throw camposVacios;
        }
        Cliente clienteFiltrado = clienteRepositorio.listarClientes().stream()
                .filter(cliente -> cliente.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
        return clienteFiltrado;
    }

    public Cliente actualizarCliente(Cliente clienteActualizar, String nombre, String telefono, String email) throws Exception {
        if (clienteActualizar == null || nombre == null || telefono == null || email == null || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw camposVacios;
        }
        clienteActualizar.setNombre(nombre);
        clienteActualizar.setTelefono(telefono);
        clienteActualizar.setEmail(email);
        return clienteActualizar;
    }

    public void eliminarCliente(String cedula) throws Exception {
        if (cedula == null || cedula.isEmpty()) {
            throw camposVacios;
        }
        try {
            Cliente clienteEliminar = buscarCliente(cedula);
            clienteRepositorio.eliminarCliente(clienteEliminar);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Cliente validarCliente(String id, String password) throws Exception {
        if(id == null || id.isEmpty() || password == null || password.isEmpty()) {
            throw camposVacios;
        }
        for (Cliente cliente : clienteRepositorio.listarClientes()) {
            if (cliente.getCedula().equals(id) && cliente.getPassword().equals(password)) {
                return cliente;
            }
        }
        throw new Exception("El cliente no existe");
    }

}
