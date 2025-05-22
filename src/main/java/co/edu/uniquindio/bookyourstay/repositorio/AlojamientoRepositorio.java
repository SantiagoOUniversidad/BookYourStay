package co.edu.uniquindio.bookyourstay.repositorio;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;

import java.util.ArrayList;
import java.util.List;

public class AlojamientoRepositorio {
    private final List<Alojamiento> alojamientos;

    public AlojamientoRepositorio() {
        alojamientos = new ArrayList<>();
    }

    public void agregar(Alojamiento alojamiento) {
        alojamientos.add(alojamiento);
    }

    public void eliminar(Alojamiento alojamiento) {
        alojamientos.remove(alojamiento);
    }

    public List<Alojamiento> buscarAlojamientoPorCiudad(String ciudad) {
        List<Alojamiento> alojamientoFiltro = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equals(ciudad)) {
                alojamientoFiltro.add(alojamiento);
            }
        }
        return alojamientoFiltro;
    }

    public Alojamiento bucarAlojamientoPorNombre(String nombre) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getNombre().equals(nombre)) {
                return alojamiento;
            }
        }
        return null;
    }

    public List<Alojamiento> listarAlojamientos() {
        return alojamientos;
    }

    public List<Alojamiento> buscarAlojamientoPorPrecio(float precioMin, float precioMax) {
        List<Alojamiento> alojamientoFiltro = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if(alojamiento.getPrecioPorNoche() < precioMax && alojamiento.getPrecioPorNoche() > precioMin) {
                alojamientoFiltro.add(alojamiento);
            }
        }
        return alojamientoFiltro;
    }

    public List<Alojamiento> buscarAlojamientoPorTipo(TipoAlojamiento tipoAlojamiento){
        List<Alojamiento> alojamientoFiltro = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getTipoAlojamiento() == tipoAlojamiento){
                alojamientoFiltro.add(alojamiento);
            }
        }
        return alojamientoFiltro;
    }
}
