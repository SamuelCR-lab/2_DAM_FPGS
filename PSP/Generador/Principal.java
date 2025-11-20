package Generador;

public class Principal {

	public static void main(String[] args) {
		String ruta = "ipconfig";
		GeneradorProceso lanzador = new GeneradorProceso();
		lanzador.ejecutar(ruta);
		System.out.println("Proceso Ejecutado!!!");
	}

}
