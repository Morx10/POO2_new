/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;


/**
 *Clase  que representa los datos y métodos de las diferentes exploraciones
 * @author Grupo#1 Paralelo#3 POO
 */
public class Exploration {
    private  String fecha;
    private String nameCrater;
    private String mineral;
    
    /**
     * Constructor de la Exploración
     * @param fecha String fecha que se realizó la exploración
     * @param crater_name String nombre del cráter que se exploró
     * @param mineral String minerales encontrados en el cráter
     */
    public Exploration( String fecha, String crater_name,String mineral) {
        this.fecha = fecha;
        this.nameCrater = crater_name;
        this.mineral =mineral;
    }
    
    /**
     * Método para obtener la fecha asociada a la exploración
     * @return fecha asociado a la exploración
     */
    
     public String getFecha() {
        return fecha;
    }
    /**
     * Método para modificar la fecha asociada a la exploración
     * @param fecha String fecha asociada a la exploración
     */
    public void setFecha(String fecha) {
        this.fecha=fecha;
    }
    /**
     * Método para obtener la el nombre del cráter asociada a la exploración
     * @return nameCrater asociado a la exploración
     */
    public String getNameCrater() {
        return nameCrater;
    }
    /**
     * Método para modificar el nombre del cráter asociado a la exploración
     * @param crater_name String nombre del cráter asociado a la exploración
     */
    public void setNameCrater(String crater_name) {
        this.nameCrater=crater_name;
    }
    /**
     * Método para obtener los minerales asociada a la exploración
     * @return mineral asociado a la exploración
     */
    public String getMineral() {
        return mineral;
    }
      /**
     * Método para modificar los minerales asociados a la exploración
     * @param mineral String minerales asociados a la exploración
     */

    public void setContenido(String mineral) {
        this.mineral=mineral;
    }
      /**
     * Método que retorna el nombre del cráter del objeto Exploration
     * @return String nombre del cráter de la exploración
     */
      @Override
    public String toString() {
       return nameCrater;}
}
