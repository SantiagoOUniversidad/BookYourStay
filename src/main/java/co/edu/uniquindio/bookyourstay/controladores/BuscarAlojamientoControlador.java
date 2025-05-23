package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarAlojamientoControlador {

    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();

    @FXML
    private TextField txtBusqueda;

    @FXML
    private ComboBox<String> cbxTipoAlojamiento;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtPrecioMax;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnVolver;

    @FXML
    private ListView<Alojamiento> lvResultados;

    private ObservableList<Alojamiento> alojamientosMostrados = FXCollections.observableArrayList();

    private Image defaultPlaceholderImage;

    @FXML
    void onBuscar(ActionEvent event) throws Exception {
        aplicarFiltros();
    }

    @FXML
    void onLimpiar(ActionEvent event) {
        limpiarFiltros();
    }

    @FXML
    void onVolver(ActionEvent event) throws Exception {
        ControladorPrincipal.openView("PanelCliente.fxml", "Inicio", new Stage());
        ControladorPrincipal.cerrarVentana((Stage) btnVolver.getScene().getWindow());
    }

    @FXML
    public void initialize() {
        cbxTipoAlojamiento.getItems().addAll("Todos", "Casa", "Hotel", "Apartamento");
        cbxTipoAlojamiento.getSelectionModel().selectFirst();

        lvResultados.setItems(alojamientosMostrados);
        lvResultados.setPlaceholder(new Label("No hay alojamientos disponibles o que coincidan con la búsqueda."));

        lvResultados.setCellFactory(param -> new ListCell<Alojamiento>() {
            private final ImageView imageView = new ImageView();
            private final Label nombreLabel = new Label();
            private final Label tipoCiudadLabel = new Label();
            private final Label precioLabel = new Label();
            private final Label capacidadLabel = new Label();
            private final VBox textoVBox = new VBox(5);
            private final HBox contentHBox = new HBox(10);

            {
                nombreLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
                precioLabel.setFont(Font.font("System", FontWeight.BOLD, 13));
                precioLabel.setStyle("-fx-text-fill: green;");

                imageView.setFitHeight(80);
                imageView.setFitWidth(120);
                imageView.setPreserveRatio(false);

                // Organizar los labels de texto verticalmente
                textoVBox.getChildren().addAll(nombreLabel, tipoCiudadLabel, capacidadLabel, precioLabel);
                VBox.setVgrow(nombreLabel, Priority.ALWAYS);

                // Añadir la imagen y el VBox de textos al HBox principal
                contentHBox.getChildren().addAll(imageView, textoVBox);
                contentHBox.setAlignment(Pos.CENTER_LEFT);
                contentHBox.setPadding(new Insets(5));
            }

            @Override
            protected void updateItem(Alojamiento alojamiento, boolean empty) {
                super.updateItem(alojamiento, empty);

                if (empty || alojamiento == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    nombreLabel.setText(alojamiento.getNombre());
                    tipoCiudadLabel.setText(String.format("%s en %s",
                            alojamiento.getTipoAlojamiento(),
                            alojamiento.getCiudad()));
                    capacidadLabel.setText("Capacidad: " + alojamiento.getCapacidadMax() + " personas");
                    precioLabel.setText(String.format("$%.2f / noche", alojamiento.getPrecioPorNoche()));

                    Image img = alojamiento.getImagen();
                    if (img != null) {
                        imageView.setImage(img);
                    } else {
                        imageView.setImage(defaultPlaceholderImage);
                    }

                    setGraphic(contentHBox);
                    setText(null);
                }
            }
        });
        cargarAlojamientosIniciales();
    }

    private void cargarAlojamientosIniciales() {
        if (bookYourStayServicio != null) {
            List<Alojamiento> todosLosAlojamientos = bookYourStayServicio.getAlojamientos();
            alojamientosMostrados.setAll(todosLosAlojamientos);
        } else {
            System.err.println("AlojamientoService no está inicializado.");
            lvResultados.setPlaceholder(new Label("Error: Servicio de alojamientos no disponible."));
        }
    }

    private void aplicarFiltros() throws Exception {
        String textoBusquedaNombre = txtBusqueda.getText().trim();
        String ciudadBusqueda = txtCiudad.getText().trim();
        String tipoSeleccionadoTxt = cbxTipoAlojamiento.getValue();
        TipoAlojamiento tipoSeleccionadoEnum = null;

        if (tipoSeleccionadoTxt != null && !tipoSeleccionadoTxt.equalsIgnoreCase("Todos")) {
            if (tipoSeleccionadoTxt.equals(TipoAlojamiento.CASA.toString())) {
                tipoSeleccionadoEnum = TipoAlojamiento.CASA;
            } else if (tipoSeleccionadoTxt.equals(TipoAlojamiento.HOTEL.toString())) {
                tipoSeleccionadoEnum = TipoAlojamiento.HOTEL;
            } else if (tipoSeleccionadoTxt.equals(TipoAlojamiento.APARTAMENTO.toString())) {
                tipoSeleccionadoEnum = TipoAlojamiento.APARTAMENTO;
            }
        }

        float precioMax = -1; // -1 indica sin filtro de precio máximo
        try {
            if (!txtPrecioMax.getText().trim().isEmpty()) {
                precioMax = Float.parseFloat(txtPrecioMax.getText().trim());
                if (precioMax < 0) {
                    controladorPrincipal.crearAlerta("El precio máximo no puede ser negativo. Se ignorará este filtro.", Alert.AlertType.WARNING);
                    precioMax = -1;
                }
            }
        } catch (NumberFormatException e) {
            controladorPrincipal.crearAlerta("El valor para precio máximo no es un número válido. Se ignorará este filtro.", Alert.AlertType.ERROR);
            precioMax = -1;
        }

        List<Alojamiento> resultadosFinales = new ArrayList<>();

        if (!textoBusquedaNombre.isEmpty()) {
            Alojamiento alojamientoEncontradoPorNombre = bookYourStayServicio.buscarAlojamientoPorNombre(textoBusquedaNombre);

            if (alojamientoEncontradoPorNombre != null) {
                boolean cumpleOtrosCriterios = true;

                // Verificar tipo
                if (tipoSeleccionadoEnum != null) {
                    if (alojamientoEncontradoPorNombre.getTipoAlojamiento() != tipoSeleccionadoEnum) {
                        cumpleOtrosCriterios = false;
                    }
                }

                // Verificar ciudad (solo si el criterio de tipo aún se cumple)
                if (cumpleOtrosCriterios && !ciudadBusqueda.isEmpty()) {
                    // Asumiendo que Alojamiento tiene un getCiudad()
                    if (!alojamientoEncontradoPorNombre.getCiudad().equalsIgnoreCase(ciudadBusqueda)) {
                        cumpleOtrosCriterios = false;
                    }
                }

                if (cumpleOtrosCriterios && precioMax >= 0) {
                    if (alojamientoEncontradoPorNombre.getPrecioPorNoche() > precioMax) {
                        cumpleOtrosCriterios = false;
                    }
                }

                if (cumpleOtrosCriterios) {
                    resultadosFinales.add(alojamientoEncontradoPorNombre);
                }

            } else {
            }

        } else {
            List<Alojamiento> alojamientosBase = new ArrayList<>(bookYourStayServicio.getAlojamientos());

            if (tipoSeleccionadoEnum != null) {
                List<Alojamiento> porTipo = bookYourStayServicio.buscarAlojamientoPorTipo(tipoSeleccionadoEnum);
                if (porTipo != null) {
                    alojamientosBase.retainAll(new HashSet<>(porTipo));
                } else {
                    alojamientosBase.clear();
                }
            }
            if (!ciudadBusqueda.isEmpty()) {
                List<Alojamiento> porCiudad = bookYourStayServicio.buscarAlojamientoPorCiudad(ciudadBusqueda);
                if (porCiudad != null) {
                    alojamientosBase.retainAll(new HashSet<>(porCiudad));
                } else {
                    alojamientosBase.clear();
                }
            }

            if (precioMax >= 0) {
                final float precioMaxFinal = precioMax;
                resultadosFinales = alojamientosBase.stream()
                        .filter(alojamiento -> alojamiento.getPrecioPorNoche() <= precioMaxFinal)
                        .collect(Collectors.toList());
            } else {
                resultadosFinales.addAll(alojamientosBase);
            }
        }

        alojamientosMostrados.setAll(resultadosFinales);

        if (resultadosFinales.isEmpty()) {
            controladorPrincipal.crearAlerta("No se encontraron alojamientos que coincidan con la búsqueda.", Alert.AlertType.INFORMATION);
        } else {
            controladorPrincipal.crearAlerta("Se encontraron " + resultadosFinales.size() + " alojamientos que coinciden con la búsqueda.", Alert.AlertType.INFORMATION);
        }
    }


    @FXML
    private void limpiarFiltros() {
        txtBusqueda.clear();
        cbxTipoAlojamiento.getSelectionModel().select("Todos"); // O el índice 0
        txtCiudad.clear();
        txtPrecioMax.clear();
        cargarAlojamientosIniciales();
    }

}