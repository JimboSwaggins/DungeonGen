package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class Display {
	public JPanel drawBoard;
	private BufferedImage image;


	public static final int HEIGHT = 500;
	public static final int WIDTH = 500;
	/**
	 * Creates and shows the GUI
	 */
	public void createAndShowGUI() {
		
		JFrame mainWindow = new JFrame();
		//mainWindow.setLayout(new GridBagLayout());
		//GridBagConstraints g = new GridBagConstraints();
		mainWindow.setSize(HEIGHT, WIDTH);
		mainWindow.setVisible(true);
		drawBoard = new JPanel();
		drawBoard.setSize(HEIGHT, WIDTH);
		mainWindow.add(drawBoard);
		drawBoard.setVisible(true);
	
		
		
		mainWindow.setResizable(false);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainWindow.setVisible(true);
		gameDraw();

	}
	
	public Display() {
		createAndShowGUI();
		gameDraw();
	}
	public static void main(String[] args) {
		new Display();
		
	}

	/**
	 * Draws the game
	 */
	private void gameDraw(){
		image  = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		Graphics g2  = image.getGraphics();
		//g2.drawImage(image, 0, 0, null);
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 1280, 720);
		
		g2.setColor(Color.GREEN);
		Room e = new Room(g2);
		//g2.setColor(Color.RED);
		//g2.fillRect(10, 10, 100, 100);
		g2 = drawBoard.getGraphics();
		g2.drawImage(image, 0, 0, null);
		
	}
}
