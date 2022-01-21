/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos;

/**
 *
 * @author marit
 */
public class HiloSencillo extends Thread {

    @Override
    public void run() {
        try {
            super.wait(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
