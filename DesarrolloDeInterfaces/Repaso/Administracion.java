package Repaso;

public class Administracion extends Persona {
	int salario;
	String estudios;
	int antiguedad;
	public Administracion(String dNI, String nombre, String apellidos, int salario, String estudios, int antigüedad) {
		super(dNI, nombre, apellidos);
		this.salario = salario;
		this.estudios = estudios;
		this.antiguedad = antigüedad;

		
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public String getEstudios() {
		return estudios;
	}
	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}
	public int getAntigüedad() {
		return antiguedad;
	}
	public void setAntigüedad(int antigüedad) {
		this.antiguedad = antigüedad;
	}
	@Override
	public String toString() {
		return "Administracion"+super.toString()+" Salario= " + salario + ", Estudios= " + estudios + ", Antigüedad= " + antiguedad;
	}
	
}
