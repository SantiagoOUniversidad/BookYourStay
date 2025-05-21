package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.FactoryAlojamiento;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AdministradorServicios {

    // Obtenemos la instancia de "BookYourStay" (Empresa)
    private final BookYourStay bookYourStay = BookYourStay.getInstancia();

    // CONSTANTES
    Exception camposVacios = new Exception("Ningún campo puede estar vacío");

    public Administrador buscarAdministrador() throws Exception {
        return Administrador.getInstancia();
    }

    // Crear Alojamiento
    public Alojamiento crearAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, ImageView imagen, float precioPorNoche, int capacidadMaxima, List<TipoServicio> servicios, float costoExtra, List<Habitacion> habitaciones) throws Exception {
        if (tipo == null || nombre == null || ciudad == null || descripcion == null || imagen == null || precioPorNoche <= 0 || capacidadMaxima <= 0 || servicios == null || costoExtra <= 0) {
            throw camposVacios;
        }
        try {
            buscarAlojamiento(nombre);
            throw new Exception("El alojamiento ya existe");
        } catch (Exception e) {
        }
        Alojamiento alojamiento = FactoryAlojamiento.crearAlojamiento(tipo, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra, habitaciones);
        bookYourStay.alojamientos.add(alojamiento);
        return alojamiento;
    }

    // Buscar Alojamiento por nombre
    public Alojamiento buscarAlojamiento(String nombre) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw camposVacios;
        }
        Alojamiento alojamientoFiltrado = bookYourStay.alojamientos.stream()
                .filter(a -> nombre.equals(a.getNombre()))
                .findFirst()
                .orElse(null);
        if (alojamientoFiltrado == null) {
            throw new Exception("El alojamiento no existe");
        }
        return alojamientoFiltrado;
    }

    // Actualizar un Alojamiento
    public void actualizarAlojamiento(String nombre, Alojamiento actualizado) throws Exception {
        if (nombre == null || nombre.isEmpty()) throw camposVacios;
        for (Alojamiento alojamiento : bookYourStay.alojamientos) {
            if (nombre.equals(alojamiento.getNombre())) {
                alojamiento.setCiudad(actualizado.getCiudad());
                alojamiento.setImagen(actualizado.getImagen());
                alojamiento.setCostoExtra(actualizado.getCostoExtra());
                alojamiento.setCapacidadMax(actualizado.getCapacidadMax());
                alojamiento.setPrecioPorNoche(actualizado.getPrecioPorNoche());
                alojamiento.setServicios(actualizado.getServicios());
                alojamiento.setDescription(actualizado.getDescription());
                break;
            }
        }
    }

    // Eliminar Alojamiento
    public void eliminarAlojamiento(Alojamiento alojamiento) throws Exception {
        if(alojamiento == null) throw new Exception("Seleccione un alojamiento");
        bookYourStay.alojamientos.remove(alojamiento);
    }

    // Calcular ocupacion porcentual
    public float calcularOcupacion(Alojamiento alojamiento) {
        List<Reserva> reservasAlojamiento = bookYourStay.getReservas().stream()
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

    // Calcular ganancias totales
    public float calcularGananciasTotales(Alojamiento alojamiento) {
        List<Reserva> reservas = bookYourStay.getReservas();
        return (float) reservas.stream()
                .filter(r -> r.getAlojamiento().equals(alojamiento))
                .mapToDouble(Reserva::getPrecioTotal)
                .sum();
    }
}
