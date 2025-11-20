package Ejemplos;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class Ejemplo3 {

	private JFrame frame;
	private JTextField textField;
	private int contador=0;
	private JPanel panel_1;
	private JPanel panel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo3 window = new Ejemplo3();
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
	public Ejemplo3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.setBounds(100, 100, 676, 454);
		/*Forma de conseguir el medio de la pantalla mediabre un */
		Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
		int tamanioyH = (int) monitor.getHeight();
		int tamanioxW = (int) monitor.getWidth();
		frame.setLocation((tamanioxW/2 - frame.getWidth()/2), (tamanioyH/2 - frame.getHeight()/2));
		//frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 100, 0));
		frame.getContentPane().add(panel, "name_12444438597500");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Clicks");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contador++;
				textField.setText(Integer.toString(contador));
			}
		});
		btnNewButton.setBounds(118, 102, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("NÚMERO DE CLICKS");
		lblNewLabel.setBounds(200, 51, 135, 35);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(219, 96, 101, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SIGUIENTE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(547, 458, 89, 23);
		panel.add(btnNewButton_1);
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		frame.getContentPane().add(panel_1, "name_12478636459300");
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("VOLVER");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(55, 401, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("Siguiente y ultima");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(486, 389, 121, 47);
		panel_1.add(btnNewButton_5);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(75, 0, 130));
		frame.getContentPane().add(panel_2, "name_12515941105800");
		panel_2.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("siguiente... Inicio");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				panel.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(494, 410, 139, 23);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Atras");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
				panel_2.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(70, 410, 89, 23);
		panel_2.add(btnNewButton_4);
	}
}
