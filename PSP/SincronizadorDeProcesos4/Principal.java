package SincronizadorDeProcesos4;

public class Principal {

	public static void main(String[] args) {
		String app = "cmd";//"cmd", "/c", "dir", "*.java"
		String[] ruta = {"/c","dir","/p"};
		GeneradorProcesos lanzador = new GeneradorProcesos();
		lanzador.ejecutar(app,ruta);
		System.out.println("Proceso Ejecutado!!!");

	}

}
