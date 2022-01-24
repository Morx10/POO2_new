/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Objetos.*;
import espol.proyectopoo2.App;
import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 *
 * @author marit
 */
public class RoversData {
    public static String ruta=CONSTANTES.ARCHIVOS+"/rovers-1.txt";
    
    public static List<Rovers> cargarRovers(){
        List<Rovers> rovers = new ArrayList<>();
        try( BufferedReader bf = 
                new BufferedReader(new FileReader(ruta)) ){
            String linea;
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(",");
                Rovers rover;
                if(p[3].equals("solar")){
                    rover = new RoverSolar(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]),Double.parseDouble(p[4]));
                }else{
                    rover = new RoverEolica(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]),Double.parseDouble(p[4]));
                }               
                rovers.add(rover);
            }         
        }  catch (IOException ex) {
            System.out.println("no se pudo cargar la informacion de los rovers");
            ex.printStackTrace();
        }
        return rovers;
    }
    
    public static void escribirRover(){   
        try(BufferedWriter write 
                = new BufferedWriter(new FileWriter(ruta));){
           
       
       List<Rovers> rovers= App.getRovers();
       
       
       for(Rovers r: rovers){
           String nombre= r.getNombreRover();
           double ubicacionx= r.getUbicacionx();
           double ubicaciony= r.getUbicaciony();
           double grados= r.getGrados();
           String tipo;
           
           if(r instanceof RoverEolica){
               tipo="eolico";
           }else{
               tipo="solar";
           }
           
           
           String line= nombre+","+ubicacionx+","+ubicaciony+","+tipo+","+grados;
           write.write(line);
           write.newLine();
       }
       /// escribir una linea en el reporte con el nombre del pais y el numero. de envios
       
       System.out.println("Reporte generado");
       } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static void main(String[] args){
        System.out.println(cargarRovers());
    }
    
}
