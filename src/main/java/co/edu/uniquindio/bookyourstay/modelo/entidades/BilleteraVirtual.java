package co.edu.uniquindio.bookyourstay.modelo.entidades;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder

public class BilleteraVirtual {

    //Atributos
    public String numero;
    public float saldo;
    public Cliente cliente;
}
