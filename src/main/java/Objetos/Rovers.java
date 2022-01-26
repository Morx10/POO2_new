/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Data.CONSTANTES;
import Data.ExplorationData;
import Data.RoversData;
import espol.proyectopoo2.VistaExplorar2_0Controller;
import java.util.List;

/**
 *
 * @author marit
 */
public abstract class Rovers implements Acciones {
    
    public String nombreRover;
    private double ubicacionx;
    private double ubicaciony;
    private double bateria;
    private double grados;

    public Rovers(String nombreRover, double ubicacionx, double ubicaciony, double grados) {
        this.nombreRover = nombreRover;
        this.ubicacionx = ubicacionx;
        this.ubicaciony = ubicaciony;
        this.bateria = 100;
        this.grados = grados;
    }      
    

    @Override
    public void avanzar() {
        bateria=bateria-1;
        double ubicacionX =getUbicacionx();
        double ubicacionY =getUbicaciony();
        
        setUbicacionx(Math.cos(Math.toRadians(grados))*(10*(Math.pow(2,1/2)))+ubicacionx);
        setUbicaciony(Math.sin(Math.toRadians(grados))*(10*(Math.pow(2,1/2)))+ubicaciony);
    
        VistaExplorar2_0Controller.moverobjeto(ubicacionX, ubicacionY);
        
        RoversData.escribirRover();
        
        
                
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
        bateria= bateria - (hypot/20 );
        
        double ubicacionX= ubicacionx;
        double ubicacionY= ubicacionx;
        
        ubicacionx=x;
        ubicaciony=y;
     
        VistaExplorar2_0Controller.moverobjeto(ubicacionX, ubicacionY);
        RoversData.escribirRover();
    }

    @Override
    public String sensar() {
        
        List<Crater> crateres= VistaExplorar2_0Controller.getCrateres();
        try{
        for(Crater c:crateres){
            if(((ubicacionx>=(c.getLongitud()-c.isRadiocrater()))|| 
                    (ubicacionx<=(c.getLongitud()+c.isRadiocrater())))&&
                    ((ubicaciony>=(c.getLatitud()-c.isRadiocrater()))||
                    (ubicaciony<=(c.getLatitud()+c.isRadiocrater())))){                

                List<String> minerales= CONSTANTES.minerales;
                int numero = (int)(Math.random()*10+1);
                
                for(int i=0;i<=numero;i++){
                    int index = (int)(Math.random() * minerales.size());
                    System.out.println(index);
                    
                    String mineral= minerales.get(index);
                    c.aniadirMinerales(mineral);
                
                }
                ExplorationData.escribirExploracion(c);
                


            
        }
        }
        }catch(Exception ex){
            System.out.println("no hay");
        }
        
        
        
        
        return "hola"; 
    }

    @Override
    public abstract void cargar();
    
    @Override
    public String toString() {
        return "Rover=" + nombreRover ;
    }

    public String getNombreRover() {
        return nombreRover;
    }

    public void setNombreRover(String nombreRover) {
        this.nombreRover = nombreRover;
    }

    public double getUbicacionx() {
        return ubicacionx;
    }

    public void setUbicacionx(double ubicacionx) {
        this.ubicacionx = ubicacionx;
    }

    public double getUbicaciony() {
        return ubicaciony;
    }

    public void setUbicaciony(double ubicaciony) {
        this.ubicaciony = ubicaciony;
    }

    public double getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public double getGrados() {
        return grados;
    }

    public void setGrados(double grados) {
        this.grados = grados;
    }
    
}
