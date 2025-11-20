package Generador2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorProcesos {
	public void ejecutar(String ruta, String nombre) {
		
		List <String> nombreArgumentos = new ArrayList<>();
		File directorio = new File(ruta);
	
		
		ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);
		pb.command(nombre);
		/*llamada inheritIO()
		 * Hace que el proceso hereda la E/S standar del proceso padre
		 * Asi podemos ver el resultado del comando
		 * */
		pb.directory(directorio);
		try {
			pb.start();
			}catch(IOException e){
	
				e.printStackTrace();
		}
		
	}
	
}
