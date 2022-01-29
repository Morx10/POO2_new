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
    
    private static String archivoExploration = CONSTANTES.ARCHIVOS+"/exploraciones.txt";
    
    public static void escribirExploracion(Crater c) throws IOException{
        try(BufferedWriter bf = 
                new BufferedWriter(new FileWriter(archivoExploration))){
            List<String> minerales =c.getMinerales();
            String str = "";
            for (String min : minerales) {
			str+= min+",";}
            String linea=LocalDate.now().toString()+";"+c.getNombrecrater()+";"+str;
            bf.write(linea.substring(0, linea.length()-1));
            bf.newLine();
            bf.flush();//para que se escriba inmediatamente en el archivo
            bf.close();
        }
}
    public static List<Exploration> obtenerExploracion(){
        List<Exploration> exploraciones= new ArrayList<Exploration>();
        try(BufferedReader bf = 
                new BufferedReader(new FileReader(archivoExploration))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] partes = linea.split(";");
                
                exploraciones.add(new Exploration(partes[0],partes[1],partes[2]));
            } }catch (Exception ex) {
             ex.printStackTrace();
             Alert a=new Alert(Alert.AlertType.INFORMATION);
             a.setContentText("No hay exploraciones que mostrar");
             a.show();
        }
            return exploraciones;
        
    }
     public static LocalDate TransformarFecha(String fecha){
        LocalDate fechafin=LocalDate.now();
        if(validarFecha(fecha)){
            int posicion =fecha.indexOf("-");
            String dia= fecha.substring(0,posicion);
            fecha= fecha.substring(posicion+1);
            posicion=fecha.indexOf("-");
            String mes=fecha.substring(0,posicion);
            fecha= fecha.substring(posicion+1);
            String anyo=fecha;
            int datodia = Integer.parseInt(dia);
            int datomes = Integer.parseInt(mes);
            int datoanyo = Integer.parseInt(anyo);
            fechafin=  LocalDate.of(datoanyo,datomes,datodia); 
        }
     return fechafin;}
     
     
     
    public static boolean ValidationFecha(String FInicio,String Ffin,Exploration exp){
         LocalDate FechaIni=TransformarFecha(FInicio);
         LocalDate FechaFin=TransformarFecha(Ffin);
         LocalDate fecha=TransformarFecha(exp.getFecha());
         if((fecha.isBefore(FechaFin)||fecha.isEqual(FechaFin))&&(fecha.isAfter(FechaIni)||fecha.isEqual(FechaIni))&&(FechaIni.isBefore(FechaFin))){
                return true;}
         return false;}
     
     
    public static boolean ValidarMinerales(Exploration exp,String minel){
            String minerales=exp.getMineral();
            String [] parts=minerales.split(",");
            for(int i = 0; i < parts.length; i++){
                if(parts[i].equals(minel)){
                    return true;}}
         return false;}
    
    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            Alert a=new Alert(Alert.AlertType.ERROR);
            e.printStackTrace();
            a.setContentText("Ingrese correctamente la fecha");
            a.show();
            return false;
        }
        return true;
    }
     
     
     public static ObservableList<Exploration>  FiltradoFecha(String FInicio,String Ffin,List<Exploration> explorations, String mineral){
         List<Exploration>  filtrado=explorations.stream().filter(x->((ValidationFecha(FInicio,Ffin,x))&&(ValidarMinerales(x,mineral)))).collect(Collectors.toList());
         Collections.sort(filtrado, (j1, j2) -> (j1.getNameCrater()).compareTo((j2.getNameCrater())));
         ObservableList<Exploration>  datos=FXCollections.observableArrayList();
         for(Exploration ex:filtrado){
             datos.add(ex);}
         if(datos.isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("No existe información con datos ingresados");
            a.show();
         }
   
        return datos;
     }
     
     public static void main(String[] args) {
        /**
        List<Exploration> exploraciones = new ArrayList<Exploration>();
        exploraciones.add(new Exploration("12-02-2020", "Mead", "Aluminio,Magnesio,Sodio"));
        exploraciones.add(new Exploration("10-01-2022", "Lucerito", "Argón,Potasio,Sodio"));
        exploraciones.add(new Exploration("10-12-2021", "Astra", "Cobre,Potasio,Plata"));
        System.out.println(FiltradoFecha("10-01-2020","11-10-2021",exploraciones,"Aluminio")); */
  
}
}