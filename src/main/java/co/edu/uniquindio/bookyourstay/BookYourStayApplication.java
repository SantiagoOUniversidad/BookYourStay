package co.edu.uniquindio.bookyourstay;

import co.edu.uniquindio.bookyourstay.controladores.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookYourStayApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControladorPrincipal.openView("InicioSesionCliente.fxml","Inicio Sesion", stage);

        Administrador administrador = Administrador.crearInstancia("0000", "Innombrable", "3023130890",
                "santiago.olarteb@uqvirtual.edu.co", "123");
    }

    public static void main(String[] args) {
        launch();
    }
}