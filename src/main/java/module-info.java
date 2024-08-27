module uniquindio.edu.co.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens uniquindio.edu.co.agenda to javafx.fxml;
    exports uniquindio.edu.co.agenda;
}