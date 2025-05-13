package co.edu.uniquindio.bookyourstay.modelo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder

public class Oferta {

    //Atributos
    private Alojamiento alojamiento;
    private double descuento;
    private Date fechaInicio;
    private Date fechaFinal;
}
