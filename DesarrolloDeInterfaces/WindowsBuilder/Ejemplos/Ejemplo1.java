package Ejemplos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Ejemplo1 {

	private JFrame frame;
	private JTextField txtDsadfasf;
	private JTextField txtAsfddaf;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo1 window = new Ejemplo1();
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
	public Ejemplo1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DAM\\Downloads\\Dragon Emblem of DragonesGOT.png"));
		frame.getContentPane().setBackground(new Color(148, 0, 211));
		frame.setBackground(Color.CYAN);
		frame.setBounds(100, 100, 590, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mi gente estamos en japon");
		lblNewLabel.setBounds(232, 9, 153, 14);
		lblNewLabel.setForeground(new Color(0, 255, 255));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(374, 278, 170, 94);
		frame.getContentPane().add(panel);
		

		JTextArea textArea = new JTextArea("Hola mundo");
		panel.add(textArea);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(new Color(255, 255, 0));
		lblNewLabel_1.setLabelFor(panel);
		panel.add(lblNewLabel_1);
		
		txtDsadfasf = new JTextField();
		txtDsadfasf.setText("dsadfasf");
		txtDsadfasf.setEnabled(false);
		txtDsadfasf.setBounds(201, 153, 86, 20);
		frame.getContentPane().add(txtDsadfasf);
		txtDsadfasf.setColumns(10);
		
		txtAsfddaf = new JTextField();
		txtAsfddaf.setText("vtgvgjh");
		txtAsfddaf.setBounds(151, 190, 86, 20);
		frame.getContentPane().add(txtAsfddaf);
		txtAsfddaf.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("siuuuuuuuuuuu");
		btnNewButton_1.setToolTipText("Pulsa si quieres al bicho\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {//funcion flecha cuando se crea un button que lo que hace es estar antento a cualquier funcion util
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(49, 5, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(22, 123, 141, 20);
		frame.getContentPane().add(passwordField);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinner.setBounds(273, 242, 64, 20);
		frame.getContentPane().add(spinner);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	}
}
