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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Grace
 */
public class ExplorationData {
    
    private static String archivoExploration = CONSTANTES.ARCHIVOS+"/exploraciones.txt";
    
    public static void escribirExploracion(Crater c) throws IOException{
        try(BufferedWriter bf = 
                new BufferedWriter(new FileWriter(archivoExploration,true))){
            String linea=LocalDate.now().toString()+";"+c.getNombrecrater();//Falta agregar los minerales
            bf.write(linea);
            bf.newLine();
            bf.flush();//para que se escriba inmediatamente en el archivo
            bf.close();
        }
        
}
    public static List<Exploration> obtenerExploracion() throws IOException{
        List<Exploration> exploraciones= new ArrayList<Exploration>();
        try(BufferedReader bf = 
                new BufferedReader(new FileReader(archivoExploration))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] partes = linea.split(";");
                
                exploraciones.add(new Exploration(partes[0],partes[1],"Aluminio,Sodio,Magnesio"));
            }
            return exploraciones;
        }
    }
     private static LocalDate TransformarFecha(String fecha){
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
        
        //Validacion de que la fecha ingresada se encuentre dentro del rango
        if(1>datodia||datodia>31||1>datomes||datomes>12||1900>datoanyo||datoanyo>2026){
            System.out.println("Fecha incorrecta");
            LocalDate fechafin= LocalDate.now();
            return fechafin;
        }
        else{
            LocalDate fechafin=  LocalDate.of(datoanyo,datomes,datodia);
            return fechafin;
        }}
     
     
     
     public static boolean ValidationFecha(String FInicio,String Ffin,Exploration exp){
         LocalDate FechaIni=TransformarFecha(FInicio);
         LocalDate FechaFin=TransformarFecha(Ffin);
         LocalDate fecha=TransformarFecha(exp.getFecha());
         if((fecha.isBefore(FechaFin)||fecha.isEqual(FechaFin))&&(fecha.isAfter(FechaIni)||fecha.isEqual(FechaIni))){
                return true;}
         return false;}
     
     
     public static boolean ValidarMinerales(Exploration exp,String minel){
            String minerales=exp.getMineral();
            String [] parts=minerales.split(",");
            for(int i = 0; i < parts.length; i++){
                if(parts[i].equals(minel)){
                    return true;}}
         return false;}
     
     
     
     
     public static List<Exploration>  FiltradoFecha(String FInicio,String Ffin,List<Exploration> explorations, String mineral){
         List<Exploration>  filtrado=explorations.stream().filter(x->((ValidationFecha(FInicio,Ffin,x))&&(ValidarMinerales(x,mineral)))).collect(Collectors.toList());
         Collections.sort(filtrado, (j1, j2) -> (j1.getNameCrater()).compareTo((j2.getNameCrater())));
        return filtrado;
     }
     
     
     public static void main(String[] args) {
        //Crater c= new Crater("1","Mead",466.51,491.53,33.36);
        //ExplorationData.escribirExploracion(c);
        //System.out.println(ExplorationData.obtenerExploracion());
        List<Exploration> exploraciones = new ArrayList<Exploration>();
        exploraciones.add(new Exploration("12-02-2020", "Mead", "Aluminio,Magnesio,Sodio"));
        exploraciones.add(new Exploration("10-01-2022", "Lucerito", "Argón,Potasio,Sodio"));
        exploraciones.add(new Exploration("10-12-2021", "Astra", "Cobre,Potasio,Plata"));
        System.out.println(FiltradoFecha("10-01-2020","11-01-2022",exploraciones,"Sodio"));    
}
}