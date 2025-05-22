package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.utils.EnvioEmail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EnviarCodigoVerificacionServicios {

    public Map<String, String> clavesGeneradas = new HashMap<>();

    EnvioEmail envioEmail = new EnvioEmail();

    public void solicitarClave(String email) throws Exception {
        String codigoGenerado = generarCodigo();
        clavesGeneradas.put(email, codigoGenerado);
        envioEmail.enviarCorreo(email, "Verifica Tu Cuenta | Book Your Stay", "Tu código es: " + codigoGenerado);
    }

    public String generarCodigo() {
        Random random = new Random();
        int codigo = random.nextInt(900000) + 100000;
        return String.valueOf(codigo);
    }

    public void verificarCodigo(String email, String codigoIngresado) throws Exception {
        String codigoGuardado = clavesGeneradas.get(email);
        if (codigoGuardado == null || !codigoIngresado.equals(codigoGuardado)) {
            throw new Exception("Código incorrecto o expirado");
        }
        clavesGeneradas.remove(email);
    }

}
