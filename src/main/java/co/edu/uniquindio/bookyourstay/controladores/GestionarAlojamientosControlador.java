package co.edu.uniquindio.bookyourstay.controladores;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoServicio;
import javafx.scene.control.*;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.servicios.AlojamientoServicio;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class GestionarAlojamientosControlador {
    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    // Crear lista de la tabla
    ObservableList<Alojamiento> listaAlojamientos = FXCollections.observableArrayList();
    Alojamiento selectedAlojamiento;

    @FXML
    private CheckBox checkBoxDesayuno;

    @FXML
    private CheckBox checkBoxPiscina;

    @FXML
    private CheckBox checkBoxWifi;

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
        actualizarAlojamiento();
    }

    @FXML
    void onAgregarAlojamientoAction(ActionEvent event) {
        agregarAlojamiento();
    }

    @FXML
    void onEliminarAlojamientoAction(ActionEvent event) {
        eliminarAlojamiento();
    }

    @FXML
    void onSeleccionarFotoAction(ActionEvent event) {
        seleccionarFotoPerfil();
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
        configuracionInicial();
        initView();
    }

    private void configuracionInicial(){
        tipoAlojamiento.getItems().addAll(TipoAlojamiento.values());
    }

    private void initView() {
        // Traer datos a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerAlojamientos();

        //Limpiar la talba
        tblAlojamientos.getItems().clear();

        // Agregara los elementos a la tabla
        tblAlojamientos.setItems(listaAlojamientos);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void initDataBinding() {
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcCapacidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCapacidadMax())));
        tbcCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        tbcPrecioNoche.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioPorNoche())));
        tbcTipoAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoAlojamiento().toString()));
    }

    // Obtener los Alojamientos
    private void obtenerAlojamientos(){
        listaAlojamientos.addAll(bookYourStayServicio.getAlojamientos());
    }

    // Funcion para seleccionar elementos de la tabla
    public void listenerSelection(){
        tblAlojamientos.setOnMouseClicked(e -> {
            //Obtener la nota seleccionada
            Alojamiento selected = tblAlojamientos.getSelectionModel().getSelectedItem();

            if(selected != null){
                selectedAlojamiento = selected;
                mostrarInformacionAlojamiento(selectedAlojamiento);
            }

        });
        ;
    }

    private void mostrarInformacionAlojamiento(Alojamiento alojamiento){
        if (alojamiento != null){
            txtNombre.setText(alojamiento.getNombre());
            txtCiudad.setText(alojamiento.getCiudad());
            txtCostoExtra.setText(String.valueOf(alojamiento.getCostoExtra()));
            txtCapacidadMaxima.setText(String.valueOf(alojamiento.getCapacidadMax()));
            txtDescripcion.setText(alojamiento.getDescription());
            txtPrecioNoche.setText(String.valueOf(alojamiento.getPrecioPorNoche()));
            imageFotoPerfil.setImage(alojamiento.getImagen());
            tipoAlojamiento.setValue(alojamiento.getTipoAlojamiento());
        }
    }

    private void agregarAlojamiento(){
        List<TipoServicio> servicios = new ArrayList<>();
        if(checkBoxPiscina.isSelected()){
            servicios.add(TipoServicio.PISCINA);
        }
        if(checkBoxWifi.isSelected()){
            servicios.add(TipoServicio.WIFI);
        }
        if(checkBoxDesayuno.isSelected()){
            servicios.add(TipoServicio.DESAYUNO);
        }
        try{
            Alojamiento alojamiento = bookYourStayServicio.agregarAlojamiento(tipoAlojamiento.getValue(),txtNombre.getText(),txtCiudad.getText(),txtDescripcion.getText(),imageFotoPerfil.getImage(),Float.parseFloat(txtPrecioNoche.getText()),Integer.parseInt(txtCapacidadMaxima.getText()),servicios,Float.parseFloat(txtCostoExtra.getText()),null);
            controladorPrincipal.crearAlerta("Alojamiento registradon con exito", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos(){
        txtNombre.clear();
        txtCiudad.clear();
        txtDescripcion.clear();
        txtCapacidadMaxima.clear();
        txtCostoExtra.clear();
        txtPrecioNoche.clear();
        tipoAlojamiento.setValue(null);
        imageFotoPerfil.setImage(null);
        checkBoxDesayuno.setSelected(false);
        checkBoxPiscina.setSelected(false);
        checkBoxWifi.setSelected(false);
    }

    // Función para construir un nuevo Alojamiento
    private Alojamiento buildAlojamiento() {
        List<TipoServicio> servicios = new ArrayList<>();
        if(checkBoxPiscina.isSelected()){
            servicios.add(TipoServicio.PISCINA);
        }
        if(checkBoxWifi.isSelected()){
            servicios.add(TipoServicio.WIFI);
        }
        if(checkBoxDesayuno.isSelected()){
            servicios.add(TipoServicio.DESAYUNO);
        }
        return Alojamiento.builder()
                .nombre(txtNombre.getText())
                .ciudad(txtCiudad.getText())
                .tipoAlojamiento(tipoAlojamiento.getValue())
                .precioPorNoche(Float.parseFloat(txtPrecioNoche.getText()))
                .capacidadMax(Integer.parseInt(txtCapacidadMaxima.getText()))
                .imagen(imageFotoPerfil.getImage())
                .description(txtDescripcion.getText())
                .servicios(servicios)
                .costoExtra(Float.parseFloat(txtCostoExtra.getText()))
                .build();
    }

    public void eliminarAlojamiento(){
            bookYourStayServicio.eliminarAlojamiento(selectedAlojamiento);
            listaAlojamientos.remove(selectedAlojamiento);
            limpiarCampos();
            limpiarSeleccion();
    }

    public void actualizarAlojamiento(){
        if (selectedAlojamiento != null){
            bookYourStayServicio.actualizarAlojamiento(selectedAlojamiento.getNombre(), buildAlojamiento());
          limpiarSeleccion();
          limpiarCampos();
          actualizarTabla();
        }
    }

    private void actualizarTabla(){
        listaAlojamientos.setAll(bookYourStayServicio.getAlojamientos());
    }

    private void limpiarSeleccion(){
        tblAlojamientos.getSelectionModel().clearSelection();
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

}

