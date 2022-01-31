/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Data.RoversData;
import espol.proyectopoo2.VistaExplorar2_0Controller;
import javafx.scene.control.Alert;

/**
 *
 * @author marit
 */
public class RoverEolica extends Rovers {


    public RoverEolica(String nombreRover, double ubicacionx, double ubicaciony, double grados) {
        super(nombreRover, ubicacionx, ubicaciony,grados);
    }

    @Override
    public void cargar() {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("despliegue de molinos");
        alert.setHeaderText(null);
        alert.showAndWait();
        VistaExplorar2_0Controller.rotar(-90);
        RoversData.escribirRover();
        super.setBateria(100);
    }
    
}
