package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.servicios.AdministradorServicios;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemGraficaController implements Initializable {

    private final BookYourStay bookYourStay = BookYourStay.getInstancia();
    private AdministradorServicios administradorServicios = new AdministradorServicios();

    @FXML
    private BarChart<String, Number> barChartGanancias;

    @FXML
    private PieChart pieChartOcupacion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarEstadisticas();
    }

    private void cargarEstadisticas() {
        for (Alojamiento alojamiento : bookYourStay.getAlojamientos()) {
            float totalGanancias = administradorServicios.calcularGananciasTotales(alojamiento);
            float ocupacion = administradorServicios.calcularOcupacion(alojamiento);

            barChartGanancias.getData().get(0).getData().add(new XYChart.Data<>(alojamiento.getNombre(), totalGanancias));
            pieChartOcupacion.getData().add(new PieChart.Data(alojamiento.getNombre(), ocupacion));
        }
    }

}
