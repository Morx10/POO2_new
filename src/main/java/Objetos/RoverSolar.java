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
    

    public RoverSolar(String nombreRover, double ubicacionx, double ubicaciony, double grados) {
        super(nombreRover, ubicacionx, ubicaciony, grados);
    }




    @Override
    public void cargar() {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("abriendo paneles");
        alert.setHeaderText(null);
        alert.showAndWait();
        
        super.girar(-super.getGrados());
      
        
        
        double ubicacionX=super.getUbicacionx();
        double ubicacionY=super.getUbicaciony();
        super.setUbicacionx(100);
        super.setUbicaciony(100);
        if(ubicacionY==100){
            if(ubicacionX>100){
                VistaExplorar2_0Controller.rotar(180);
            }           
        }else if(ubicacionX==100){
            if(ubicacionY>100){
                VistaExplorar2_0Controller.rotar(-90);
            }          
        }else{
             super.girar(Math.toDegrees(Math.atan((100-ubicacionY)/(100-ubicacionX))));
        }
       
        
        VistaExplorar2_0Controller.moverobjeto(ubicacionX, ubicacionY);
        super.setBateria(100);
        
     
        
    }
    
}
