package GeneradorEjercicio4;

public class Principal {

	public static void main(String[] args) {
		String ruta = "ping" + " google.es";

		GeneradorProceso lanzador = new GeneradorProceso();
		lanzador.ejecutar(ruta);
		System.out.println("Proceso Ejecutado!!!");
	}

}
