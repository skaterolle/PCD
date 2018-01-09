/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Almacen {

    private int capacidad;
    private int fruta;

    public Almacen(int _capacidad, int _fruta) {
        capacidad = _capacidad; // capacidad total del almacen
        fruta = _fruta;	// Fruta que hay en estos momentos en el almacen, no puede ser superior a la capacidad
    }

    public synchronized void Deposita(int id, int cuanto) {
        try {
            while (capacidad - fruta < cuanto) {
                wait(); //Como no tenemos mutex se hacen esperas de los propios hilos
            }
            fruta = fruta + cuanto;
            System.out.println("CONTENIDO: " + fruta);
            notifyAll(); //Despertamos a todos los hilos que estan esperando
        } catch (InterruptedException ex) {
            System.out.println(id + "No ha podido entregar la fruta");
            System.exit(0); //salimos directamente y cierra el hilo
        }
    }

    public synchronized void Compra(int id, int cuanto) {
        try {
            while (fruta < cuanto) { //Si la cantidad pedida es superior a la contenida se espera
                wait();
            }
            fruta = fruta - cuanto;
            System.out.println("CONTENIDO: " + fruta);
            notifyAll();
        } catch (InterruptedException ex) {
            System.out.println(id + "No ha podido comprar la fruta");
            System.exit(0);
        }

    }
}
