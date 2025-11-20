package PracticaAbstractos;

public class Revista extends Comun{
	int numero;     
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Revista(int codigo, String titulo, int anio, int numero) {
		super(codigo, titulo, anio);
		this.numero = numero;
	}
	
	
	
}
