package LanzadorProceso2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneradorProceso {
	public void ejecutar(String rutaDirectorio, String nombreEjecutable) {
		List <String> nombreArgumentos = new ArrayList<>();
		File directorio = new File (rutaDirectorio);

		
		
		ProcessBuilder pb = new ProcessBuilder(nombreArgumentos);
		//command nombre del ejecutable
		pb.command(nombreEjecutable);
		// nombre del directorio donde se encuentra
		pb.directory(directorio);
		try {
			//Process proceso = pb.start();
			pb.start();
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}
}
