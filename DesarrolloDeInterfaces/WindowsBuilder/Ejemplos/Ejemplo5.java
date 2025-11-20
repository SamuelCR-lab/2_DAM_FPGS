package Ejemplos;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;

public class Ejemplo5 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo5 window = new Ejemplo5();
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
	public Ejemplo5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Ejemplo5.class.getResource("/Dragon Emblem of DragonesGOT.png")));
		frame.setBounds(0, 0, 725, 521);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					g.drawImage(foto, 0,0,725, 521, null);
				}
				
			};
			return panelConFondo;
			
		}
	}


