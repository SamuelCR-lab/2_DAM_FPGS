package PracticaAbstractos;


public class Libros extends Comun{
	static boolean prestado;
	public Libros(int codigo, String titulo, int anio) {
		super(codigo, titulo, anio);
		prestado = false;
	}

	public static void prestar() {
		prestado=true;
	}
	public static void devolver() {
		prestado=false;
	}
	public static boolean prestado() {
		return prestado;
	}
	
}
