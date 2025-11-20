package LanzadorProceso3;

import java.util.ArrayList;
import java.util.List;

public class GeneradorProceso {
	public void ejecutar(String ruta) {
		List <String> nombreArgumentos = new ArrayList<>();
		nombreArgumentos.add("C:/Mycode/Sumar2Numeros.exe");
		nombreArgumentos.add("19");
		nombreArgumentos.add("21");
		
		
		ProcessBuilder pb = new ProcessBuilder(ruta);
		try {
			Process proceso = pb.start();
			
		}catch (Exception e){
			
			e.printStackTrace();
		}
	}
}
