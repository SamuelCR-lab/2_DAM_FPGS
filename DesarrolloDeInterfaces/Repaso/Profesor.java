package Repaso;

public class Profesor extends Persona{
	int salario;
	int numeroAsignaturas;
	boolean tutor;
	
	public Profesor(String dNI, String nombre, String apellidos, int salario, int numeroAsignaturas, boolean tutor) {
		super(dNI, nombre, apellidos);
		this.salario = salario;
		this.numeroAsignaturas = numeroAsignaturas;
		this.tutor = tutor;
	}
	
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public int getNumeroAsignaturas() {
		return numeroAsignaturas;
	}
	public void setNumeroAsignaturas(int numeroAsignaturas) {
		this.numeroAsignaturas = numeroAsignaturas;
	}
	public boolean isTutor() {
		return tutor;
	}
	public void setTutor(boolean tutor) {
		this.tutor = tutor;
	}

	@Override
	public String toString() {
		return "Profesor"+super.toString()+" Salario= " + salario + ", NumeroAsignaturas= " + numeroAsignaturas + ", Tutor= " + tutor;
	}
	
}
