package Ejemplos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Ejercicio6 {

	private JLabel lblNewLabel, lblNewLabel_;
	private JFrame frame;
	Timer temporizador;
	int i =11;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio6 window = new Ejercicio6();
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
	public Ejercicio6() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setBounds(249, 117, 88, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				temporizador.start();
			}
		});
		
		JLabel lblNewLabel_ = new JLabel("Feliz puente!!!!!");
		lblNewLabel_.setVisible(false);
		lblNewLabel_.setOpaque(true);
		lblNewLabel_.setBackground(new Color(0, 255, 255));
		lblNewLabel_.setBounds(200, 40, 132, 34);
		frame.getContentPane().add(lblNewLabel_);
		btnNewButton.setBounds(25, 117, 132, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salir...");
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		temporizador= new Timer(1000, new ActionListener(){
			@Override	
			public void actionPerformed(ActionEvent e) {
					i--;
					lblNewLabel.setText(String.valueOf(i));
					
					if (i ==0) {
						temporizador.stop();
						lblNewLabel_.setVisible(true);
						//Este es un deshabilitador del botón para que ahorrar codigo
						btnNewButton.setEnabled(false);
						btnNewButton_1.setVisible(true);
					}
				
				/*Manera de 
				 * if(i!=0) {
					i--;
					lblNewLabel.setText(String.valueOf(i));
					
					if (i ==0) {
						temporizador.stop();
						lblNewLabel_.setVisible(true);
					}
				}else {
					i=10;
					lblNewLabel.setText(String.valueOf(i));
					lblNewLabel_.setVisible(false);
				}*/
			}
		});
	}
}
