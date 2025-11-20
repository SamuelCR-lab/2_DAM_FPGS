package GeneradorEjercicio3;

import java.io.IOException;
import java.util.List;

public class Comando {
	public void comando(List<String> nombreArgumentos) {
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(nombreArgumentos);
		/*llamada inheritIO()
		 * Hace que el proceso hereda la E/S standar del proceso padre
		 * Asi podemos ver el resultado del comando
		 * */
		pb.inheritIO();
		try {
			Process proceso = pb.start();
			int codigoRetorno = proceso.waitFor();
			System.out.println("$$$$$$$$$$$$$$$$$$$$");
			System.out.println("El comando devuelve: "+codigoRetorno);
			System.out.println("$$$$$$$$$$$$$$$$$$$$");
		
				if (codigoRetorno == 0) {
					System.out.println("Ejecucion correcta");
				}else {
					System.out.println("Ejecucion con errores");
				}
			}catch(IOException e){
				System.out.println("Error durante la ejecucion del comando");
				System.out.println("Informacion Adicional: ");
				e.printStackTrace();
				System.exit(2);
		}
		catch (InterruptedException e) {
			System.err.println("Proceso Interrumpido");
		}
	}
}
