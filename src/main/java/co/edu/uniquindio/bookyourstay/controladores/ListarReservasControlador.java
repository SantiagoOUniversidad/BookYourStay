package co.edu.uniquindio.bookyourstay.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.bookyourstay.modelo.entidades.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListarReservasControlador {
    // Obtenemos la instancia de el controlador principal
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
    Reserva selectedReserva;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Reserva, String> tbcCantidadPersonas;

    @FXML
    private TableColumn<Reserva, String> tbcFechaFinal;

    @FXML
    private TableColumn<Reserva, String> tbcFechaInicio;

    @FXML
    private TableColumn<Reserva, String> tbcNombreAlojamiento;

    @FXML
    private TableColumn<Reserva, String> tbcPrecioTotal;

    @FXML
    private TableView<Reserva> tblReservas;

    @FXML
    void eliminarReservaAction(ActionEvent event) {
        eliminarReserva();
    }

    @FXML
    void volverAction(ActionEvent event) throws IOException {
        ControladorPrincipal.openView("PanelCliente.fxml", "Panel Cliente", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) tblReservas.getScene().getWindow());
    }

    @FXML
    void initialize() {
        assert tbcCantidadPersonas != null : "fx:id=\"tbcCantidadPersonas\" was not injected: check your FXML file 'ListarReservas.fxml'.";
        assert tbcFechaFinal != null : "fx:id=\"tbcFechaFinal\" was not injected: check your FXML file 'ListarReservas.fxml'.";
        assert tbcFechaInicio != null : "fx:id=\"tbcFechaInicio\" was not injected: check your FXML file 'ListarReservas.fxml'.";
        assert tbcNombreAlojamiento != null : "fx:id=\"tbcNombreAlojamiento\" was not injected: check your FXML file 'ListarReservas.fxml'.";
        assert tbcPrecioTotal != null : "fx:id=\"tbcPrecioTotal\" was not injected: check your FXML file 'ListarReservas.fxml'.";
        assert tblReservas != null : "fx:id=\"tblReservas\" was not injected: check your FXML file 'ListarReservas.fxml'.";
        initView();
    }

    private void initView() {
        initDataBinding();

        obtenerReservas();

        tblReservas.getItems().clear();

        tblReservas.setItems(listaReservas);

        listenerSelection();
    }

    private void initDataBinding() {
        tbcCantidadPersonas.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidadPersonas())));
        tbcPrecioTotal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioTotal())));
        tbcFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        tbcFechaFinal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFinal().toString()));
        tbcNombreAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().getNombre()));
    }

    private void obtenerReservas() {
        listaReservas.addAll(bookYourStayServicio.listarReservasCliente(Sesion.getInstancia().getCliente()));
    }

    private void listenerSelection() {
        tblReservas.setOnMouseClicked(e -> {
            //Obtener la nota seleccionada
            Reserva selected = tblReservas.getSelectionModel().getSelectedItem();
            if(selected != null){
                selectedReserva = selected;
            }
        });
    }

    private void eliminarReserva() {
        bookYourStayServicio.eliminarReserva(selectedReserva);
        listaReservas.remove(selectedReserva);
        tblReservas.getSelectionModel().clearSelection();
    }
}
