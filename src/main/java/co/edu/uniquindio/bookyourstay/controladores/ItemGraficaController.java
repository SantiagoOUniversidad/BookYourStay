package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.servicios.AdministradorServicios;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemGraficaController implements Initializable {

    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

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
        for (Alojamiento alojamiento : bookYourStayServicio.getAlojamientos()) {
            float totalGanancias = bookYourStayServicio.calcularGananciasTotales(alojamiento);
            float ocupacion = bookYourStayServicio.calcularOcupacion(alojamiento);

            barChartGanancias.getData().get(0).getData().add(new XYChart.Data<>(alojamiento.getNombre(), totalGanancias));
            pieChartOcupacion.getData().add(new PieChart.Data(alojamiento.getNombre(), ocupacion));
        }
    }

}
