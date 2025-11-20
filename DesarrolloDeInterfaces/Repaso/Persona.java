package Repaso;

public abstract class Persona {

	String dNI;
	String nombre;
	String apellidos;
	int salario;
	
	 public Persona(String dNI, String nombre, String apellidos) {
		super();
		this.dNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
	}
	public String getDNI() {
		return dNI;
	}
	public void setDNI(String dNI) {
		this.dNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	protected abstract int getSalario() ;
	@Override
	public String toString() {
		return " de DNI= " + dNI + ", Nombre= " + nombre + ", Apellidos= " + apellidos;
	}
	

}
