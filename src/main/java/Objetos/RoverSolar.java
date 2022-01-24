/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import espol.proyectopoo2.VistaExplorar2_0Controller;
import java.util.HashSet;
import javafx.scene.control.Alert;

/**
 *
 * @author marit
 */
public class RoverSolar extends Rovers {
    

    public RoverSolar(String nombreRover, double ubicacionx, double ubicaciony) {
        super(nombreRover, ubicacionx, ubicaciony);
    }




    @Override
    public void cargar() {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("abriendo paneles");
        alert.setHeaderText(null);
        alert.showAndWait();
        
        double ubicacionX=super.getUbicacionx();
        double ubicacionY=super.getUbicaciony();
        super.setUbicacionx(100);
        super.setUbicaciony(100);
        
        VistaExplorar2_0Controller.moverobjeto(ubicacionX, ubicacionY);
        super.setBateria(100);
     
        
    }
    
}
