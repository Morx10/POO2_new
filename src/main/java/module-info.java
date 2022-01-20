module espol.proyectopoo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens espol.proyectopoo2 to javafx.fxml;
    exports espol.proyectopoo2;
}
