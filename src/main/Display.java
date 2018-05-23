package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	private JFrame mainWindow;
	private Graphics g2;
	private JTextArea info;
	private JButton draw;
	
	
	public static final int HEIGHT = 500;
	public static final int WIDTH = 750;
	/**
	 * Creates and shows the GUI
	 */
	public static int numOfRoomsToMake;
	public void createAndShowGUI(){
		numOfRoomsToMake = 1;
		mainWindow = new JFrame();
		mainWindow.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		mainWindow.setSize(HEIGHT, WIDTH);
		mainWindow.setVisible(true);
		
		
	
		g.gridx = 0;
		g.gridy = 0;
		
		g.weightx = 4;
		
		
		info = new JTextArea();
		mainWindow.add(info, g);
		info.setColumns(50);
		g.gridx = 1;
		g.gridy = 0;
		g.weightx = 0;
		draw = new JButton();
		draw.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    String benis = info.getText();
					numOfRoomsToMake = Integer.parseInt(benis);
				    gameDraw();
					
				  } 
				});
		
		draw.setLocation(40, 40);
		draw.setSize(40, 40);
		draw.setMaximumSize(new Dimension(40, 40));
		draw.setText("Generate New Dungeon");
		mainWindow.add(draw, g);
		
		
		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.weightx = 4;
		g.weighty = 4;
		g.fill = 1;
		g.ipadx = 2;
		g.ipady = 2;
		drawBoard = new JPanel();
		drawBoard.setSize(HEIGHT, WIDTH);
		drawBoard.setMinimumSize(new Dimension(HEIGHT, WIDTH));
		drawBoard.setBackground(Color.BLACK);
		mainWindow.add(drawBoard, g);
		drawBoard.setVisible(true);
		
		mainWindow.pack();
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		mainWindow.setVisible(true);

	}
	
	
	public void createNewMaterial() {
		
	}
	
	public Display(){
		createAndShowGUI();
		try {
			LoadAllObjects.loadMaterials();
			LoadAllObjects.loadWeapons();
			LoadAllObjects.getMaterial("IRON");
			LoadAllObjects.getMaterial("GOLD");

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
