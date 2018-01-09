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
public class Cliente extends Thread{
    private int id;
    private Almacen alm;
    
    public Cliente(int i, Almacen al){
        id=i;
        alm=al; //cada hilo accede al mismo almacen ya que si no fuese asi el almacen siempre estaria vacio
    }
    @Override
    public void run(){
  
            Random rnd = new Random();
            rnd.setSeed(System.currentTimeMillis()+id);
            int cantidad=rnd.nextInt(4)+2;
            System.out.println("Soy el cliente "+id+" y quiero "+ cantidad);
            alm.Compra(id,cantidad);
            System.out.println("Soy el cliente "+id+" marchandome con la fruta");
            
       
    }
    
}
