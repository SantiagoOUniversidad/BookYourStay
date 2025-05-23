package co.edu.uniquindio.bookyourstay.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionarOfertasControlador {
    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    // Crear lista de la tabla
    ObservableList<Oferta> listaOfertas = FXCollections.observableArrayList();
    Oferta selectedOferta;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datePickerFinal;

    @FXML
    private DatePicker datePickerInicio;

    @FXML
    private TableColumn<Oferta, String> tbcDescuento;

    @FXML
    private TableColumn<Oferta, String> tbcFechaFinal;

    @FXML
    private TableColumn<Oferta, String> tbcFechaInicio;

    @FXML
    private TableColumn<Oferta, String> tbcNombre;

    @FXML
    private TableColumn<Oferta, String> tbcPrecioNormal;

    @FXML
    private TableView<Oferta> tblOferta;

    @FXML
    private TextField txtNombreAlojamiento;

    @FXML
    private TextField txtPrecioDescuento;

    @FXML
    void onActualizarOferta(ActionEvent event) throws Exception {
        actualizarOferta();
    }

    @FXML
    void onAgregarOferta(ActionEvent event) {
        agregarOferta();
    }

    @FXML
    void onEliminarOferta(ActionEvent event) throws Exception {
        eliminarOferta();
    }

    @FXML
    void initialize() {
        assert datePickerFinal != null : "fx:id=\"datePickerFinal\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert datePickerInicio != null : "fx:id=\"datePickerInicio\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert tbcDescuento != null : "fx:id=\"tbcDescuento\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert tbcFechaFinal != null : "fx:id=\"tbcFechaFinal\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert tbcFechaInicio != null : "fx:id=\"tbcFechaInicio\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert tbcNombre != null : "fx:id=\"tbcNombre\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert tbcPrecioNormal != null : "fx:id=\"tbcPrecioNormal\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert tblOferta != null : "fx:id=\"tblOferta\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert txtNombreAlojamiento != null : "fx:id=\"txtNombreAlojamiento\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        assert txtPrecioDescuento != null : "fx:id=\"txtPrecioDescuento\" was not injected: check your FXML file 'gestionarOfertas.fxml'.";
        initView();
    }

    private void initView() {
        // Traer datos a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerLista();

        //Limpiar la talba
        tblOferta.getItems().clear();

        // Agregara los elementos a la tabla
        tblOferta.setItems(listaOfertas);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void obtenerLista() {
        listaOfertas.addAll(bookYourStayServicio.getListaOfertas());
    }

    private void initDataBinding() {
        tbcDescuento.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDescuento())));
        tbcPrecioNormal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAlojamiento().getPrecioPorNoche())));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
        tbcDescuento.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAlojamiento().getPrecioPorNoche())));
        tbcFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        tbcFechaFinal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFinal().toString()));
    }

    private void listenerSelection() {
        tblOferta.setOnMouseClicked(e -> {
            //Obtener la nota seleccionada
            Oferta selected = tblOferta.getSelectionModel().getSelectedItem();
            if(selected != null){
                selectedOferta = selected;
                mostrarInformacionOferta(selectedOferta);
            }

        });
    }

    private void mostrarInformacionOferta(Oferta oferta) {
        if (oferta != null){
            txtNombreAlojamiento.setText(oferta.getAlojamiento().getNombre());
            txtPrecioDescuento.setText(String.valueOf(oferta.getDescuento()));
            datePickerFinal.setValue(oferta.getFechaFinal());
            datePickerInicio.setValue(oferta.getFechaInicio());
        }
    }

    private void agregarOferta() {
        try{
            Oferta oferta = buildOferta();
            listaOfertas.add(oferta);
            controladorPrincipal.crearAlerta("Oferta generada con exito", Alert.AlertType.INFORMATION);
            limpiarSeleccion();
            limpiarCampos();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos(){
        txtNombreAlojamiento.clear();
        txtPrecioDescuento.clear();
        datePickerInicio.setValue(null);
        datePickerFinal.setValue(null);
    }

    private Oferta buildOferta() throws Exception {
        if (bookYourStayServicio.buscarAlojamientoNombre(txtNombreAlojamiento.getText()) == null){
            throw new Exception("El alojamiento no existe");
        }

        return Oferta.builder()
                .descuento(Float.parseFloat(txtPrecioDescuento.getText()))
                .fechaInicio(datePickerInicio.getValue())
                .fechaFinal(datePickerFinal.getValue())
                .alojamiento(bookYourStayServicio.buscarAlojamientoNombre(txtNombreAlojamiento.getText()))
                .build();
    }

    private void eliminarOferta() throws Exception {
        bookYourStayServicio.eliminarOferta(selectedOferta);
        listaOfertas.remove(selectedOferta);
    }

    public void actualizarOferta() throws Exception {
        if(selectedOferta != null){
            bookYourStayServicio.actualizarOferta(selectedOferta,buildOferta());
            limpiarCampos();
            limpiarSeleccion();
            actualizarTabla();
        }
    }

    private void actualizarTabla(){
        listaOfertas.setAll(bookYourStayServicio.getListaOfertas());
    }

    private void limpiarSeleccion(){
        tblOferta.getSelectionModel().clearSelection();
        limpiarCampos();
    }
}