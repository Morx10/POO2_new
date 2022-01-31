/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Data.CONSTANTES;
import Data.ExplorationData;
import Data.RoversData;
import espol.proyectopoo2.VistaExplorar2_0Controller;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * Información y métodos del rover
 * @author Grupo#1 Paralelo#3 POO
 */
public abstract class Rovers implements Acciones {
    
    /**
     *
     */
    public String nombreRover;
    private double ubicacionx;
    private double ubicaciony;
    private double bateria;
    private double grados;
    
    /**
     * Constructor del rover
     * @param nombreRover nombre del rover
     * @param ubicacionx Coordenada x (longitud) del rover
     * @param ubicaciony Coordenada y (latitud) del rover
     * @param grados Cantidad de grados por rotar del rover
     */
    public Rovers(String nombreRover, double ubicacionx, double ubicaciony, double grados) {
        this.nombreRover = nombreRover;
        this.ubicacionx = ubicacionx;
        this.ubicaciony = ubicaciony;
        this.bateria = 100;
        this.grados = grados;
    }      
    
    /**
     * Indica al rover que debe moverse hacia delante
     */
    @Override
    public void avanzar() {
        if(bateria>0){
            bateria=bateria-1;
        double ubicacionX =getUbicacionx();
        double ubicacionY =getUbicaciony();
        
        setUbicacionx(Math.cos(Math.toRadians(grados))*(10*(Math.pow(2,1/2)))+ubicacionx);
        setUbicaciony(Math.sin(Math.toRadians(grados))*(10*(Math.pow(2,1/2)))+ubicaciony);
        VistaExplorar2_0Controller.moverobjeto(ubicacionX, ubicacionY);
        
        RoversData.escribirRover();
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bateria insuficiente, Ingrese el comando cargar");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        
        
        
                
    }
    
    /**
     * Indica al rover que debe rotar en sentido de las manecillas del reloj
     * @param girar Cantidad de grados por rotar 
     */
    @Override
    public void girar(double girar) {
        grados= grados+girar;
        VistaExplorar2_0Controller.rotar(grados);
        RoversData.escribirRover();
        
        
    }

    /**
     * Indica al rover que debe moverse a un punto específico del planeta
     * @param x Coordenada x (longitud) del rover
     * @param y Coordenada y (latitud) del rover
     */
    @Override
    public void dirigirse(double x, double y) {
        
            double dirX= x-ubicacionx;
        double dirY= y-ubicaciony;
       
        double hypot = Math.hypot(dirX,dirY);
        double newbateria= bateria - (hypot/10 );
        if(newbateria>0){           
            bateria=newbateria;
            System.out.println(bateria);
            double ubicacionX= ubicacionx;
            double ubicacionY= ubicaciony;
        
            ubicacionx=x;
            ubicaciony=y;
     
            VistaExplorar2_0Controller.moverobjeto(ubicacionX, ubicacionY);
            RoversData.escribirRover();          
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bateria insuficiente, No puede llegar a ese destino");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        
    }
    
    /**
     * Indica al rover que debe sensar al suelo en su posición actual
     * @param c Crater a ser sensado
     */
    @Override
    public void sensar(Crater c) {
        try{          
                System.out.println(c.getNombrecrater());
                List<String> minerales= CONSTANTES.minerales;
                ArrayList<String> aniadir= new ArrayList<>();
                int numero = (int)(Math.random()*10+1);
                
                for(int i=0;i<=numero;i++){
                    int index = (int)(Math.random() * minerales.size());                   
                    String mineral= minerales.get(index);
                    System.out.println(mineral);
                    aniadir.add(mineral);             
                }
                if(c.getMinerales()==null){                   
                    c.setMinerales(aniadir);
                }else{
                    c.aniadirMinerales(aniadir);
                }
                
                ExplorationData.escribirExploracion(c);
       
        }catch(Exception ex){
            System.out.println("error");
            
        }
    }
    
    /**
     * Indica al rover que inicie la operación de recarga
     */
    @Override
    public abstract void cargar();
    
    /**
     * Conversión a String del objeto rover
     * @return Representación en String del objeto rover
     */
    @Override
    public String toString() {
        return "Rover=" + nombreRover ;
    }

    /**
     * Devuelve el nombre del rover
     * @return nombreRover nombre del Rover
     */
    public String getNombreRover() {
        return nombreRover;
    }

    /**
     * Actualiza el nombre del rover
     * @param nombreRover Nuevo nombre del rover
     */
    public void setNombreRover(String nombreRover) {
        this.nombreRover = nombreRover;
    }

    /**
     * Devuelve la coordenada x (longitud) del rover
     * @return ubicacionx Coordenada x (longitud) del rover
     */
    public double getUbicacionx() {
        return ubicacionx;
    }

    /**
     * Actualiza la coordenada x (longitud) del rover
     * @param ubicacionx Nueva coordenada x (longitud) del rover
     */
    public void setUbicacionx(double ubicacionx) {
        this.ubicacionx = ubicacionx;
    }

    /**
     * Devuelve la coordenada y (latitud) del rover
     * @return ubicaciony Coordenada y (latitud) del rover
     */
    public double getUbicaciony() {
        return ubicaciony;
    }

    /**
     * Actualiza la coordenada y (latitud) del rover
     * @param ubicaciony Nueva coordenada y (latitud) del rover
     */
    public void setUbicaciony(double ubicaciony) {
        this.ubicaciony = ubicaciony;
    }

    /**
     * Devuelve el valor del nivel de batería del rover
     * @return bateria Porcentaje de carga de la batería
     */
    public double getBateria() {
        return bateria;
    }

    /**
     * Actualiza el nivel de batería del rover
     * @param bateria Porcentaje actual de carga de la batería
     */
    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    /**
     * Devuelve la cantidad de grados por rotar del rover
     * @return grados Cantidad de grados por rotar del rover
     */
    public double getGrados() {
        return grados;
    }

    /**
     * Actualiza los grados por rotar del rover
     * @param grados Nueva cantidad de grados por rotar del rover
     */
    public void setGrados(double grados) {
        this.grados = grados;
    }
    
    /**
     * Actualiza la cantidad de grados por rotar del rover a 0
     */
    public void resetGrados(){
        this.grados=0;
    } 
}
