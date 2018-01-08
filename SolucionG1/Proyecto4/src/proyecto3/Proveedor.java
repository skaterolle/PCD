/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class Proveedor implements Runnable{
    private int id;
    Almacen alm;
    
    public Proveedor(int i, Almacen al){
        id=i;
        alm=al;
    }
    @Override
    public void run(){
            Random rnd = new Random();  // new Random(System.nanoTime()+id)
            rnd.setSeed(System.currentTimeMillis()+id);  // esto no se necesita con lo de arriba
            int cantidad=rnd.nextInt(4)+2;
            System.out.println(" .......................... Soy el Proveedor "+id+" y pongo "+ cantidad);
            alm.Deposita(id,cantidad);  //llama a deposita de almacen, la id es solo para el sout
            System.out.println("........................... Soy el Proveedor "+id+" marchandome");
    }

}
