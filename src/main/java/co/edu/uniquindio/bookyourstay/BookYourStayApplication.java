package co.edu.uniquindio.bookyourstay;

import co.edu.uniquindio.bookyourstay.controladores.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.BookYourStay;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.entidades.ClienteTemporal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookYourStayApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BookYourStay bookYourStay = BookYourStay.getInstancia();
        ControladorPrincipal.openView("InicioSesionCliente.fxml","Inicio Sesion", stage);

        // Creamos el administrador
        Administrador administrador = new Administrador("0000", "Admin", "3023130890",
                "santiago.olarteb@uqvirtual.edu.co", "123");
        ControladorPrincipal.getInstancia().getBookYourStayServicio().agregarAdministrador(administrador);

        // Creamos un cliente de prueba
        Cliente clientePrueba = new Cliente("0001", "Prueba", "3112417691","olartebuitrago@icloud.com", "321");
        bookYourStay.clientes.add(clientePrueba);
    }

    public static void main(String[] args) {
        launch();
    }
}