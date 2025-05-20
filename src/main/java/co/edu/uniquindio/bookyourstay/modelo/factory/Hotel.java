package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import javafx.scene.image.ImageView;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString


public class Hotel extends Alojamiento {

    //Atributos
    private List<Habitacion> habitaciones;

    public Hotel(TipoAlojamiento alojamiento, String nombre, String ciudad, String descripcion, ImageView imagen, float precioPorNoche, int capacidadMaxima, List<TipoServicio> servicios, float costoExtra, List<Habitacion> habitaciones) {
        super(alojamiento, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra);
        this.habitaciones = new ArrayList<>();
    }
}
