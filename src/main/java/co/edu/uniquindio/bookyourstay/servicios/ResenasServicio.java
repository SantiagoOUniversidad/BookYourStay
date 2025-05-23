package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Resena;
import co.edu.uniquindio.bookyourstay.repositorio.ResenasRepositorio;

import java.util.List;

public class ResenasServicio {
    private final ResenasRepositorio resenasRepositorio;

    public ResenasServicio(){
        this.resenasRepositorio = new ResenasRepositorio();
    }

    public List<Resena> listarResenas() {
        return resenasRepositorio.listarResenas();
    }

    public void agregarResena(Resena resena) {
        resenasRepositorio.agregarResena(resena);
    }
}
