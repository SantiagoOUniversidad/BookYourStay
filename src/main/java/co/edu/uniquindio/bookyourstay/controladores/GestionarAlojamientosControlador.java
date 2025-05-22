package co.edu.uniquindio.bookyourstay.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
public class GestionarAlojamientosControlador {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageFotoPerfil;

    @FXML
    private TableColumn<Alojamiento, String> tbcCapacidad;

    @FXML
    private TableColumn<Alojamiento, String> tbcCiudad;

    @FXML
    private TableColumn<Alojamiento, String> tbcNombre;

    @FXML
    private TableColumn<Alojamiento, String> tbcPrecioNoche;

    @FXML
    private TableView<Alojamiento> tblAlojamientos;

    @FXML
    private TableColumn<Alojamiento, String> tbcTipoAlojamiento;

    @FXML
    private ChoiceBox<TipoAlojamiento> tipoAlojamiento;

    @FXML
    private TextField txtCapacidadMaxima;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtCostoExtra;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecioNoche;

    @FXML
    void onActualizarAlojamientoAction(ActionEvent event) {

    }

    @FXML
    void onAgregarAlojamientoAction(ActionEvent event) {

    }

    @FXML
    void onEliminarAlojamientoAction(ActionEvent event) {

    }

    @FXML
    void onSeleccionarFotoAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert imageFotoPerfil != null : "fx:id=\"imageFotoPerfil\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tbcCapacidad != null : "fx:id=\"tbcCapacidad\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tbcCiudad != null : "fx:id=\"tbcCiudad\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tbcNombre != null : "fx:id=\"tbcNombre\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tbcPrecioNoche != null : "fx:id=\"tbcPrecioNoche\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tblAlojamientos != null : "fx:id=\"tblAlojamientos\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tipoAlojamiento != null : "fx:id=\"tipoAlojamiento\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert txtCapacidadMaxima != null : "fx:id=\"txtCapacidadMaxima\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert txtCiudad != null : "fx:id=\"txtCiudad\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert txtCostoExtra != null : "fx:id=\"txtCostoExtra\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert txtPrecioNoche != null : "fx:id=\"txtPrecioNoche\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
        assert tbcTipoAlojamiento != null : "fx:id=\"tbcTipoAlojamiento\" was not injected: check your FXML file 'GestionarAlojamientos.fxml'.";
    }

}

