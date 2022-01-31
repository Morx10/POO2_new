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
     * @param string
     * @param d
     * @param d1
     * @param d2
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

    @Override
    public void girar(double girar) {
        grados= grados+girar;
        VistaExplorar2_0Controller.rotar(grados);
        RoversData.escribirRover();
        
        
    }

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

    @Override
    public abstract void cargar();
    
    @Override
    public String toString() {
        return "Rover=" + nombreRover ;
    }

    /**
     *
     * @return
     */
    public String getNombreRover() {
        return nombreRover;
    }

    /**
     *
     * @param nombreRover
     */
    public void setNombreRover(String nombreRover) {
        this.nombreRover = nombreRover;
    }

    /**
     *
     * @return
     */
    public double getUbicacionx() {
        return ubicacionx;
    }

    /**
     *
     * @param ubicacionx
     */
    public void setUbicacionx(double ubicacionx) {
        this.ubicacionx = ubicacionx;
    }

    /**
     *
     * @return
     */
    public double getUbicaciony() {
        return ubicaciony;
    }

    /**
     *
     * @param ubicaciony
     */
    public void setUbicaciony(double ubicaciony) {
        this.ubicaciony = ubicaciony;
    }

    /**
     *
     * @return
     */
    public double getBateria() {
        return bateria;
    }

    /**
     *
     * @param bateria
     */
    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    /**
     *
     * @return
     */
    public double getGrados() {
        return grados;
    }

    /**
     *
     * @param grados
     */
    public void setGrados(double grados) {
        this.grados = grados;
    }
    
    /**
     *
     */
    public void resetGrados(){
        this.grados=0;
    }
        
    
    
}
