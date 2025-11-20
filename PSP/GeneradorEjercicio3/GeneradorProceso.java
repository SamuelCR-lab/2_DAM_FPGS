package GeneradorEjercicio3;



import java.util.ArrayList;
import java.util.List;

public class GeneradorProceso extends Comando{
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
		
		comando(nombreArgumentos);
	}
	
}
