module co.edu.uniquindio.bookyourstay {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.mail;


    opens co.edu.uniquindio.bookyourstay to javafx.fxml;
    exports co.edu.uniquindio.bookyourstay;
}