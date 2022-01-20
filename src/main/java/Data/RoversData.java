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
                if(p[3].equals("Solar")){
                    rover = new RoverSolar(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]));
                }else{
                    rover = new RoverEolica(p[0],Double.parseDouble(p[1]),Double.parseDouble(p[2]));
                }               
                rovers.add(rover);
            }         
        }  catch (IOException ex) {
            System.out.println("no se pudo cargar la informacion de los rovers");
            ex.printStackTrace();
        }
        return rovers;
    }
    
    public static void main(String[] args){
        System.out.println(cargarRovers());
    }
    
}
