package co.edu.uniquindio.bookyourstay.repositorio;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {
    private final List<Cliente> clientes;

    public ClienteRepositorio() {
        clientes = new ArrayList<Cliente>();
    }

    public void agregarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void eliminarCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public Cliente buscarClientePorNombre(String cedula){
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes(){
        return clientes;
    }


}
