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
import java.util.List;

/**
 *
 * @author Grace
 */
public class ExplorationData {
    
    static String ruta = "exploraciones.txt";
    
    public static void escribirExploracion(Crater c) throws IOException{
        try(BufferedWriter bf = 
                new BufferedWriter(new FileWriter(ruta,true))){
            String linea=LocalDate.now().toString()+","+c.getNombrecrater();//Falta agregar los minerales
            bf.write(linea);
            bf.newLine();
            bf.flush();//para que se escriba inmediatamente en el archivo
            bf.close();
        }
        
}
    public static List<Exploration> obtenerExploracion() throws IOException{
        List<Exploration> exploraciones= new ArrayList<Exploration>();
        try(BufferedReader bf = 
                new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea=bf.readLine())!=null){
                String[] partes = linea.split(";");
                
                exploraciones.add(new Exploration(partes[0],partes[1],partes[2]));
            }
            return exploraciones;
        }
    }

     public static void main(String[] args){
        
    }
}
