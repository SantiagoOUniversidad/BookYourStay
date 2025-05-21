package co.edu.uniquindio.bookyourstay;

import co.edu.uniquindio.bookyourstay.controladores.ControladorPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookYourStayApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml","Inicio Sesion", stage);
    }

    public static void main(String[] args) {
        launch();
    }
}