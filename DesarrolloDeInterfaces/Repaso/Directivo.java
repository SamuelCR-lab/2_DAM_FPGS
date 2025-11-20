package Repaso;

public class Directivo extends Persona {
	int salario;
	boolean salesiano;
	boolean turno;
	public Directivo(String dNI, String nombre, String apellidos, int salario, boolean salesiano, boolean turno) {
		super(dNI, nombre, apellidos);
		this.salario = salario;
		this.salesiano = salesiano;
		this.turno = turno;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public boolean isSalesiano() {
		return salesiano;
	}
	public void setSalesiano(boolean salesiano) {
		this.salesiano = salesiano;
	}
	public boolean isTurno() {
		return turno;
	}
	public void setTurno(boolean turno) {
		this.turno = turno;
	}
	@Override
	public String toString() {
		return "Directivo"+super.toString()+" Salario= " + salario + ", Salesiano= " + salesiano + ", Turno= " + turno;
	}
	
}
