package PerseguirMosca;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MatarAlaMosca {

	private JFrame frame;
	
	private JLabel lblNewLabel,lblNewLabel_1,LabelMosca;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatarAlaMosca window = new MatarAlaMosca();
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
	public MatarAlaMosca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Random random = new Random();
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		LabelMosca = buscarImagen();
		frame.getContentPane().add(LabelMosca);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 725, 309, 25);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(475, 725, 288, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		frame.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent evento) {
				int xM = evento.getX();
				int yM = evento.getY();
				lblNewLabel.setText("Movimiento del Ratón en X = "+xM+", en Y = "+yM);
				int MoscaX = LabelMosca.getX();
				int MoscaY = LabelMosca.getY();
				lblNewLabel_1.setText("Movimiento de la Mosca en X = "+MoscaX+", en Y = "+MoscaY);
				
				
				
				int distanEnX = Math.abs(xM-MoscaX);
				int distanEnY = Math.abs(xM-MoscaY);
				int limite = 75;
				int moscaW = LabelMosca.getWidth(), moscaH =LabelMosca.getHeight();
				if (distanEnX < limite || distanEnY < limite) {
					int movimientoEnX = random.nextInt(101)-50;
					int movimientoEnY = random.nextInt(101)-50;
					//Para que el movimiento no corta la mosca por la mitad cuando llega al limite o borde por ello lo de panelConFondoW que se tiene que multiplicar por dos porque represeanta la mitad 
					
					
					int nuevoX = Math.max(0,Math.min(MoscaX+ movimientoEnX,frame.getWidth()-2*moscaW));
					int nuevoY = Math.max(0,Math.min(MoscaY+ movimientoEnY,frame.getHeight()-2*moscaH));
					LabelMosca.setLocation(nuevoX, nuevoY);
				}
	
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private JLabel buscarImagen() {
		BufferedImage fondo = null;
		try {
		 fondo = ImageIO.read(new File("src/MoscaJuzgadora.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		final Image foto = fondo;
		LabelMosca = new JLabel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(getGraphics());
				g.drawImage(foto, 0,0,70, 70, null);
			}
			
		};
		LabelMosca.setBounds(330, 330, 100, 100);
		return LabelMosca;
		
		
	}
	private void persecucionMosca() {
		
		
	}
}
