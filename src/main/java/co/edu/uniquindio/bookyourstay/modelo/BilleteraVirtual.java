package co.edu.uniquindio.bookyourstay.modelo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class BilleteraVirtual {

    //Atributos
    private String numero;
    private float saldo;
    private List<Reserva> listaReservas;
    private Cliente cliente;
}
