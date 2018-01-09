/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author pedro
 */
public class Proveedor implements Runnable {

    private int id;
    private Semaphore fruta; //Lo inicializamos con el del generador

    public Proveedor(int i, Semaphore fruta) {
        id = i;
        this.fruta = fruta;
    }

    @Override
    public void run() {
        Random rnd = new Random();   // Toma el tiempo que lleva ejecutado el programa
        rnd.setSeed(System.currentTimeMillis() + id); //Toma el tiempo del pc en ms
        int cantidad = rnd.nextInt(4) + 2;
        System.out.println(" .......................... Soy el Proveedor " + id + " y pongo " + cantidad);
        for (int i = 0; i < cantidad; i++) {
            fruta.release(); // Lo soltamos ya que estaba cogido del cliente
        }
        System.out.println("........................... Soy el Proveedor " + id + " marchandome");
    }
}
