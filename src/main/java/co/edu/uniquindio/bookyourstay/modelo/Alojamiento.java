package co.edu.uniquindio.bookyourstay.modelo;

import javafx.scene.image.ImageView;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@Builder

public class Alojamiento {

    //Atributos
    private String nombre;
    private String ciudad;
    private String description;
    private ImageView imagen;
    private float precioPorNoche;
    private int capacidadMax;
    private List<TipoServicio> listaServicios;

}
