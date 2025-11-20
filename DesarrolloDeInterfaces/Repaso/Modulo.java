package Repaso;

public class Modulo {
	String nombre;
	int numHoras;
	Profesor profesor;
	boolean convalidacion;
	

	public Modulo(String nombre, int numHoras, Profesor profesor, boolean convalidacion) {
		super();
		this.nombre = nombre;
		this.numHoras = numHoras;
		this.convalidacion = convalidacion;
		this.profesor = profesor;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumHoras() {
		return numHoras;
	}
	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public boolean isConvalidacion() {
		return convalidacion;
	}
	public void setConvalidacion(boolean convalidacion) {
		this.convalidacion = convalidacion;
	}
	@Override
	public String toString() {
		return "Modulo de Nombre= " + nombre + ", Numero de Horas= " + numHoras + ", Convalidacion= "
				+ convalidacion+ ", Profesor= " + profesor;
	}
	
}
