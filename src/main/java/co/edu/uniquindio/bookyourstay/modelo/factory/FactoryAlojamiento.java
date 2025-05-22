package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class FactoryAlojamiento {
    public static Alojamiento crearAlojamiento(TipoAlojamiento tipo, String nombre, String ciudad, String descripcion, Image imagen, float precioPorNoche, int capacidadMaxima, List<TipoServicio> servicios, float costoExtra, List<Habitacion> habitaciones) {
        return switch (tipo) {
            case CASA -> new Casa(tipo, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra);
            case APARTAMENTO -> new Apartamento(tipo, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra);
            case HOTEL -> new Hotel(tipo, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra, habitaciones);
        };
    }
}