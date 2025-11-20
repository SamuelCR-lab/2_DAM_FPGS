package JuegoPINGPONG;

import java.util.concurrent.Semaphore;

public class JugadorPINGPONG extends Thread{
	
	public void run() {
				try {
					for(; ;) {
					PING_PONG.SEM1.acquire();
					System.out.print("PING...");
					PING_PONG.SEM2.release();
						while (true) {
								PING_PONG.SEM2.acquire();
								System.out.println("PONG...");
								PING_PONG.SEM1.release();
						}
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				// TODO Auto-generated catch block
			
		
	}
	
}
