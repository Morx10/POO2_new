/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import javafx.scene.control.Alert;

/**
 *
 * @author marit
 */
public class RoverEolica extends Rovers {


    public RoverEolica(String nombreRover, double ubicacionx, double ubicaciony) {
        super(nombreRover, ubicacionx, ubicaciony);
    }

    @Override
    public void cargar() {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("despliegue de molinos");
        alert.setHeaderText(null);
        alert.showAndWait();
        super.setUbicacionx(100);
        super.setUbicaciony(100);
    }
    
}
