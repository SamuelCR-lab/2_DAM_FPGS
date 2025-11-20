package PracticaAbstractos;

public  class Comun {
	int codigo;
	String titulo;
	int anio;
	
	public Comun(int codigo, String titulo, int anio) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.anio = anio;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	@Override
	public String toString() {
		return " codigo=" + codigo + ", titulo=" + titulo + ", anio=" + anio;
	}
	
}
