module espol.proyectopoo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
 

    opens espol.proyectopoo2 to javafx.fxml;
    exports espol.proyectopoo2;
    opens Objetos to javafx.base;
   
    
}
