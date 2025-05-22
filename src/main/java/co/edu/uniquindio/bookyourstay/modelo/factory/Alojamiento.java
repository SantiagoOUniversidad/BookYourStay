package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import javafx.scene.image.Image;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder

public class Alojamiento {

    //Atributos
    public TipoAlojamiento tipoAlojamiento;
    public String nombre;
    public String ciudad;
    public String description;
    public Image imagen;
    public float precioPorNoche;
    public int capacidadMax;
    public List<TipoServicio> servicios;
    public float costoExtra;
}
