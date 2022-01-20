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
 * FXML Controller class
 *
 * @author sanch
 */
public class VistaPrincipalController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Explorar(MouseEvent event) throws IOException {
        Parent root = App.loadFXML("VistaExplorar");
        App.setRoot(root);  
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
    
}
