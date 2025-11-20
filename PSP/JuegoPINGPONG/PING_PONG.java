package JuegoPINGPONG;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class PING_PONG {
	public final static Semaphore SEM1 = new Semaphore(1);
	public final static Semaphore SEM2 = new Semaphore(0);
	public static int NumHilo= 10;
	
	public static void main(String[] args) {
		JugadorPINGPONG jPING = new JugadorPINGPONG();
		JugadorPINGPONG jPONG = new JugadorPINGPONG();
		JugadorPINGPONG jPING1 = new JugadorPINGPONG();
		JugadorPINGPONG jPONG1 = new JugadorPINGPONG();
			try {
				jPING.start();
				jPONG.start();
				jPING1.start();
				jPONG1.start();
				
				jPING.join();
				jPONG.join();
				jPING1.join();
				jPONG1.join();
			}catch(InterruptedException i) {
				i.getStackTrace();
			}

	}

}
