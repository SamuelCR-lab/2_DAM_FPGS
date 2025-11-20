
package BloquesCodigoSincronizado;

import java.io.IOException;
import java.util.ArrayList;

public class TransferConClock {	
	public static final int NUM_HILOS =10; 
	public static final int CUENTA_TOTAL =100000000; 
		public static void main(String[] args) {
			CuentaBancaria c1 = new CuentaBancaria("ES123456789012345", 12345);
			CuentaBancaria c2 = new CuentaBancaria("ES123456789067890", 56789);
			System.out.println("Saldo inicial de "+c1.getContador1()+" en la cuenta"+c1.getContador1());
			System.out.println("Saldo inicial de "+c2.getContador2()+" en la cuenta"+c2.getContador2());
			System.out.println("------------------------------------------------------");
			
			Thread h1 = new Thread(new hilo (c1,c2,"H1"));
			Thread h2 = new Thread(new hilo (c2,c1,"H2"));
			h1.start();
			h1.start();
				try {
					h1.join();
					h2.join();
					
				}catch (InterruptedException e) {
					System.out.println("Excepcion por interrumpido");
					e.getMessage();

				}
			/*
			ArrayList <Thread> hilos = new ArrayList<Thread>();
			for (int i = 1;i<11;i++) {
				hilos.add(new Thread(new hilo("Hilo"+i,0))) ;
			}
			*/
				System.out.println("------------------------------------");
				System.out.println("cuenta global: contador 1 = "+c1.getContador1()
									+"\n contador 2 ="+c2.getContador2());
			

			
		}

	

	}


