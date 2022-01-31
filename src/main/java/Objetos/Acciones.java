/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Objetos;

/**
 * Ejecuta las acciones del rover
 * @author Grupo#1 Paralelo#3 POO
 */
public interface Acciones {
    /**
     * Indica al rover que debe moverse hacia delante
     */  
    public void avanzar();

    /**
     * Indica al rover que debe rotar en sentido de las manecillas del reloj
     * @param grados Cantidad de grados por rotar
     */
    public void girar(double grados);
    
    /**
     * Indica al rover que debe moverse a un punto específico del planeta
     * @param x Coordenada x (longitud) del rover
     * @param y Coordenada y (latitud) del rover
     */
    public void dirigirse( double x, double y);
    
    /**
     * Indica al rover que debe sensar al suelo en su posición actual
     * @param c Crater a ser sensado
     */
    public void sensar(Crater c);
    
    /**
     * Indica al rover que inicie la operación de recarga
     */
    public void cargar();
       
}
