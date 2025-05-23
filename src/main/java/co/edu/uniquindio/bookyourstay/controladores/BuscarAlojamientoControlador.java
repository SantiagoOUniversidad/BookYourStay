package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.enums.TipoAlojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.vo.Sesion;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import co.edu.uniquindio.bookyourstay.servicios.FacturaServicios;
import co.edu.uniquindio.bookyourstay.utils.Factura;
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarAlojamientoControlador {

    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    BookYourStayServicio bookYourStayServicio = controladorPrincipal.getBookYourStayServicio();
    private final Cliente cliente = Sesion.getInstancia().getCliente();
    FacturaServicios factura = new FacturaServicios(null, null, 0, 0);

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
            private final Label capacidadInfoLabel = new Label(); // Para mostrar la capacidad max del alojamiento

            // Nuevos controles para cada celda
            private final DatePicker fechaInicioPicker = new DatePicker();
            private final DatePicker fechaFinPicker = new DatePicker();
            private final Spinner<Integer> huespedesSpinner = new Spinner<>();
            private final Button reservarButton = new Button("Reservar");

            private final VBox detallesAlojamientoVBox = new VBox(2); // Para nombre, tipo/ciudad, precio, capacidad
            private final GridPane reservaControlsGridPane = new GridPane(); // Para organizar controles de reserva
            private final VBox infoYReservaVBox = new VBox(10); // Para detalles del alojamiento Y controles de reserva
            private final HBox contentHBox = new HBox(10); // HBox principal: imagen | (info + controles reserva)

            { // Bloque de inicialización para la celda
                // Estilos de Labels
                nombreLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
                precioLabel.setFont(Font.font("System", FontWeight.BOLD, 13));
                precioLabel.setStyle("-fx-text-fill: green;");

                // Configuración ImageView
                imageView.setFitHeight(100); // Un poco más de altura para acomodar más controles
                imageView.setFitWidth(150);
                imageView.setPreserveRatio(false);

                // Configuración DatePickers
                fechaInicioPicker.setPromptText("Fecha Inicio");
                fechaFinPicker.setPromptText("Fecha Fin");
                // Opcional: deshabilitar fechas pasadas
                fechaInicioPicker.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.compareTo(LocalDate.now()) < 0 );
                    }
                });
                fechaFinPicker.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate fInicio = fechaInicioPicker.getValue();
                        if (fInicio != null) {
                            setDisable(empty || date.compareTo(fInicio.plusDays(1)) < 0 );
                        } else {
                            setDisable(empty || date.compareTo(LocalDate.now().plusDays(1)) < 0 );
                        }
                    }
                });
                // Actualizar el dayCellFactory de fechaFin cuando fechaInicio cambia
                fechaInicioPicker.valueProperty().addListener((obs, oldDate, newDate) -> {
                    fechaFinPicker.setValue(null); // Limpiar fecha fin si cambia inicio
                    // Forzar re-renderizado de las celdas de fechaFinPicker para aplicar nueva lógica de deshabilitación
                    fechaFinPicker.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
                        public void updateItem(LocalDate date, boolean empty) {
                            super.updateItem(date, empty);
                            if (newDate != null) {
                                setDisable(empty || date.compareTo(newDate.plusDays(1)) < 0);
                            } else {
                                setDisable(empty || date.compareTo(LocalDate.now().plusDays(1)) < 0);
                            }
                        }
                    });
                });


                // Configuración Spinner para Huéspedes
                // El rango (1 a capacidadMaxima) se establecerá en updateItem
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1, 1); // (min, max, initial)
                huespedesSpinner.setValueFactory(valueFactory);
                huespedesSpinner.setEditable(true); // Permitir escribir el número
                huespedesSpinner.setPrefWidth(80); // Ancho para el spinner

                // Organizar detalles del alojamiento
                detallesAlojamientoVBox.getChildren().addAll(nombreLabel, tipoCiudadLabel, capacidadInfoLabel, precioLabel);

                // Organizar controles de reserva en un GridPane para mejor alineación
                reservaControlsGridPane.setHgap(5);
                reservaControlsGridPane.setVgap(5);
                reservaControlsGridPane.add(new Label("Check-in:"), 0, 0);
                reservaControlsGridPane.add(fechaInicioPicker, 1, 0);
                reservaControlsGridPane.add(new Label("Check-out:"), 0, 1);
                reservaControlsGridPane.add(fechaFinPicker, 1, 1);
                reservaControlsGridPane.add(new Label("Huéspedes:"), 0, 2);
                reservaControlsGridPane.add(huespedesSpinner, 1, 2);
                // El botón de reservar podría ir debajo o al lado. Lo pondré debajo del GridPane.

                // VBox para combinar detalles del alojamiento y los controles de reserva
                infoYReservaVBox.getChildren().addAll(detallesAlojamientoVBox, reservaControlsGridPane, reservarButton);
                infoYReservaVBox.setAlignment(Pos.TOP_LEFT);
                VBox.setVgrow(detallesAlojamientoVBox, Priority.NEVER);
                VBox.setVgrow(reservaControlsGridPane, Priority.NEVER);

                // HBox principal
                contentHBox.getChildren().addAll(imageView, infoYReservaVBox);
                contentHBox.setAlignment(Pos.CENTER_LEFT); // O Pos.TOP_LEFT
                contentHBox.setPadding(new Insets(10)); // Más padding general
                HBox.setHgrow(infoYReservaVBox, Priority.ALWAYS);
            }

            @Override
            protected void updateItem(Alojamiento alojamiento, boolean empty) {
                super.updateItem(alojamiento, empty);

                if (empty || alojamiento == null) {
                    setText(null);
                    setGraphic(null);
                    // Limpiar listeners si los hubiera para evitar memory leaks con celdas reutilizadas
                    reservarButton.setOnAction(null);
                } else {
                    nombreLabel.setText(alojamiento.getNombre());
                    tipoCiudadLabel.setText(String.format("%s en %s",
                            alojamiento.getTipoAlojamiento() != null ? alojamiento.getTipoAlojamiento().toString() : "N/A",
                            alojamiento.getCiudad()));
                    capacidadInfoLabel.setText("Capacidad Máx: " + alojamiento.getCapacidadMax() + " personas");
                    precioLabel.setText(String.format("$%.2f / noche", alojamiento.getPrecioPorNoche()));

                    Image img = alojamiento.getImagen();
                    imageView.setImage(img != null ? img : defaultPlaceholderImage);

                    // Configurar el rango del Spinner de huéspedes basado en la capacidad del alojamiento
                    // y resetear su valor a 1.
                    SpinnerValueFactory<Integer> valueFactory = (SpinnerValueFactory<Integer>) huespedesSpinner.getValueFactory();
                    if (valueFactory != null) {
                        valueFactory.setValue(1); // Resetear a 1 huésped por defecto
                    } else {
                        // Si valueFactory es null por alguna razón, crearlo de nuevo
                        SpinnerValueFactory<Integer> newValueFactory =
                                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, alojamiento.getCapacidadMax(), 1);
                        huespedesSpinner.setValueFactory(newValueFactory);
                    }
                    // Limpiar selecciones de fecha
                    fechaInicioPicker.setValue(null);
                    fechaFinPicker.setValue(null);

                    // Configurar la acción del botón para ESTE alojamiento específico
                    reservarButton.setOnAction(event -> {
                        BilleteraVirtual billetera = cliente.getBilleteraVirtual();
                        LocalDate fechaInicio = fechaInicioPicker.getValue();
                        LocalDate fechaFin = fechaFinPicker.getValue();
                        int numeroDias = (int) Duration.between(fechaInicio, fechaFin).toDays();
                        int numHuespedes = huespedesSpinner.getValue();
                        float precioParcial = alojamiento.getPrecioPorNoche() * numeroDias;
                        float precioTotal = precioParcial + alojamiento.getCostoExtra();
                        if (cliente.getBilleteraVirtual() == null || billetera.getSaldo() < precioTotal) {
                            controladorPrincipal.crearAlerta("No tienes saldo suficiente para realizar esta reserva.", Alert.AlertType.ERROR);
                        } else {
                            Factura facturaReserva = factura.generarFactura(LocalDate.now(), precioParcial, precioTotal);
                            factura.enviarFactura(facturaReserva, cliente.getEmail());
                            Reserva reserva = Reserva.builder().cliente(cliente).alojamiento(alojamiento).cantidadPersonas(numHuespedes).fechaInicio(fechaInicio).fechaFinal(fechaFin).precioTotal(precioTotal).factura(facturaReserva).build();
                            bookYourStayServicio.agregarReserva(reserva);
                            controladorPrincipal.crearAlerta("Reserva realizada con éxito.", Alert.AlertType.INFORMATION);
                        }
                        fechaInicioPicker.setValue(null);
                    });

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