package co.edu.uniquindio.bookyourstay.modelo.entidades;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoHabitacion;
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
    private ImageView imagen;
    private String description;
    private TipoHabitacion tipoHabitacion;
}
