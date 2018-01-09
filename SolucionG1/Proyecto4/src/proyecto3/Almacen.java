/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pedro
 */
public class Almacen {

    private int capacidad;
    private int fruta;
    ReentrantLock mutex = new ReentrantLock(); //Semaphore principal
    Condition hayfruta = mutex.newCondition(); //Condiciones ya que todos utilizan el mismo almacen
    Condition hayhueco = mutex.newCondition();

    public Almacen(int _capacidad, int _fruta) {
        capacidad = _capacidad;
        fruta = _fruta;
    }

    public void Deposita(int id, int cuanto) {
        try {
            mutex.lock(); //no permite que entre si hay otro con el lock bloqueado
            while (capacidad - fruta < cuanto) { // |——————————————| cantidad
                hayhueco.await();                //       |————————| fruta 
            }.                                   //    |———————————|cuanto
            fruta = fruta + cuanto;
            System.out.println("CONTENIDO: " + fruta);
            hayfruta.signalAll();
        } catch (InterruptedException ex) {
            System.out.println(id + "No ha podido entregar la fruta");
            System.exit(0);
        } finally {
            mutex.unlock();  //desbloquea el mutex
        }
    }

    public void Compra(int id, int cuanto) {
        try {
            int intentos = 0;
            mutex.lock();
            while (fruta < cuanto && intentos < 2) {//Los intentos son para que no se quede de manera indefinida cargado el hilo
                hayfruta.await();
                intentos++;
            }
            if (intentos < 2) {
                fruta = fruta - cuanto;
                System.out.println("CONTENIDO: " + fruta);
                hayhueco.signalAll();
            }
            else{
                System.out.println(id+" se marcha ABURRIDO"); //Cuando los intentos superan o son 2
            }
        } catch (InterruptedException ex) {
            System.out.println(id + "No ha podido comprar la fruta");
            System.exit(0);
        } finally {
            mutex.unlock(); //Con finally siempre se ejecuta por si el try salta al catch para que no termine directamente
        }

    }
}
