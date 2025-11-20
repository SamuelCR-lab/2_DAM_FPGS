package TraductorEnClases;

import java.awt.EventQueue;

public class Principal {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarcoTraductor miVentana =  new MarcoTraductor();
					miVentana.setSize(1200,800);
					miVentana.setVisible(true);
					
				}catch (Exception i){i.getStackTrace();}
			}
		});
	}

}
