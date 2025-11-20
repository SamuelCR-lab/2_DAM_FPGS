package Repaso;

public class Alumno extends Persona {

			String fechaNacimiento;
			String sexo;
			boolean repetidor;
			Modulo modulo;
			
			public Alumno(String dNI, String nombre, String apellidos, String fechaNacimiento, String sexo,
					boolean repetidor, Modulo modulo) {
				super(dNI, nombre, apellidos);
				this.fechaNacimiento = fechaNacimiento;
				this.sexo = sexo;
				this.repetidor = repetidor;
				this.modulo = modulo;
			}
			public String getFechaNacimiento() {
				return fechaNacimiento;
			}
			public void setFechaNacimiento(String fechaNacimiento) {
				this.fechaNacimiento = fechaNacimiento;
			}
			public String isSexo() {
				return sexo;
			}
			public void setSexo(String sexo) {
				this.sexo = sexo;
			}
			public boolean isRepetidor() {
				return repetidor;
			}
			public void setRepetidor(boolean repetidor) {
				this.repetidor = repetidor;
			}
			public Modulo getModulo() {
				return modulo;
			}

			public void setModulo(Modulo modulo) {
				this.modulo = modulo;
			}
			@Override
			public String toString() {
				return "Alumno"+super.toString()+" FechaNacimiento= " + fechaNacimiento + ", sexo= " + sexo + ", repetidor= " + repetidor
						+ ", modulo= " + modulo;
			}


}
