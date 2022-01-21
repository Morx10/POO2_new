/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author marit
 */
public abstract class Rovers implements Acciones {
    
    public String nombreRover;
    private double ubicacionx;
    private double ubicaciony;
    private int bateria;

    public Rovers(String nombreRover, double ubicacionx, double ubicaciony) {
        this.nombreRover = nombreRover;
        this.ubicacionx = ubicacionx;
        this.ubicaciony = ubicaciony;
        this.bateria = 100;
    }      
    

    @Override
    public void avanzar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void girar(double girar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dirigirse(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String sensar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
    
}
