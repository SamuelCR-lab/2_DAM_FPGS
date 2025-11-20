package Traductor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class VentanaTraductor {
	public static Scanner entrada = new Scanner(System.in);
	private JFrame frame;
	private JTextField txtEscribe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTraductor window = new VentanaTraductor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean controlDeErrores(String palabra) {
		Pattern comprueba = Pattern.compile("^([a-zA-Z]+)$");
		Matcher match = comprueba.matcher(palabra);
			if(match.find()) {
				return true;
			}
			return false;
	}
	/**
	 * Create the application.
	 */
	public VentanaTraductor() {
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
		
		JLabel lblNewLabel = new JLabel("Traductor de Ingles a Español");
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setBounds(94, 11, 237, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Introduce la palabra en Ingles: ");
		lblNewLabel_1.setBounds(10, 60, 185, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtEscribe = new JTextField();
		txtEscribe.setOpaque(false);
		txtEscribe.setDropMode(DropMode.INSERT);
		txtEscribe.setBounds(203, 60, 128, 25);
		frame.getContentPane().add(txtEscribe);
		txtEscribe.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Palabra Traducida en Español: ");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setBounds(10, 143, 185, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_3.setBounds(203, 143, 128, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Escribe una palabra sin números. ");
		lblNewLabel_4.setVisible(false);
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(170, 118, 202, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Traducir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_4.setVisible(false);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);

				if (controlDeErrores(txtEscribe.getText())){
						try{
						
						String palabraInicial = txtEscribe.getText();
						
						String web = "https://www.spanishdict.com/translate/"+palabraInicial;
						Document documento = Jsoup.connect(web).get();
						Element palabraTraducida = documento.select("div#quickdef1-en a.tCur1iYh").get(0);
						String resultado=palabraTraducida.html().toUpperCase();
						lblNewLabel_3.setText(resultado);
						}catch(IOException io) {
							io.getStackTrace();
						}
						lblNewLabel_2.setVisible(true);
						
						lblNewLabel_3.setVisible(true);
						
					}else {
						lblNewLabel_4.setVisible(true);
					}
				}
		});
		btnNewButton.setBounds(335, 61, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		frame.getContentPane().add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 76, 55);
		layeredPane.add(panel);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel.add(lblNewLabel_5);
		
	
		
		
	}
}
