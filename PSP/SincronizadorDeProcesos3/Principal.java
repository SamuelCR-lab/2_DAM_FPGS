package Generador2;

public class Principal {

	public static void main(String[] args) {
		String ruta = "C:/windows/system32";
		String parametros = "notepad.exe";
		GeneradorProcesos lanzador = new GeneradorProcesos();
		lanzador.ejecutar(ruta,parametros);
		System.out.println("Proceso Ejecutado!!!");
	}

}
