package co.edu.uniquindio.bookyourstay.repositorio;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Resena;

import java.util.ArrayList;
import java.util.List;

public class ResenasRepositorio {
    private final List<Resena> resenas;

    public ResenasRepositorio() {
        resenas = new ArrayList();
    }

    public void agregarResena(Resena resena) {
        resenas.add(resena);
    }

    public void eliminarResena(Resena resena) {
        resenas.remove(resena);
    }

    public List<Resena> listarResenas() {
        return resenas;
    }
}
