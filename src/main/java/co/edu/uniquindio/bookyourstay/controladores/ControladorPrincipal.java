package co.edu.uniquindio.bookyourstay.controladores;
import co.edu.uniquindio.bookyourstay.BookYourStayApplication;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

@Getter

public class ControladorPrincipal {
    // Atributos
    private static ControladorPrincipal instancia = new ControladorPrincipal();
    private final BookYourStayServicio bookYourStayServicio = new BookYourStayServicio();

    // Contructor
    private ControladorPrincipal() {}

    public static ControladorPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    // Crear alerta
    public void crearAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Funcion para abrir pantallas
    public static void openView(String archivo, String titulo, Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BookYourStayApplication.class.getResource(archivo));
            Parent rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle(titulo);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Función para cerrar ventanas
    public static void cerrarVentana(Stage stage){
        stage.close();
    }
}
