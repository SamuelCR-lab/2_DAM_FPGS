package SincronizadorDeProcesos3;

public class Principal {

	public static void main(String[] args) {
		String ruta = "notepad.exe";
		GeneradorProcesos lanzador = new GeneradorProcesos();
		lanzador.ejecutar(ruta);
		System.out.println("Proceso Ejecutado!!!");

	}

}
