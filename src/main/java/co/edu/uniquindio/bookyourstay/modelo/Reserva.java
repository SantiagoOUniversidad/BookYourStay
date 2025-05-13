package co.edu.uniquindio.bookyourstay.modelo;

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
}
