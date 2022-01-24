/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Objetos.Crater;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marit
 */
public class CraterData {
    public static String ruta=CONSTANTES.ARCHIVOS+"/crateres_info.txt";
    public static String ruta2=CONSTANTES.ARCHIVOS+"/exploraciones.txt";
    
    public static List<Crater> cargarCrateres(){
        List<Crater> crateres = new ArrayList<>();
        try( BufferedReader bf = 
                new BufferedReader(new FileReader(ruta)) ){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(",");
                Crater crater = new Crater(p[0],p[1],Double.parseDouble(p[2]),Double.parseDouble(p[3]),Double.parseDouble(p[4]));
                crateres.add(crater);
            }         
        }  catch (IOException ex) {
            System.out.println("no se pudo cargar la informacion de los crateres");
            ex.printStackTrace();
        }
        return crateres;
    }
    
    public static String[] cargarMinerales(String crater){
        String[] minerales = null;
        try( BufferedReader bf = 
                new BufferedReader(new FileReader(ruta2)) ){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(";");
                
                if(p[1].equals(crater)){
                    minerales = p[2].split(",");
                }
            }         
        }  catch (Exception ex) {
            System.out.println("No hay informacion");
            ex.printStackTrace();
        }
        return minerales;
    }
    
    
    public static List<String> crateresSensados(){
        List<String> crateres = new ArrayList<>();
        try( BufferedReader bf = 
                new BufferedReader(new FileReader(ruta2)) ){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(";");               
                crateres.add(p[1]);                      
            }         
        }  catch (Exception ex) {
            System.out.println("No hay informacion");
            ex.printStackTrace();
        }
        return crateres;
    }
    
    public static void main(String[] args){
        System.out.println(cargarCrateres());
    }
}
