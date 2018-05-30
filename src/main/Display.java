package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JButton addNewMaterial;
	private JButton addNewWeapon;
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
		g.gridheight = 10;
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

		g.gridwidth = 1;
		g.gridheight = 1;
		g.ipadx = 1;
		g.ipady = 1;
		g.weightx = 1;
		g.weighty = 1;
		g.fill = 0;
		g.gridx = 5;
		g.gridy = 1;

		addNewMaterial = new JButton();
		addNewMaterial.setText("Generate new material");
		addNewMaterial.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				createNewMaterial();
			} 
		});
		mainWindow.add(addNewMaterial, g);


		g.gridx = 5;
		g.gridy = 2;

		addNewWeapon = new JButton();
		addNewWeapon.setText("Generate new weapon");
		mainWindow.add(addNewWeapon, g);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);

	}


	public void createNewMaterial() {
		JFrame tempWindow = new JFrame();
		
		tempWindow.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		g.gridx = 0;
		g.gridy = 0;
		g.ipadx = 50;
		g.ipady = 10;
		g.fill = 1;
		JTextArea inputName = new JTextArea();
		inputName.setColumns(30);
		inputName.setRows(1);
		
		tempWindow.add(inputName, g);
		g.gridx = 1;
		g.gridy = 0;
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Material Name");
		
		tempWindow.add(nameLabel, g);
		
		g.gridx = 0;
		g.gridy = 1;
		
		JTextArea inputDensity = new JTextArea();
		inputDensity.setColumns(30);
		inputDensity.setRows(1);
		tempWindow.add(inputDensity, g);
		
		g.gridx = 1;
		g.gridy = 1;
		
		JLabel densityLabel = new JLabel();
		densityLabel.setText("Material Density");
		tempWindow.add(densityLabel, g);
		
		g.gridx = 0;
		g.gridy = 2;
		
		JTextArea inputValue = new JTextArea();
		inputValue.setColumns(30);
		inputValue.setRows(1);
		
		tempWindow.add(inputValue, g);
		
		
		g.gridx = 1;
		g.gridy = 2;
		
		JLabel valueLabel = new JLabel();
		valueLabel.setText("Material Base Value");
		tempWindow.add(valueLabel, g);
		
		
		g.gridx = 0;
		g.gridy = 3;
		
		JTextArea inputColor = new JTextArea();
		inputColor.setColumns(30);
		inputColor.setRows(1);
		
		tempWindow.add(inputColor, g);
		
		
		g.gridx = 1;
		g.gridy = 3;
		
		JLabel colorLabel = new JLabel();
		colorLabel.setText("Material Color");
		tempWindow.add(colorLabel, g);
		
		g.gridx = 0;
		g.gridy = 4;
		
		JTextArea inputTemp = new JTextArea();
		inputTemp.setColumns(30);
		inputTemp.setRows(1);
		
		tempWindow.add(inputTemp, g);
		
		
		g.gridx = 1;
		g.gridy = 4;
		
		JLabel tempLabel = new JLabel();
		tempLabel.setText("Material Temp");
		tempWindow.add(tempLabel, g);
		
		
		
		g.gridx = 0;
		g.gridy = 5;
		
		JButton saveButton = new JButton();
		saveButton.setText("Save and Exit");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = ".\\src\\main\\MaterialTable.txt";
				try(FileWriter fw = new FileWriter(fileName, true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
					out.println("");
					out.println("MATERIAL:" + inputName.getText());
					out.println("DENSITY:" + inputDensity.getText());
					out.println("TEMP:" + inputTemp.getText());
					out.println("COLOR:" + inputColor.getText());
					out.println("VALUE:" + inputValue.getText());
					out.println("/MATERIAL");
					
					} catch (IOException e1) {
					    e1.printStackTrace();
					}
			}
		});
		
		tempWindow.add(saveButton, g);
		tempWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tempWindow.pack();
		tempWindow.setVisible(true);
	}

	public void createNewWeapon() {
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
		Room.makeMultipleRooms(g2, numOfRoomsToMake);
		Room.drawAllRooms(g2);
		for(Room room: Room.listOfRooms) {
			room.connectRooms(g2);
		}
		g2 = drawBoard.getGraphics();
		g2.drawImage(image, 0, 0, null);

	}
}
