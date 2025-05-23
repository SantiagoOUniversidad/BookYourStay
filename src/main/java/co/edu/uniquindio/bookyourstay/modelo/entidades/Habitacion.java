package co.edu.uniquindio.bookyourstay.modelo.entidades;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoHabitacion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString

public class Habitacion {

    //Atributos
    private int numero;
    private float precio;
    private int capacidad;
    private Image imagen;
    private String description;
    private TipoHabitacion tipoHabitacion;

    public Habitacion(Habitacion original) {
        this.numero = original.numero;
        this.precio = original.precio;
        this.capacidad = original.capacidad;
        this.imagen = original.imagen; // Esto copia la referencia a la imagen, si necesitas una imagen distinta, deberás clonarla.
        this.description = original.description;
        this.tipoHabitacion = original.tipoHabitacion; // Si TipoHabitacion es mutable, también deberías copiarlo.
    }

}
