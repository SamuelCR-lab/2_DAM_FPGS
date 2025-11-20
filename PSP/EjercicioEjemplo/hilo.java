package EjercicioEjemplo;

import java.util.Random;

public class hilo implements Runnable{
	
	private final String nombre;
	hilo(String nombre) {
		this.nombre = nombre;
	}
	
	
	public void run () {
		System.out.println("Hola, soy un hilo: "+nombre);
			for(int i  = 0 ;i<5;i++) {
				Random r = new Random();
				int pausa = 20 + r.nextInt(500-20);
				System.out.println(""+nombre+" hace pausa "+pausa+"ms. ");
				try {
					Thread.sleep(pausa);
				}catch (InterruptedException e){
					System.out.println(""+nombre+"se ha interrumpido.");
				}
			}
		
		System.out.println("Hilo terminado. "+nombre);
	}
	
}	
	
	
	
	
	


