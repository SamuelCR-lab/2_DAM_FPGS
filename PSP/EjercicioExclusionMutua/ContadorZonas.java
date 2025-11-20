package EjercicioExclusionMutua;

public class ContadorZonas {
		private int cuentaGlobal=0;
		private int cuentaPP=0;
		private int cuentaPSOE=0;
		private int cuentaCIU=0;
		private int cuentaNSNC=0;
		
		synchronized public int getCuentaPP() {
			return cuentaPP;
		}
		synchronized public int getCuentaPSOE() {
			return cuentaPSOE;
		}
		synchronized public int getCuentaCIU() {
			return cuentaCIU;
		}
		synchronized public int getCuentaNSNC() {
			return cuentaNSNC;
		}
		synchronized public int getCuenta() {	
			return cuentaGlobal;
		}
		
		synchronized public int incrementaPP() {
			this.cuentaPP++;
			return cuentaPP;
		}
		synchronized public int incrementaPSOE() {
			this.cuentaPSOE++;
			return cuentaPSOE;
		}
		synchronized public int incrementaCIU() {
			this.cuentaCIU++;
			return cuentaCIU;
		}
		synchronized public int incrementaNSNC() {
			this.cuentaNSNC++;
			return cuentaNSNC;
		}
		synchronized public int incrementa() {
			this.cuentaGlobal++;
			return cuentaGlobal;
		}


	}


