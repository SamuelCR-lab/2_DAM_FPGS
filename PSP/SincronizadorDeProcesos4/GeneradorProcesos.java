package SincronizadorDeProcesos4;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GeneradorProcesos {
	public static int MAXTIEMPO =1; 
	public void ejecutar(String ruta, String[] args) {
		List <String> nombreArgumentos = new ArrayList<>();
		if (ruta==null || ruta.isEmpty()) {
			System.out.println("Falta el nombre del comando");
			System.exit(1);
		}
		nombreArgumentos.add(ruta);
		for (int i = 0;i < args.length; i++) {
			nombreArgumentos.add(args[i]);
		}
		Process proceso = null;
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(nombreArgumentos);
		/*llamada inheritIO()
		 * Hace que el proceso hereda la E/S standar del proceso padre
		 * Asi podemos ver el resultado del comando
		 * */
		pb.inheritIO();
		try {
			proceso = pb.start();
			
			System.out.println("Se ha lanzado el proceso");
			System.out.println("El proceso padre esperara a que el hijo cierre");
			
			}catch(IOException e){
	
				e.printStackTrace();
		}
		/*try {
			proceso.waitFor(MAXTIEMPO, TimeUnit.SECONDS);
		}catch(InterruptedException a) {
			a.printStackTrace();
		}
		
		//Sacar valor de retorno del proceso
		//Destruir con destroy
		//
		
		try {
			System.out.println(proceso.exitValue());
		}catch(IllegalThreadStateException i) {
			System.out.println(i);
		}
		if(proceso!=null) {
			proceso.destroy();
			System.out.println("El proceso hijo se destruye");
		}
		System.out.println(proceso.isAlive());*/
		
	}
	
}
