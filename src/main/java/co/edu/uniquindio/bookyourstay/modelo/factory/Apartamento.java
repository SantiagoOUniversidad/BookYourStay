package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString

public class Apartamento extends Alojamiento {

    public Apartamento(TipoAlojamiento alojamiento, String nombre, String ciudad, String descripcion, Image imagen, float precioPorNoche, int capacidadMaxima, List<TipoServicio> servicios, float costoExtra) {
        super(alojamiento, nombre, ciudad, descripcion, imagen, precioPorNoche, capacidadMaxima, servicios, costoExtra);
    }
}