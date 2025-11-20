package EjercicioEjemplo;

import java.io.IOException;

public class LanzaHilos {	
	public static int MAXTIEMPO =250; 
		public static void main(String[] args) {
			Thread h1 = new Thread(new hilo("Hilo1"));
			Thread h2 = new Thread(new hilo("Hilo2"));
			Thread h3 = new Thread(new hilo("Hilo3"));
			h1.start();
			h2.start();
			h3.start();
				try {
					h1.join();
					h2.join();
					h3.join();
				}catch (InterruptedException e) {
					System.out.println("Se he interrumpido");
					e.getMessage();

				}
				System.out.println("Hilo principal terminado");

			
		}
		static void join (long ms) {
			
			
		}
	

	}


