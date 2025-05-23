package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorio.OfertaRepositorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OfertaServicio {
    private final OfertaRepositorio ofertasRepositorio;

    public OfertaServicio() {
        this.ofertasRepositorio = new OfertaRepositorio();
    }

    public List<Oferta> listarOfertas() {
        return ofertasRepositorio.listarOfertas();
    }

    public Oferta crearOferta(Alojamiento alojamiento, double descuento, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        if(alojamiento==null || descuento < 0 || fechaInicio == null || fechaFin == null) throw new Exception("No pueden haber campos vacios");
        Oferta oferta = Oferta.builder()
                .alojamiento(alojamiento)
                .descuento(descuento)
                .fechaInicio(fechaInicio)
                .fechaFinal(fechaFin)
                .build();
        ofertasRepositorio.agregarOferta(oferta);
        return oferta;
    }
    public void eliminarOferta(Oferta oferta){
        ofertasRepositorio.eliminarOferta(oferta);
    }

    public void actualizarOferta(Oferta oferta, Oferta acutalizado){
        oferta.setAlojamiento(acutalizado.getAlojamiento());
        oferta.setDescuento(acutalizado.getDescuento());
        oferta.setFechaFinal(acutalizado.getFechaFinal());
        oferta.setFechaInicio(acutalizado.getFechaInicio());
    }
}

