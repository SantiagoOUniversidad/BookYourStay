package co.edu.uniquindio.bookyourstay.repositorio;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepositorio {
    private final List<Reserva> reservas;

    public ReservaRepositorio() {
        reservas = new ArrayList<Reserva>();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }

    public List<Reserva> listarReservasCliente(Cliente cliente){
        List<Reserva> reservasFiltradas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if(reserva.getCliente().equals(cliente)){
                reservasFiltradas.add(reserva);
            }
        }
        return reservasFiltradas;
    }
}
