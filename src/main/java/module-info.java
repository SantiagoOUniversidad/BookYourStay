module co.edu.uniquindio.bookyourstay {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.mail;
    requires java.desktop;


    opens co.edu.uniquindio.bookyourstay to javafx.fxml;
    exports co.edu.uniquindio.bookyourstay;
    exports co.edu.uniquindio.bookyourstay.modelo.entidades;
    exports co.edu.uniquindio.bookyourstay.modelo.vo;
    exports co.edu.uniquindio.bookyourstay.controladores;
    opens co.edu.uniquindio.bookyourstay.controladores to javafx.fxml;
}