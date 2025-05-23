package co.edu.uniquindio.bookyourstay.repositorio;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Oferta;

import java.util.ArrayList;
import java.util.List;

public class OfertaRepositorio {
    private final List<Oferta> ofertas;

    public OfertaRepositorio() {
        ofertas = new ArrayList<>();
    }

    public void agregarOferta(Oferta oferta) {
        ofertas.add(oferta);
    }

    public void eliminarOferta(Oferta oferta) {
        ofertas.remove(oferta);
    }

    public List<Oferta> listarOfertas() {
        return ofertas;
    }
}
