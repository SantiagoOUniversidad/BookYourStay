package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.FactoryAlojamiento;
import co.edu.uniquindio.bookyourstay.repositorio.AlojamientoRepositorio;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class AlojamientoServicio {
    // Obtenemos la instancia de "BookYourStay" (Empresa)
    private final AlojamientoRepositorio alojamientoRepositorio;

    public AlojamientoServicio() {
        this.alojamientoRepositorio = new AlojamientoRepositorio();
    }

    public List<Alojamiento> listarAlojamientos() {
        return alojamientoRepositorio.listarAlojamientos();
    }

    // Crear Alojamiento
    public Alojamiento crearAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, Image imagen, float precioPorNoche, int capacidadMaxima, List<TipoServicio> servicios, float costoExtra, List<Habitacion> habitaciones) throws Exception {
        if (tipo == null || nombre == null || ciudad == null || descripcion == null || imagen == null || precioPorNoche < 0 || capacidadMaxima <= 0 || servicios == null || costoExtra < 0) {
            throw new Exception("No pueden haber campos vacios");
        }
        if (alojamientoRepositorio.bucarAlojamientoPorNombre(nombre) != null) {
            throw new Exception("Ya existe una alojamiento con ese nombre");
        }
        Alojamiento alojamiento = FactoryAlojamiento.crearAlojamiento(tipo, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra, habitaciones);
        alojamientoRepositorio.agregar(alojamiento);
        return alojamiento;
    }

    public void eliminarAlojamiento(Alojamiento alojamiento){
        alojamientoRepositorio.eliminar(alojamiento);
    }

    public void actualizarAlojamiento(String nombre, Alojamiento actualizado){
        Alojamiento encontrado = alojamientoRepositorio.bucarAlojamientoPorNombre(nombre);
        encontrado.setNombre(actualizado.getNombre());
        encontrado.setCiudad(actualizado.getCiudad());
        encontrado.setImagen(actualizado.getImagen());
        encontrado.setCostoExtra(actualizado.getCostoExtra());
        encontrado.setCapacidadMax(actualizado.getCapacidadMax());
        encontrado.setServicios(actualizado.getServicios());
        encontrado.setTipoAlojamiento(actualizado.getTipoAlojamiento());
        encontrado.setPrecioPorNoche(actualizado.getPrecioPorNoche());
        encontrado.setDescription(actualizado.getDescription());

    }
}
