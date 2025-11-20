package SincronizadorDeProcesos3;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GeneradorProcesos {
	public void ejecutar(String ruta) {
		
		//List <String> nombreArgumentos = new ArrayList<>();
		//File directorio = new File(ruta);
		Process proceso = null;
		
		ProcessBuilder pb = new ProcessBuilder(ruta);
		//pb.command(nombre);
		/*llamada inheritIO()
		 * Hace que el proceso hereda la E/S standar del proceso padre
		 * Asi podemos ver el resultado del comando
		 * */
		//pb.directory(directorio);
		try {
			proceso = pb.start();
			System.out.println("Se ha lanzado el proceso");
			System.out.println("El proceso padre esperara a que el hijo cierre");
			}catch(IOException e){
	
				e.printStackTrace();
		}
		try {
			proceso.waitFor();
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
		System.out.println(proceso.isAlive());
	}
	
}
