package Ejemplos;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


public class Ejemplo4 {
	
	private JFrame frame;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo4 window = new Ejemplo4();
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
	public Ejemplo4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Ejemplo4.class.getResource("/Dragon Emblem of DragonesGOT.png")));
		frame.setBounds(100, 100, 725, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(255, 255, 255));
		layeredPane.setBounds(0, 0, 709, 482);
		frame.getContentPane().add(layeredPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 709, 482);
		layeredPane.add(panel);
		panel.setLayout(null);

		btnNewButton_2 = new JButton("Cambiar al morado");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
		});

		btnNewButton_2.setBounds(57, 407, 149, 23);
		panel.add(btnNewButton_2);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 100, 0));
		panel_2.setBounds(0, 0, 709, 482);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		btnNewButton_1 = new JButton("Cambiar al Azul");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_2.setVisible(false);
				
			}
		});
		btnNewButton_1.setBounds(78, 400, 156, 23);
		panel_2.add(btnNewButton_1);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(75, 0, 130));
		panel_1.setBounds(0, 0, 709, 482);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		JButton btnNewButton = new JButton("Cambiar al verde");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		btnNewButton.setBounds(102, 402, 196, 23);
		panel_1.add(btnNewButton);
		
		//Agregar imagen en un nuevo panel
		frame.getContentPane().add(buscarImagen());
		
	}
	
	private Component buscarImagen() {
		BufferedImage fondo = null;
		try {
		 fondo = ImageIO.read(new File("src/Fondo de pantalla.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		final Image foto = fondo;
		JPanel panelConFondo = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(getGraphics());
				g.drawImage(foto, 0,0,70, 70, null);
			}
			
		};
		return panelConFondo;
		
	}
}
