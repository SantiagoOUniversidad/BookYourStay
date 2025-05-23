package co.edu.uniquindio.bookyourstay.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Habitacion;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Resena;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ResenasControlador {
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    ObservableList<Resena> listaResenas = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Resena, String> tbcCalificacion;

    @FXML
    private TableColumn<Resena, String> tbcComentarios;

    @FXML
    private TableColumn<Resena, String> tbcNombre;

    @FXML
    private TableView<Resena> tblResenas;

    @FXML
    private TextField txtCalificacion;

    @FXML
    private TextArea txtComentarios;

    @FXML
    private TextField txtNombre;

    @FXML
    void agregarResenaAction(ActionEvent event) throws Exception {
        agregarResena();
    }

    @FXML
    void volverAction(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("PanelCliente.fxml", "Panel", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) txtCalificacion.getScene().getWindow());
    }

    @FXML
    void initialize() {
        assert tbcCalificacion != null : "fx:id=\"tbcCalificacion\" was not injected: check your FXML file 'Resenas.fxml'.";
        assert tbcComentarios != null : "fx:id=\"tbcComentarios\" was not injected: check your FXML file 'Resenas.fxml'.";
        assert tbcNombre != null : "fx:id=\"tbcNombre\" was not injected: check your FXML file 'Resenas.fxml'.";
        assert tblResenas != null : "fx:id=\"tblResenas\" was not injected: check your FXML file 'Resenas.fxml'.";
        assert txtCalificacion != null : "fx:id=\"txtCalificacion\" was not injected: check your FXML file 'Resenas.fxml'.";
        assert txtComentarios != null : "fx:id=\"txtComentarios\" was not injected: check your FXML file 'Resenas.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'Resenas.fxml'.";
        initView();
    }

    private void initView() {
        initDataBinding();

        obtenerListaResenas();

        tblResenas.getItems().clear();

        tblResenas.setItems(listaResenas);
    }

    private void obtenerListaResenas() {
        listaResenas.addAll(bookYourStayServicio.listarResena());
    }

    private void initDataBinding() {
        tbcCalificacion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCalificacion())));
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
        tbcComentarios.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComentarios()));
    }

    private void agregarResena() throws Exception {
        try {
            Alojamiento alojamiento = bookYourStayServicio.buscarAlojamientoNombre(txtNombre.getText());
            Resena resena = Resena.builder()
                    .alojamiento(alojamiento)
                    .comentarios(txtComentarios.getText())
                    .calificacion(Integer.parseInt(txtCalificacion.getText()))
                    .build();
            listaResenas.add(resena);
            bookYourStayServicio.agregarResena(resena);
            controladorPrincipal.crearAlerta("Reseña publicada con exito", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

}
