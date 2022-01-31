/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Data.RoversData;
import javafx.scene.control.Alert;

/**
 * Informacion y acciones del rover eólico
 * @author Grupo#1 Paralelo#3 POO
 */
public class RoverEolica extends Rovers {

    /**
     * Constructor del rover eólico
     * @param nombreRover Nombre del rover eólico
     * @param ubicacionx Coordenada x (longitud) del rover eólico
     * @param ubicaciony Coordenada y (latitud) del rover eólico
     * @param grados Cantidad de grados por rotar del rover eólico
     */
    public RoverEolica(String nombreRover, double ubicacionx, double ubicaciony, double grados) {
        super(nombreRover, ubicacionx, ubicaciony,grados);
    }
    
    /**
     * Inicio de la operación de recarga del rover eólico
     */
    @Override
    public void cargar() {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("despliegue de molinos");
        alert.setHeaderText(null);
        alert.showAndWait();
        super.resetGrados();
        super.girar(-90);
        RoversData.escribirRover();
        super.setBateria(100);
    }
    
}
