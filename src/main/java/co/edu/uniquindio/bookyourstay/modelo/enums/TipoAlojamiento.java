package co.edu.uniquindio.bookyourstay.modelo.enums;

public enum TipoAlojamiento {
    APARTAMENTO,
    CASA,
    HOTEL;

    public static TipoAlojamiento fromString(String tipoAlojamiento){
        if (tipoAlojamiento.equalsIgnoreCase("casa")) return CASA;
        else if (tipoAlojamiento.equalsIgnoreCase("apartamento")) return APARTAMENTO;
        else return HOTEL;
    }
}
