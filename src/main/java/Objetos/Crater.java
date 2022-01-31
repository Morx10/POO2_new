/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Grupo#1 Paralelo#3 POO
 */
public class Crater {
    private String idcrater;
    private String nombrecrater;
    private double latitud;
    private double longitud;
    private double radiocrater;
    private ArrayList<String> minerales;

    public Crater(String idcrater, String nombrecrater, double longitud, double latitud, double radiocrater) {
        this.idcrater = idcrater;
        this.nombrecrater = nombrecrater;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radiocrater = radiocrater;
        this.minerales=new ArrayList<String>();
    }

    public void setMinerales(ArrayList<String> minerales) {  
        this.minerales=minerales;      
    }

    public String getIdcrater() {
        return idcrater;
    }

    public void setIdcrater(String idcrater) {
        this.idcrater = idcrater;
    }

    public String getNombrecrater() {
        return nombrecrater;
    }

    public double getLatitud() {
        return latitud;
    }
    
    public double getLongitud(){
        return longitud;
    }

    public void setNombrecrater(String nombrecrater) {
        this.nombrecrater = nombrecrater;
    }

    public double isLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double isLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double isRadiocrater() {
        return radiocrater;
    }

    public void setRadiocrater(double radiocrater) {
        this.radiocrater = radiocrater;
    }
    
    @Override
    public boolean equals(Object obj){
      if(obj != null){
        if (obj instanceof Crater){
          Crater c = (Crater)obj;
          if(this.nombrecrater.equals(c.nombrecrater))
            return true;
        }
      }
      return false;
    }

    public List<String> getMinerales() {
        return minerales;
    }

    public void aniadirMinerales(ArrayList<String> minerales) {  
        this.minerales.addAll(minerales);      
    }
    
    
    
    
}
