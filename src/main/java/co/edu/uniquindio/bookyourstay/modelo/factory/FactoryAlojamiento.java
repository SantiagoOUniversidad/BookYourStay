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
            case CASA -> Casa.builder()
                    .nombre(nombre)
                    .ciudad(ciudad)
                    .tipoAlojamiento(tipo)
                    .description(descripcion)
                    .imagen(imagen)
                    .precioPorNoche(precioPorNoche)
                    .capacidadMax(capacidadMaxima)
                    .costoExtra(costoExtra)
                    .build();
            case APARTAMENTO -> Apartamento.builder()
                    .nombre(nombre)
                    .ciudad(ciudad)
                    .tipoAlojamiento(tipo)
                    .description(descripcion)
                    .imagen(imagen)
                    .precioPorNoche(precioPorNoche)
                    .capacidadMax(capacidadMaxima)
                    .costoExtra(costoExtra)
                    .build();
            case HOTEL -> Hotel.builder()
                    .nombre(nombre)
                    .ciudad(ciudad)
                    .tipoAlojamiento(tipo)
                    .description(descripcion)
                    .imagen(imagen)
                    .precioPorNoche(precioPorNoche)
                    .capacidadMax(capacidadMaxima)
                    .habitaciones(habitaciones)
                    .costoExtra(0)
                    .build();
        };
    }
}