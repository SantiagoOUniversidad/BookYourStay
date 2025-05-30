package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.controladores.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.repositorio.AdministradorRepositorio;
import co.edu.uniquindio.bookyourstay.utils.EnvioEmail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CambiarContrasenaServicios {

    public Map<String, String> clavesGeneradas = new HashMap<>();

    ClienteServicios clienteServicios = new ClienteServicios();
    EnvioEmail envioEmail = new EnvioEmail();
    AdministradorRepositorio administradorRepositorio = new AdministradorRepositorio();
    private final BookYourStayServicio bookYourStay = ControladorPrincipal.getInstancia().getBookYourStayServicio();

    public boolean solicitarClave(String cedula) throws Exception {
        try {
            Cliente clienteRecuperar = clienteServicios.buscarCliente(cedula);
            String emailCliente = clienteRecuperar.getEmail();
            String codigoGenerado = generarCodigo();
            clavesGeneradas.put(cedula, codigoGenerado);
            envioEmail.enviarCorreo(emailCliente, "Código de recuperacion | Book Your Stay", "Tu código es: " + codigoGenerado);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String generarCodigo() {
        Random random = new Random();
        int codigo = random.nextInt(900000) + 100000;
        return String.valueOf(codigo);
    }

    public boolean verificarCodigo(String cedula, String codigoIngresado, String nuevaContrasena) throws Exception {
        String codigoGuardado = clavesGeneradas.get(cedula);
        if (codigoGuardado == null || !codigoIngresado.equals(codigoGuardado)) {
            throw new Exception("Código incorrecto o expirado");
        }
        Cliente clienteNuevaContrasena = clienteServicios.buscarCliente(cedula);
        clienteNuevaContrasena.setPassword(nuevaContrasena);
        clavesGeneradas.remove(cedula);
        return true;
    }

    public boolean solicitarClaveAdmin() throws Exception {
        try {
            Administrador administradorRecuperar = administradorRepositorio.listarAdministradores().stream()
                    .findFirst()
                    .orElse(null);
            String emailAdmin = administradorRecuperar.getEmail();
            String codigoGenerado = generarCodigo();
            clavesGeneradas.put(emailAdmin, codigoGenerado);
            envioEmail.enviarCorreo(emailAdmin, "Código de recuperacion | Book Your Stay", "Tu código es: " + codigoGenerado);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean verificarCodigoAdmin(String codigoIngresado, String nuevaContrasena) throws Exception {
        Administrador administradorRecuperar = bookYourStay.listarAdministrador().stream()
                .findFirst()
                .orElse(null);
        String codigoGuardado = clavesGeneradas.get(administradorRecuperar.getEmail());
        if (codigoGuardado == null || !codigoIngresado.equals(codigoGuardado)) {
            throw new Exception("Código incorrecto o expirado");
        }
        administradorRecuperar.setPassword(nuevaContrasena);
        clavesGeneradas.remove(administradorRecuperar.getEmail());
        return true;
    }

}
