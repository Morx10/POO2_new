/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Objetos.Crater;
import Objetos.Exploration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 **Clase  que representa los métodos de las diferentes exploraciones 
 * @author Grupo#1 Paralelo#3 POO
 */
public class ExplorationData {
    
    private static final String archivoExploration = CONSTANTES.ARCHIVOS+"/exploraciones.txt";
    
    /**
     * Agrega una exploración al archivo de exploraciones del sistema
     * @param c : cráter que se sensó el rover
     */
    public static void escribirExploracion(Crater c) throws IOException{
        try(BufferedWriter bf = 
                new BufferedWriter(new FileWriter((archivoExploration),true))){
            List<String> minerales =c.getMinerales();
            String str = "";
          
            for (String min : minerales) {
			str+= min+",";}
            String linea=LocalDate.now().toString()+";"+c.getNombrecrater()+";"+str;
            bf.write(linea.substring(0, linea.length()-1));
            bf.newLine();
            //para que se escriba inmediatamente en el archivo   
        }
}
     /**
     * Retorna una lista de exploraciones que han sido escritos en archivoExploration.txt por
     * el rover al momento de sensar 
     * @return exploraciones lista de las diferentes exploraciones
     */
    public static List<Exploration> obtenerExploracion(){
        List<Exploration> exploraciones= new ArrayList<>();
        try(BufferedReader bf = 
                new BufferedReader(new FileReader(archivoExploration))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] partes = linea.split(";");
                
                exploraciones.add(new Exploration(partes[0],partes[1],partes[2]));
            } 
            }catch (Exception ex) {
             Alert a=new Alert(Alert.AlertType.INFORMATION);
             a.setContentText("No hay exploraciones que mostrar");
             a.show();
        }
            return exploraciones;
        
    }
    
    /**
     * Método estático para transformar una fecha que ingresa como String a LocalDate
     * @param fecha String cadena de caracteres que representa la fecha
     * @return LocalDate fecha convertida en LocalDate
     */
     public static LocalDate TransformarFecha(String fecha){
        //LocalDate fechafin=LocalDate.now()
           LocalDate fechafin = LocalDate.parse(fecha); 
        
     return fechafin;}
     
     /**
     * Método estático para validar que se ingrese una fecha válida en un rango determinado
     * @param Inicio String cadena de caracteres que representa la fecha de Inicio
     *  @param fin String cadena de caracteres que representa la fecha de fin
     * @param exp Exploration objeto del cual se obtiene la fecha que debe estar entre el rango definido
     * @return Boolean valor de verdad si la fecha se encuentra dentro del rango 
     */
     
    public static boolean ValidationFecha(String Inicio,String fin,Exploration exp){
        boolean valor=false;
        System.out.println("entre");
        LocalDate FInicio=TransformarFecha(Inicio);
        LocalDate Ffin=TransformarFecha(fin);
        LocalDate fecha=TransformarFecha(exp.getFecha());
        valor= (fecha.isBefore( Ffin)||fecha.isEqual( Ffin))&&(fecha.isAfter(FInicio)||fecha.isEqual(FInicio))&&(FInicio.isBefore( Ffin));  
         return valor;}
     /**
     * Método estático para validar que el mineral ingresado se encuntre entre los minerales de los cráteres sensados
     * @param exp Exploration exploración de donde se verfica los minerales
     *  @param minel String cadena de minerales que se comprueba con los minerales de la exploración
     * @return Boolean valor de verdad si el mineral si se encuentra entre los minerales de la exploración 
     */
    public static boolean ValidarMinerales(Exploration exp,String minel)throws StringIndexOutOfBoundsException{
        
            String minerales=exp.getMineral();
            String [] parts=minerales.split(",");
             for (String part : parts) {
                 if (part.equals(minel)) {
                     return true;}
           }
        
         return false;}
      /**
     * Méotdo estático para validar que se ingrese una fecha válida
     * @param fecha Strng cadena de caracteres que representa la fecha
     * @return Boolean valor de verdad si la fecha se encuentra en el formato correcto 
     */
    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } 
          catch (ParseException e) {
              Alert a=new Alert(Alert.AlertType.WARNING);
              a.setContentText("Formato de Fecha no válido");
              a.show();
            return false;}
        return true;
    }
     
    /**
     * Retorna una lista de exploraciones que han sido filtrada por un rango de fecha y un mineral a buscar
     * @param FInicio String fecha de inicio del rango
     * @param Ffin String fecha fin del rango
     * @param explorations List<Exploration> lista que se filtrata de acuerdo a las condiciones
     * @param mineral String mineral que servira de condición para el filtrado
     * @return filtrado lista de las diferentes exploraciones filtradas
     */
     public static List<Exploration>  FiltradoFecha(String FInicio,String Ffin,List<Exploration> explorations, String mineral){
         
         List<Exploration>  filtrado= new ArrayList<>();
         
         if((validarFecha(FInicio))&&(validarFecha(Ffin))){
             filtrado=explorations.stream().filter(x->((ValidationFecha(FInicio,Ffin,x))&&(ValidarMinerales(x,mineral)))).collect(Collectors.toList()); 
         }         
   
         Collections.sort(filtrado, (j1, j2) -> (j1.getNameCrater()).compareTo((j2.getNameCrater())));
         
        return filtrado;}
     
     
     public static void main(String[] args) {
        /*List<Exploration> exploraciones = new ArrayList<>();
        exploraciones.add(new Exploration("2020-01-10", "Mead", "Aluminio,Magnesio,Sodio"));
        exploraciones.add(new Exploration("2022-01-10", "Lucerito", "Argón,Potasio,Sodio"));
        exploraciones.add(new Exploration("2021-10-12", "Astra", "Cobre,Potasio,Plata"));
        LocalDate fi=TransformarFecha("2020-01-10");
        LocalDate ff=TransformarFecha("2021-10-12");
        boolean valor=validarFecha("2020-01-10");
        Exploration exp=new Exploration("2021-10-12","Astra","Cobre,Potasio,Plata");
        
        System.out.println(fi);
        System.out.println(ff);
      
        System.out.println(FiltradoFecha(fi,ff,exploraciones,"Aluminio"));*/ 
  
}}
