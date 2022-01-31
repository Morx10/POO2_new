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
 *
 * @author Grace
 */
public class ExplorationData {
    
    private static final String archivoExploration = CONSTANTES.ARCHIVOS+"/exploraciones.txt";
    
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
     public static LocalDate TransformarFecha(String fecha){
        LocalDate fechafin=LocalDate.now();
        if(validarFecha(fecha)){
           fechafin = LocalDate.parse(fecha); 
        }
     return fechafin;}
     
     
     
    public static boolean ValidationFecha(LocalDate FInicio,LocalDate Ffin,Exploration exp){
         LocalDate fecha=TransformarFecha(exp.getFecha());
         return (fecha.isBefore( Ffin)||fecha.isEqual( Ffin))&&(fecha.isAfter(FInicio)||fecha.isEqual(FInicio))&&(FInicio.isBefore( Ffin));}
     
     
    public static boolean ValidarMinerales(Exploration exp,String minel){
         while(minel!=null){
            String minerales=exp.getMineral();
            String [] parts=minerales.split(",");
             for (String part : parts) {
                 if (part.equals(minel)) {
                     return true;}
           }} 
         return false;}
    
    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } 
          catch (ParseException e) {
            /**Alert a=new Alert(Alert.AlertType.INFORMATION);
             a.setContentText("Formato de Fecha no válido");
             a.show();*/
            return false;}
        return true;
    }
     
     
     public static List<Exploration>  FiltradoFecha(LocalDate FInicio,LocalDate Ffin,List<Exploration> explorations, String mineral){
         List<Exploration>  filtrado=explorations.stream().filter(x->((ValidationFecha(FInicio,Ffin,x))&&(ValidarMinerales(x,mineral)))).collect(Collectors.toList());
         Collections.sort(filtrado, (j1, j2) -> (j1.getNameCrater()).compareTo((j2.getNameCrater())));
        return filtrado;
     }
     
     public static void main(String[] args) {
       
        List<Exploration> exploraciones = new ArrayList<Exploration>();
        exploraciones.add(new Exploration("2020-01-10", "Mead", "Aluminio,Magnesio,Sodio"));
        exploraciones.add(new Exploration("2022-01-10", "Lucerito", "Argón,Potasio,Sodio"));
        exploraciones.add(new Exploration("2021-10-12", "Astra", "Cobre,Potasio,Plata"));
        LocalDate fi=TransformarFecha("2020-01-10");
        LocalDate ff=TransformarFecha("2021-10-12");
        boolean valor=validarFecha("2020-01-10");
        Exploration exp=new Exploration("2020-01-10", "Mead", "Aluminio,Magnesio,Sodio");
        System.out.println(fi);
        System.out.println(valor);
        System.out.println(ValidationFecha(fi,ff,exp));
        System.out.println(ValidarMinerales(exp,"Magnesio"));
        System.out.println(FiltradoFecha(fi,ff,exploraciones,"Aluminio")); 
  
}
}