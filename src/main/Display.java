package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Display {
	public JPanel drawBoard;
	private BufferedImage image;

	private Graphics g2;
	
	public static final int HEIGHT = 500;
	public static final int WIDTH = 750;
	/**
	 * Creates and shows the GUI
	 */
	public static int numOfRoomsToMake;
	public void createAndShowGUI(){
		numOfRoomsToMake = 1;
		JFrame mainWindow = new JFrame();
		//mainWindow.setLayout(new GridBagLayout());
		//GridBagConstraints g = new GridBagConstraints();
		mainWindow.setSize(HEIGHT, WIDTH);
		mainWindow.setVisible(true);
		drawBoard = new JPanel();
		drawBoard.setSize(HEIGHT, WIDTH);
		mainWindow.add(drawBoard);
		drawBoard.setVisible(true);
	
		JTextArea info = new JTextArea();
		info.setLocation(0, 0);
		info.setColumns(4);
		info.setRows(1);
		drawBoard.add(info);
		JButton Draw = new JButton();
		Draw.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    String benis = info.getText();
					numOfRoomsToMake = Integer.parseInt(benis);
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
		try {
			Loot.loadPrefix();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Room.makeMultipleRooms(g2, numOfRoomsToMake);
		Room.drawAllRooms(g2);
		for(Room room: Room.listOfRooms) {
			room.connectRooms(g2);
		}
		g2 = drawBoard.getGraphics();
		g2.drawImage(image, 0, 0, null);
		
	}
}
