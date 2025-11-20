
package EjercicioExclusionMutua;

import java.io.IOException;
import java.util.ArrayList;

public class LanzaHilos {	
	public static final int NUM_ZONAS =10; 
	public static final int CUENTA_TOTAL =3000; 
		public static void main(String[] args) {
			ContadorZonas contCompartido = new ContadorZonas();
			Thread [] hilos = new Thread[NUM_ZONAS];
			for (int i = 0;i<10;i++) {
				Thread th = new Thread(new hilo(i,CUENTA_TOTAL/NUM_ZONAS,contCompartido));
				th.start();
				hilos[i]=th;
				
			}
			for (Thread h: hilos) {
				try {
					h.join();
				}catch (InterruptedException e) {
					System.out.println("Excepcion por interrumpido");
					e.getMessage();

				}
			}
			/*
			ArrayList <Thread> hilos = new ArrayList<Thread>();
			for (int i = 1;i<11;i++) {
				hilos.add(new Thread(new hilo("Hilo"+i,0))) ;
			}
			*/
			
				
				System.out.println("Ciudanos totales: "+contCompartido.getCuenta()+"\nPP: "+contCompartido.getCuentaPP()
				+"\nPSOE: "+contCompartido.getCuentaPSOE()+"\nCIUDADANOS: "+contCompartido.getCuentaCIU()+"\nNS/NC: "+contCompartido.getCuentaNSNC());

			
		}

	

	}


