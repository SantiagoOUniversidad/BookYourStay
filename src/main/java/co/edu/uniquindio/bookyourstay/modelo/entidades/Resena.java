package co.edu.uniquindio.bookyourstay.modelo.entidades;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder

public class Resena {
    // Atributos
    private Alojamiento alojamiento;
    private String comentarios;
    private int calificacion;
}
