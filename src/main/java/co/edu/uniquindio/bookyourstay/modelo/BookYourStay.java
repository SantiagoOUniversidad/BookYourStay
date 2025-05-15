package co.edu.uniquindio.bookyourstay.modelo;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString

public class BookYourStay {
    //Atributos
    private static BookYourStay instancia;
    public List<Cliente> clientes;
    public List<Alojamiento> alojamientos;
    public List<Reserva> reservas;
    public List<Oferta> ofertas;

    public BookYourStay(List clientes, List alojamientos, List reservas, List ofertas) {
        this.clientes = new ArrayList<Cliente>();
        this.alojamientos = new ArrayList<Alojamiento>();
        this.reservas = new ArrayList<Reserva>();
        this.ofertas = new ArrayList<Oferta>();
    }

    public static BookYourStay getInstancia() {
        if (instancia == null) {
            instancia = new BookYourStay(null, null, null, null);
            return instancia;
        } else {
            return instancia;
        }
    }

}
