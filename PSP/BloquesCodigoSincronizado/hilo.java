package BloquesCodigoSincronizado;

import java.time.Duration;
import java.util.Random;



public class hilo implements Runnable{
	private final CuentaBancaria contCompartido;

	 int contador, vueltas,miCuenta=0;
	hilo(int contador,int vueltas, CuentaBancaria c) {
		this.contador = contador;
		this.vueltas= vueltas;
		this.contCompartido = c;
	}
	public int getNumCuenta() {
		return miCuenta;
	}
	
	
	public void run () {
		for(int i = 0; i<vueltas ;i++) {
			this.contCompartido.incrementar1();//incrementa el contador compartido
			this.contCompartido.incrementar2();
			miCuenta++;
		}
		System.out.println("Hilo: "+contador+" ha terminado, cuenta:"+getNumCuenta());
				
			
			} 
	
}
	
	
	
	
	


