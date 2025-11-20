package LanzadorProcesos1;

import java.util.ArrayList;
import java.util.List;

public class GeneradorProceso {
	public void ejecutar(String ruta) {
		List <String> nombreArgumentos = new ArrayList<>();
		nombreArgumentos.add("C:/Mycode/Sumar2Numeros.exe");

		
		
		ProcessBuilder pb = new ProcessBuilder(ruta);
		try {
			Process proceso = pb.start();
			
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}
}
