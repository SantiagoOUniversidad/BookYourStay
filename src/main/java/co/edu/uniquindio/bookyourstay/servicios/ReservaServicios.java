package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorio.ReservaRepositorio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservaServicios {
    private final ReservaRepositorio reservaRepositorio;

    public ReservaServicios(){
        this.reservaRepositorio = new ReservaRepositorio();
    }

    // Calcular ganancias totales
    public float calcularGananciasTotales(Alojamiento alojamiento) {
        List<Reserva> reservas = reservaRepositorio.listarReservas();
        return (float) reservas.stream()
                .filter(r -> r.getAlojamiento().equals(alojamiento))
                .mapToDouble(Reserva::getPrecioTotal)
                .sum();
    }

    // Calcular ocupacion porcentual
    public float calcularOcupacion(Alojamiento alojamiento) {
        List<Reserva> reservasAlojamiento = reservaRepositorio.listarReservas().stream()
                .filter(r -> r.getAlojamiento().equals(alojamiento))
                .toList();

        if (reservasAlojamiento.isEmpty()) {
            return 0;
        }

        // Encontrar la fecha más temprana y más tardía
        LocalDate fechaInicio = reservasAlojamiento.stream()
                .map(Reserva::getFechaInicio)
                .min(LocalDate::compareTo)
                .orElseThrow();

        LocalDate fechaFinal = reservasAlojamiento.stream()
                .map(Reserva::getFechaFinal)
                .max(LocalDate::compareTo)
                .orElseThrow();

        float diasTotales = ChronoUnit.DAYS.between(fechaInicio, fechaFinal);

        float diasReservados = reservasAlojamiento.stream()
                .mapToLong(r -> ChronoUnit.DAYS.between(r.getFechaInicio(), r.getFechaFinal()))
                .sum();

        return (diasTotales == 0) ? 0 : (diasReservados / diasTotales) * 100;
    }
}
