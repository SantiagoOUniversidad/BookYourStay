package co.edu.uniquindio.bookyourstay.modelo;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString

public class BookYourStay {
    //Atributos
    private List<Administrador> administradores;
    private List<Cliente> clientes;
    private List<Alojamiento> alojamientos;
    private List<Reserva> reservas;
    private List<Oferta> ofertas;

    public BookYourStay(List administradores, List clientes, List alojamientos, List reservas, List ofertas) {
        this.administradores = new ArrayList<Administrador>();
        this.clientes = new ArrayList<Cliente>();
        this.alojamientos = new ArrayList<Alojamiento>();
        this.reservas = new ArrayList<Reserva>();
        this.ofertas = new ArrayList<Oferta>();
    }

    //EXCEPCIONES
    Exception camposVacios = new Exception("Ningún campo puede estar vacío");

    //CRUD ADMINISTRADOR

    public Administrador crearAdministrador(String cedula, String nombre, String telefono, String email, String password) throws Exception {
        if (cedula == null || nombre == null || telefono == null || email == null || password == null) {
            throw camposVacios;
        }
        if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw camposVacios;
        }
        try {
            buscarAdministrador(cedula);
            throw new Exception("El administrador ya existe");
        } catch (Exception adminNoEncontrado) {
        }
        Administrador nuevoAdministrador = Administrador.builder().cedula(cedula).nombre(nombre).telefono(telefono).email(email).password(password).build();
        administradores.add(nuevoAdministrador);
        return nuevoAdministrador;
    }

    public Administrador buscarAdministrador(String cedula) throws Exception {
        Exception adminNoEncontrado = new Exception("Administrador no encontrado");
        if (cedula == null || cedula.isEmpty()) {
            throw camposVacios;
        }
        Administrador adminFiltrado = administradores.stream()
                .filter(admin -> admin.getCedula().equals(cedula))
                .findFirst()
                .orElseThrow(() -> new Exception(adminNoEncontrado));
        return adminFiltrado;
    }

}
