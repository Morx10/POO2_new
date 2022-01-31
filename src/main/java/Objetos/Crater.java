/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 * Informacion del crater a ser explorado
 * @author Grupo#1 Paralelo#3 POO
 */
public class Crater {
    private String idcrater;
    private String nombrecrater;
    private double latitud;
    private double longitud;
    private double radiocrater;
    private ArrayList<String> minerales;
    
    /**
     * Constructor del crater
     * @param idcrater Codigo del crater
     * @param nombrecrater Nombre del crater
     * @param longitud Coordenada x (longitud) del crater
     * @param latitud Coordenada y (latitud) del crater
     * @param radiocrater Radio del crater
     */
    public Crater(String idcrater, String nombrecrater, double longitud, double latitud, double radiocrater) {
        this.idcrater = idcrater;
        this.nombrecrater = nombrecrater;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radiocrater = radiocrater;
        this.minerales=new ArrayList<String>();
    }
    
    /**
     * Actualiza la lista de minerales
     * @param minerales Lista de minerales
     */
    public void setMinerales(ArrayList<String> minerales) {  
        this.minerales=minerales;      
    }
    
    /**
     * Obtiene el codigo del crater
     * @return idcrater Codigo del crater
     */
    public String getIdcrater() {
        return idcrater;
    }
    
    /**
     * Actualiza el codigo del crater
     * @param idcrater Codigo del crater
     */
    public void setIdcrater(String idcrater) {
        this.idcrater = idcrater;
    }
    
    /**
     * Obtiene el nombre del crater
     * @return nombrecrater Nombre del crater
     */
    public String getNombrecrater() {
        return nombrecrater;
    }
    
    /**
     * Obtiene la latitud del crater
     * @return latitud Coordenada y del crater
     */
    public double getLatitud() {
        return latitud;
    }
    
    /**
     * Obtiene la longitud del crater
     * @return longitud Coordenada x del crater
     */
    public double getLongitud(){
        return longitud;
    }
    
    /**
     * Actualiza el nombre del crater
     * @param nombrecrater nombre del crater
     */
    public void setNombrecrater(String nombrecrater) {
        this.nombrecrater = nombrecrater;
    }
    
    /**
     * Actualiza la latitud del cráter
     * @param latitud Nueva latitud del cráter
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    
    /**
     * Actualiza la longitud del cráter
     * @param longitud Longitud del cráter
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    /**
     * Devuelve el radio del cráter
     * @return radiocrater Radio del cráter
     */
    public double isRadiocrater() {
        return radiocrater;
    }
    
    /**
     * Actualiza el radio del crater
     * @param radiocrater Nuevo radio del cráter
     */
    public void setRadiocrater(double radiocrater) {
        this.radiocrater = radiocrater;
    }
    
    /**
     * Verifica si dos crateres tienen el mismo nombre
     * @param obj Objeto a ser verificado
     * @return Valor de verdad de la igualdad
     */
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
    
    /**
     * Obtiene la lista de minerales del cráter
     * @return minerales Lista de minerales
     */
    public List<String> getMinerales() {
        return minerales;
    }
    
    /**
     * Agrega minerales a la lista
     * @param minerales Lista de minerales
     */
    public void aniadirMinerales(ArrayList<String> minerales) {  
        this.minerales.addAll(minerales);      
    }
    
    
    
    
}
