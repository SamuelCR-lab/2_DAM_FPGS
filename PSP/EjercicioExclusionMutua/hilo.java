package EjercicioExclusionMutua;

import java.time.Duration;
import java.util.Random;

public class hilo implements Runnable{
	private final ContadorZonas contCompartido;

	 int contador, vueltas,miCuenta=0,cuentaPP,cuentaPSOE,cuentaCIU,cuentaNSNC;
	hilo(int contador,int vueltas, ContadorZonas c) {

		this.contador = contador;
		this.vueltas= vueltas;
		this.contCompartido = c;
	}
	public int getMiCuenta() {
		return miCuenta;
	}
	public int getCuentaPP() {
		return cuentaPP;
	}
	public int getCuentaPSOE() {
		return cuentaPSOE;
	}
	public int getCuentaCIU() {
		return cuentaCIU;
	}
	public int getCuentaNSNC() {
		return cuentaNSNC;
	}
	
	public void run () {
		for(int i = 0; i<vueltas ;i++) {
			int numAleatorio = (int)(Math.random() * 4); // 0 al 3
			switch(numAleatorio) {
			case 0:
				contCompartido.incrementaPP();
				cuentaPP++;
				contCompartido.incrementa();
				break;
			case 1:
				contCompartido.incrementaPSOE();
				cuentaPSOE++;
				contCompartido.incrementa();
				break;
			case 2:
				contCompartido.incrementaCIU();
				cuentaCIU++;
				contCompartido.incrementa();
				break;
			case 3:
				contCompartido.incrementaNSNC();
				cuentaNSNC++;
				contCompartido.incrementa();
				break;
					
			}
			
		}
		System.out.println("Zona: "+contador+" ha terminado, Cuenta PP: "+getCuentaPP()+
				", Cuenta PSOE: "+getCuentaPSOE()+", Cuenta Ciudadanos: "+getCuentaCIU()+
				", Cuenta NSNC: "+getCuentaNSNC());
				
			
			} 
	
}
	
	
	
	
	


