package TraductorEnClases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EventoTraducir implements ActionListener{
	JLabel mostrarTraduccion;
	JTextField palabraIntro;
	public EventoTraducir(JTextField palabraIntro, JLabel mostrarTraduccion) {
		this.palabraIntro=palabraIntro;
		this.mostrarTraduccion=mostrarTraduccion;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//Control de errores
		
		if (palabraIntro.getText().isBlank()!=true) {
			mostrarTraduccion.setText(Operaciones.traducirAesp(palabraIntro.getText()));
			
			PanelTraductor.salida.setVisible(true);
			PanelTraductor.lblNewLabel_2.setVisible(true);
			PanelTraductor.lblNewLabel_1.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "No se ha traducido el texto correcto");
		}
	}
}
