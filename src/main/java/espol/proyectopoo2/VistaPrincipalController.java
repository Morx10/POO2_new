/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.proyectopoo2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;


/**
 * Clase controladora FXML para la Vista Principal
 *
 * @author sanch
 */
public class VistaPrincipalController implements Initializable {

    /**
     * Inicializa la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    


    @FXML
    private void planificarRutas(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaPlanificar");
        App.setRoot(root); 
    }

    @FXML
    private void verReportes(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaReporte");
        App.setRoot(root); 
    }

    @FXML
    private void Salir(MouseEvent event) {
        Platform.exit();
    }



    @FXML
    private void Explorar(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaExplorar2_0");
        App.setRoot(root);
    }
    
}
