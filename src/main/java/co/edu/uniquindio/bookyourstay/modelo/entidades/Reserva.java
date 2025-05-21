package co.edu.uniquindio.bookyourstay.modelo.entidades;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder

public class Reserva {

    //Atributos
    private Cliente cliente;
    private Alojamiento alojamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private float precioTotal;
}
