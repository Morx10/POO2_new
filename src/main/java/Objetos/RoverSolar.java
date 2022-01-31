/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Data.RoversData;
import espol.proyectopoo2.VistaExplorar2_0Controller;
import javafx.scene.control.Alert;

/**
 * Información y acciones del rover solar
 * @author Grupo#1 Paralelo#3 POO
 */
public class RoverSolar extends Rovers {
    
    /**
     * Constructor del rover solar
     * @param nombreRover Nombre del rover solar
     * @param ubicacionx Coordenada x (longitud) del rover solar
     * @param ubicaciony Coordenada y (latitud) del rover solar
     * @param grados Cantidad de grados por rotar del rover solar
     */
    public RoverSolar(String nombreRover, double ubicacionx, double ubicaciony, double grados) {
        super(nombreRover, ubicacionx, ubicaciony, grados);
    }

    /**
     * Inicio de la operación de recarga del rover solar
     */
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
        RoversData.escribirRover();
        
     
        
    }
    
}
