/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.util.List;
import java.util.stream.Stream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Grace
 */
public class Exploration {
    private String fecha;
    private String crater_name;
    private String mineral;
    public Exploration( String fecha, String crater_name,String mineral) {
        this.fecha = fecha;
        this.crater_name = crater_name;
        this.mineral =mineral;
    }
     public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha=fecha;
    }

    public String getNameCrater() {
        return crater_name;
    }

    public void setNameCrater(String crater_name) {
        this.crater_name=crater_name;
    }

    public String getMineral() {
        return mineral;
    }

    public void setContenido(String mineral) {
        this.mineral=mineral;
    }
        @Override
    public String toString() {
       return crater_name;}
   

    
}
