package TraductorEnClases;

import javax.swing.JFrame;

public class MarcoTraductor extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarcoTraductor() {
		setTitle("TRADUCTOR INGLES-ESPAÑOL");
		setSize(800,600);
		
		setLocationRelativeTo(null);
		setResizable(false);
	
		PanelTraductor miPanel = new PanelTraductor();
		add(miPanel);
		
	}
}
