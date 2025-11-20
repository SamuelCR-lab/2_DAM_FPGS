package LanzadorProcesos1;

public class Principal {

	public static void main(String[] args) {
		String ruta = "%windir%\\system32\\cmd.exe";
		GeneradorProceso lanzador = new GeneradorProceso();
		lanzador.ejecutar(ruta);
		System.out.println("Proceso Ejecutado!!!");
	}

}
