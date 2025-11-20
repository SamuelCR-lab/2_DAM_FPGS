package GeneradorEjercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneradorProceso extends Comando{
	public void ejecutar(String ruta) {
		Runtime rt = Runtime.getRuntime();//Runtime ya es un proceso en si esta mas optimizado, pero el Process builder es mas completo para poder hacer más cosas
		Process proceso = null;
		String line;
		
		try {
			proceso = rt.exec(ruta);
			BufferedReader br = new BufferedReader(new InputStreamReader (proceso.getInputStream()));
			/**
			 * El bufferedReader es un canal de comunicacion que tiene el 
			 * proceso hijo con el inputStreamReader y vamos a poder acceder a el desde el br del proceso padre.
			 * Este proceso de comunicacion del br (BufferedReader) es lo que tendriamos con el (pb.inheritIO(); DE programas anteriores) dandonos acceso al proceso hijo,
			 *  sin tener que que heredarlo 
			 * 
			 * */ 
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			
		}catch(IOException e) {
			e.getStackTrace();
			System.exit(-1);
		}
		if(proceso != null){
			proceso.destroy();
		}
		try {
			proceso.waitFor();
			
		}catch(InterruptedException a) {
			System.exit(-2);
		}
		System.exit(0);
	}
}
