package co.edu.uniquindio.bookyourstay.modelo.entidades;

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
    private Cliente cliente;
}
