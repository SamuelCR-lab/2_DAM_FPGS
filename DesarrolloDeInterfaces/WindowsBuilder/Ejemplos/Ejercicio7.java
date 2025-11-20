package Ejemplos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejercicio7 {

	private JFrame frame;
	private JLabel lblNewLabel, lblNewLabel_1,lblNewLabel_2,progressBar;
	Timer temporizador;
	int i =0;
	int progreso=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio7 window = new Ejercicio7();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ejercicio7() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(false);

		lblNewLabel.setBounds(69, 149, 300, 35);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(0, 250, 154));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(299, 66, 63, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temporizador.start();
			}
		});
		btnNewButton.setBounds(35, 66, 85, 35);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Preparando café");
		lblNewLabel_2.setBounds(92, 195, 85, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		progressBar = new JLabel("");
		progressBar.setBackground(new Color(100, 149, 237));
		frame.getContentPane().add(progressBar);
		
		temporizador=new Timer(1000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				i += 10;
				progreso += 30;
				progressBar.setBounds(69, 149, progreso, 35);
				progressBar.setText(String.valueOf(i+"%"));
				progressBar.setOpaque(true);
				if (i == 100) {
					temporizador.stop();
				}
				/*if (i>0) {
					temporizador.stop();
					
					//Este es un deshabilitador del botón para que ahorrar codigo
					btnNewButton.setEnabled(false);
					progressBar.setVisible(true);
				}else {
					//lblNewLabel_1.setFO
				}*/
			}
		});
		/*progressBar.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				String msg="ya no queda nada";
				JOptionPane.showMessageDialog(null,msg,"AL RECREO?",1);
			}
		});*/
		
		
	}

	
	
	
}
