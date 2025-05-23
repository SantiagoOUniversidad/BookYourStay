package co.edu.uniquindio.bookyourstay.controladores;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoHabitacion;
import co.edu.uniquindio.bookyourstay.modelo.factory.Hotel;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class GestionarHabitacionesControler {

    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();

    ObservableList<Habitacion> listaHabitaciones = FXCollections.observableArrayList();
    Habitacion selectedHabitacion;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageFotoPerfil;

    @FXML
    private TableColumn<Habitacion, String> tbcCapacidad;

    @FXML
    private TableColumn<Habitacion, String> tbcNumero;

    @FXML
    private TableColumn<Habitacion, String> tbcPrecio;

    @FXML
    private TableColumn<Habitacion, String> tbcTipoHabitacion;

    @FXML
    private TableView<Habitacion> tblHabitaciones;

    @FXML
    private ChoiceBox<TipoHabitacion> tipoHabitacion;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtPrecio;

    @FXML
    void onActualizarHabitacionAction(ActionEvent event) {
        actualizarHabitacion();
    }

    @FXML
    void onAgregarHabitacionAction(ActionEvent event) {
        agregarHabitacion();
    }

    @FXML
    void onEliminarHabitacionAction(ActionEvent event) {
        eliminarHabitacion();
    }

    @FXML
    void onSeleccionarFotoAction(ActionEvent event) {
        seleccionarFotoPerfil();
    }

    @FXML
    void initialize() {
        assert imageFotoPerfil != null : "fx:id=\"imageFotoPerfil\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert tbcCapacidad != null : "fx:id=\"tbcCapacidad\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert tbcNumero != null : "fx:id=\"tbcNumero\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert tbcPrecio != null : "fx:id=\"tbcPrecio\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert tbcTipoHabitacion != null : "fx:id=\"tbcTipoHabitacion\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert tblHabitaciones != null : "fx:id=\"tblHabitaciones\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert tipoHabitacion != null : "fx:id=\"tipoHabitacion\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert txtCapacidad != null : "fx:id=\"txtCapacidad\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        assert txtPrecio != null : "fx:id=\"txtPrecio\" was not injected: check your FXML file 'gestionarHabitaciones.fxml'.";
        inicializarLista();
        configuracionInicial();
        initView();
    }

    private void configuracionInicial() {
        tipoHabitacion.getItems().addAll(TipoHabitacion.values());
    }

    private void initView() {
        // Traer datos a la tabla
        initDataBinding();

        // Obtiene la lista
        inicializarLista();

        //Limpiar la talba
        tblHabitaciones.getItems().clear();

        // Agregara los elementos a la tabla
        tblHabitaciones.setItems(listaHabitaciones);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void inicializarLista() {
        listaHabitaciones.clear();
        listaHabitaciones.addAll(Sesion.getInstancia().getHabitaciones());
    }

    private void initDataBinding(){
        tbcCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidad())));
        tbcNumero.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumero())));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
        tbcTipoHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoHabitacion().toString()));
    }

    public void listenerSelection(){
        tblHabitaciones.setOnMouseClicked(e -> {
            //Obtener la nota seleccionada
            Habitacion selected = tblHabitaciones.getSelectionModel().getSelectedItem();
            if(selected != null){
                selectedHabitacion = selected;
                mostrarInformacionHabitacion(selectedHabitacion);
            }

        });
    }

    private void mostrarInformacionHabitacion(Habitacion habitacion) {
        if (habitacion != null) {
            txtCapacidad.setText(String.valueOf(habitacion.getCapacidad()));
            txtNumero.setText(String.valueOf(habitacion.getNumero()));
            txtPrecio.setText(String.valueOf(habitacion.getPrecio()));
            txtDescripcion.setText(habitacion.getDescription());
            imageFotoPerfil.setImage(habitacion.getImagen());
            tipoHabitacion.setValue(habitacion.getTipoHabitacion());
        }
    }

    private void agregarHabitacion(){
        try{
            Habitacion habitacion = buildHabitacion();
            listaHabitaciones.add(habitacion);
            Sesion.getInstancia().getHabitaciones().add(habitacion);
            controladorPrincipal.crearAlerta("Habitacion creada con exito", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private Habitacion buildHabitacion() {
        return Habitacion.builder()
                .capacidad(Integer.parseInt(txtCapacidad.getText()))
                .numero(Integer.parseInt(txtNumero.getText()))
                .precio(Float.parseFloat(txtPrecio.getText()))
                .tipoHabitacion(tipoHabitacion.getValue())
                .imagen(imageFotoPerfil.getImage())
                .description(txtDescripcion.getText())
                .build();
    }

    public void eliminarHabitacion(){
        listaHabitaciones.remove(selectedHabitacion);
        Sesion.getInstancia().getHabitaciones().remove(selectedHabitacion);
        limpiarCampos();
        limpiarSeleccion();
    }

    private void actualizarHabitacion(){
        if (selectedHabitacion != null){
            Habitacion actualizado = buildHabitacion();
            selectedHabitacion.setTipoHabitacion(actualizado.getTipoHabitacion());
            selectedHabitacion.setDescription(actualizado.getDescription());
            selectedHabitacion.setCapacidad(actualizado.getCapacidad());
            selectedHabitacion.setNumero(actualizado.getNumero());
            selectedHabitacion.setPrecio(actualizado.getPrecio());
            selectedHabitacion.setImagen(actualizado.getImagen());
            actualizarTabla();
            limpiarSeleccion();
            limpiarCampos();
        }
    }

    private void actualizarTabla() {
        listaHabitaciones.setAll(Sesion.getInstancia().getHabitaciones());
    }

    private void limpiarCampos() {
        txtCapacidad.clear();
        txtNumero.clear();
        txtPrecio.clear();
        txtDescripcion.clear();
        tipoHabitacion.setValue(null);
        imageFotoPerfil.setImage(null);
    }

    private void limpiarSeleccion() {
        tblHabitaciones.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    // Funcion para seleccionar foto de perfil del contacto
    public void seleccionarFotoPerfil() {
        FileChooser fileChooser = new FileChooser();

        // Filtro para permitir solo imágenes
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg")
        );

        // Mostrar el diálogo de selección de archivos
        File archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            // Convertir el archivo a una imagen y mostrarlo en el ImageView
            Image imagen = new Image(archivoSeleccionado.toURI().toString());
            imageFotoPerfil.setImage(imagen);
        }
    }

    public List<Habitacion> getListaHabitaciones() {
        return new ArrayList<>(listaHabitaciones);
    }
}
