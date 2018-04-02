package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class Display {
	public JPanel drawBoard;
	private BufferedImage image;

	
	private Graphics g2;
	
	public static final int HEIGHT = 500;
	public static final int WIDTH = 750;
	/**
	 * Creates and shows the GUI
	 * @throws ThisFunctionCanNotHandleNegativeParameters 
	 */
	public void createAndShowGUI(){
		
		JFrame mainWindow = new JFrame();
		//mainWindow.setLayout(new GridBagLayout());
		//GridBagConstraints g = new GridBagConstraints();
		mainWindow.setSize(HEIGHT, WIDTH);
		mainWindow.setVisible(true);
		drawBoard = new JPanel();
		drawBoard.setSize(HEIGHT, WIDTH);
		mainWindow.add(drawBoard);
		drawBoard.setVisible(true);
	
		
		JButton Draw = new JButton();
		Draw.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    
					gameDraw();
				  } 
				});
		
		Draw.setLocation(40, 40);
		Draw.setSize(40, 40);
		Draw.setMaximumSize(new Dimension(40, 40));
		Draw.setText("Generate New Dungeon");
		drawBoard.add(Draw);
		
		mainWindow.setResizable(false);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainWindow.setVisible(true);

	}
	
	public Display(){
		createAndShowGUI();
	}
	public static void main(String[] args){
		new Display();
	}

	
	/**
	 * Draws the game
	 * 
	 */
	private void gameDraw(){
		image  = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2  = image.getGraphics();
		Room.makeMultipleRooms(g2, 12);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Room.drawAllRooms(g2);
		g2 = drawBoard.getGraphics();
		g2.drawImage(image, 0, 0, null);
		
	}
}
