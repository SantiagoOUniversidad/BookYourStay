package co.edu.uniquindio.bookyourstay;

import co.edu.uniquindio.bookyourstay.controladores.ControladorPrincipal;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.entidades.Cliente;
import co.edu.uniquindio.bookyourstay.servicios.BookYourStayServicio;
import javafx.application.Application;
import javafx.stage.Stage;

public class BookYourStayApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BookYourStayServicio bookYourStay = ControladorPrincipal.getInstancia().getBookYourStayServicio();
        ControladorPrincipal.openView("InicioSesionCliente.fxml","Inicio Sesion", stage);

        // Creamos el administrador
        Administrador administrador = new Administrador("0000", "Admin", "3023130890",
                "santiago.olarteb@uqvirtual.edu.co", "123");
        ControladorPrincipal.getInstancia().getBookYourStayServicio().agregarAdministrador(administrador);

        // Creamos un cliente de prueba
        Cliente clientePrueba = new Cliente("0001", "Prueba", "3112417691","olartebuitrago@icloud.com", "321", null);
        bookYourStay.agregarCliente(clientePrueba);
    }

    public static void main(String[] args) {
        launch();
    }
}