package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;

import utils.StringAnalyzer;

public class Display {
	public JPanel drawBoard;
	private BufferedImage image;
	private JFrame mainWindow;
	private Graphics g2;
	private JTextArea info;
	private JButton draw;


	private JButton addNewMaterial;
	private JButton removeMaterial;

	private JButton addNewWeapon;


	public final String materialTable = ".\\tables\\MaterialTable.txt";


	public JFrame consoleWindow;
	public static JTextArea consoleLog;

	public static JTextArea getConsole() {
		return consoleLog;
	}


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

		g.gridx = 6;

		removeMaterial = new JButton();
		removeMaterial.setText("Remove a material");
		removeMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMaterial();
			}
		});

		mainWindow.add(removeMaterial, g);

		g.gridx = 5;
		g.gridy = 2;

		addNewWeapon = new JButton();
		addNewWeapon.setText("Generate new weapon");
		mainWindow.add(addNewWeapon, g);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.pack();



		//Console
		consoleWindow = new JFrame();
		consoleWindow.setSize(new Dimension(600, 600));

		consoleWindow.setResizable(false);
		consoleWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		consoleLog = new JTextArea();
		JScrollPane consoleScrollArea= new JScrollPane(consoleLog); 
		consoleWindow.add(consoleScrollArea);
		consoleLog.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					parseText(consoleLog.getText());
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
		});

		consoleWindow.setVisible(true);
		mainWindow.setVisible(true);
	}

	public void clear() {
		consoleLog.setText("");
	}

	public static void cprint(String n) {
		consoleLog.append("\n" + n);
	}

	public void parseText(String toParse) {
		int last  = consoleLog.getLineCount() - 1;
		int start = 0;
		int end = 0;
		String lastLine = "";
		try {
			start = consoleLog.getLineStartOffset(last);
			end = consoleLog.getLineEndOffset(last);
			lastLine = consoleLog.getText().substring(start, end);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		if(lastLine.equals(" ")||lastLine.equals(null)) {
			cprint("Please don't do that.");
			return;
		}
		String[] toRead = lastLine.split(" ");

		if(lastLine.equals("clear")) {
			clear();
		}if(lastLine.equals("reload")) {
			LoadAllObjects.refreshDatabases();
			cprint("Reloaded all tables");
		}if(lastLine.equals("exit")){
			mainWindow.dispose();
			consoleWindow.dispose();
		}if(toRead.length == 1&&toRead[0].equals("help")) {
			cprint("clear  -> clears the console log");
			cprint("reload -> reloads all tables");
			cprint("exit   -> closes all windows");
			cprint("list   -> lists all items in a set");
			cprint("help   -> lists all commands that can be used");
			cprint("\ntype help + 'command' for more info on it's usage");


		}if(toRead.length == 2 && toRead[0].equals("help")) {
			switch(toRead[1].toLowerCase()) {

			case "help":
				cprint("you don't get any help for that one bud");
				break;
			case "list":
				cprint("list [parameter]");
				cprint("valid parameters:");
				cprint("-m  >> Lists all materials that are currently loaded");
				break;
			case "reload":
				cprint("reloads all tables from file system.");
				break;
			case "exit":
				cprint("closes all windows (probably)");
				break;
			case "clear":
				cprint("clears the console screen. There is no going back from this one.");
				break;
				
			default:
				cprint("I don't understand");
			}
		}
		if(lastLine.equals("")) {
			cprint("You didn't enter anything");
		}
		if(toRead[0].equals("list")) {
			if(toRead.length == 1) {
				cprint("You didn't enter a parameter");
				return;
			}
			switch(toRead[1]) {
			case "-m":
				LoadAllObjects.printMaterials();
				break;
			default:
				cprint("Invalid parameter to list");
				break;
			}
		}

	}

	public void createNewMaterial() {
		JFrame tempWindow = new JFrame();

		tempWindow.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();


		//Default Constraints
		g.gridx = 0;
		g.gridy = 0;
		g.ipadx = 50;
		g.ipady = 10;
		g.fill = 1;


		//Material Name Input
		JTextArea inputName = new JTextArea();
		inputName.setColumns(30);
		inputName.setRows(1);
		inputName.setBorder(BorderFactory.createLineBorder(Color.black));
		inputName.setName("Name");


		tempWindow.add(inputName, g);

		//Material Name Label
		g.gridx = 1;
		g.gridy = 0;

		JLabel nameLabel = new JLabel();
		nameLabel.setText("Material Name");		
		tempWindow.add(nameLabel, g);


		//Material Density Input
		g.gridx = 0;
		g.gridy = 1;


		JTextArea inputDensity = new JTextArea();
		inputDensity.setColumns(30);
		inputDensity.setRows(1);
		inputDensity.setBorder(BorderFactory.createLineBorder(Color.black));
		inputDensity.setName("density");

		tempWindow.add(inputDensity, g);


		//Material Density Label
		g.gridx = 1;
		g.gridy = 1;

		JLabel densityLabel = new JLabel();
		densityLabel.setText("Material Density");
		tempWindow.add(densityLabel, g);


		//Material Value Input
		g.gridx = 0;
		g.gridy = 2;

		JTextArea inputValue = new JTextArea();
		inputValue.setColumns(30);
		inputValue.setRows(1);
		inputValue.setBorder(BorderFactory.createLineBorder(Color.black));
		inputValue.setName("Material Base Value");

		tempWindow.add(inputValue, g);

		//Material Value Label
		g.gridx = 1;
		g.gridy = 2;

		JLabel valueLabel = new JLabel();
		valueLabel.setText("Material Base Value");
		tempWindow.add(valueLabel, g);



		//Material Color Input
		g.gridx = 0;
		g.gridy = 3;

		JTextArea inputColor = new JTextArea();
		inputColor.setColumns(30);
		inputColor.setRows(1);
		inputColor.setBorder(BorderFactory.createLineBorder(Color.black));
		inputColor.setName("Color");

		tempWindow.add(inputColor, g);

		//Material Color Label
		g.gridx = 1;
		g.gridy = 3;

		JLabel colorLabel = new JLabel();
		colorLabel.setText("Material Color");
		tempWindow.add(colorLabel, g);



		//Material Temperature Input
		g.gridx = 0;
		g.gridy = 4;

		JTextArea inputTemp = new JTextArea();
		inputTemp.setColumns(30);
		inputTemp.setRows(1);
		inputTemp.setBorder(BorderFactory.createLineBorder(Color.black));
		inputTemp.setName("Temperature");

		tempWindow.add(inputTemp, g);



		//Material Temperature Label
		g.gridx = 1;
		g.gridy = 4;

		JLabel tempLabel = new JLabel();
		tempLabel.setText("Material Temp");
		tempWindow.add(tempLabel, g);


		//Save and Exit Button
		g.gridx = 0;
		g.gridy = 5;

		JButton saveButton = new JButton();
		saveButton.setText("Save and Exit");


		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = materialTable;
				try(FileWriter fw = new FileWriter(fileName, true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)){
					JTextArea[] checkForNull = new JTextArea[]{inputName, inputDensity, inputTemp, inputColor, inputValue};
					for(JTextArea p:checkForNull) {
						if(StringAnalyzer.isNull(p.getText())) {
							JOptionPane.showMessageDialog(tempWindow, "You entered NOTHING as the value for the " + p.getName() + ".");
							return;
						}
					}
					if(StringAnalyzer.containsCharacters(inputDensity.getText())) {
						JOptionPane.showMessageDialog(tempWindow, "Please only enter numbers into the density box.");
						return;
					}
					if(StringAnalyzer.containsCharacters(inputValue.getText())){
						JOptionPane.showMessageDialog(tempWindow, "Please only enter numbers into the value box.");
						return;
					}
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
				tempWindow.dispatchEvent(new WindowEvent(tempWindow, WindowEvent.WINDOW_CLOSING));
			}
		});

		tempWindow.add(saveButton, g);
		tempWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tempWindow.pack();
		tempWindow.setVisible(true);
	}

	public void removeMaterial() {
		JFrame tempWindow = new JFrame();
		tempWindow.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();


		//Default Constraints
		g.gridx = 0;
		g.gridy = 0;
		g.ipadx = 50;
		g.gridheight= 500;
		g.fill = 10;


		g.gridx = 0;
		g.gridy = 0;

		JComboBox<String> materialListing = new JComboBox<String>();



		try{
			FileReader fr = new FileReader(materialTable);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				//This begins reading for a new weapon type
				if(line.contains("MATERIAL:")) {
					materialListing.addItem(line.replaceAll("MATERIAL:", ""));
				}
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		tempWindow.add(materialListing, g);

		g.gridx = 1;
		g.gridy = 0;

		JButton saveAndExit = new JButton();
		saveAndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					FileReader fr = new FileReader(materialTable);
					LineNumberReader br = new LineNumberReader(fr);
					String line = "";
					boolean isRemoving  = false;
					int start = 0;
					int end = 0;
					while((line = br.readLine()) != null) {
						//This begins reading for a new weapon type
						if(line.contains("MATERIAL:" + materialListing.getSelectedItem())) {
							isRemoving = true;
							start = br.getLineNumber();
						}
						if(line.contains("/MATERIAL")&&isRemoving) {
							end = br.getLineNumber();
							isRemoving = false;
							break;
						}
					}

					fr.close();
					br.close();


					LineNumberReader br2 = new LineNumberReader(new FileReader(".\\tables\\MaterialTable.txt"));
					File f = new File(".\\tables\\MaterialTable.txt");
					File buffer = new File(".\\tables\\MaterialTable.temp");
					// Open a temporary file to write to.
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(buffer)));

					// ... then inside your loop ...

					while ((line = br2.readLine()) != null) {
						if(br2.getLineNumber() >= start&&br2.getLineNumber() <= end) {
							writer.append("");
						}else {
							writer.append(line + "\n");
						}
					}

					// ... and finally ...
					writer.close();
					br2.close();

					f.delete();
					if(renameFileExtension(".\\tables\\MaterialTable.temp", "txt")) {
						System.out.println("File successfully modified");
					}else {
						System.out.println("That didn't work." + getFileExtension(".\\tables\\MaterialTable.temp"));
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		tempWindow.add(saveAndExit, g);
		tempWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tempWindow.pack();
		tempWindow.setVisible(true);
	}

	//Credit to http://www.rgagnon.com/javadetails/java-0541.html
	public static boolean renameFileExtension
	(String source, String newExtension)
	{
		String target;
		String currentExtension = getFileExtension(source);

		if (currentExtension.equals("")){
			target = source + "." + newExtension;
		}
		else {
			target = source.replaceFirst(Pattern.quote("." +
					currentExtension) + "$", Matcher.quoteReplacement("." + newExtension));

		}
		return new File(source).renameTo(new File(target));
	}

	public static String getFileExtension(String f) {
		String ext = "";
		int i = f.lastIndexOf('.');
		if (i > 0 &&  i < f.length() - 1) {
			ext = f.substring(i + 1);
		}
		return ext;
	}

	public void populateDirectory() throws IOException, URISyntaxException {
		//String filePath = Display.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		//System.out.println(filePath);
		if(!new File(".\\tables").exists()) {
			new File(".\\tables").mkdir();
		}if(!new File(".\\tables\\MaterialTable.txt").exists()) {
			new File(".\\tables\\MaterialTable.txt").createNewFile();
		}
		if(!new File(".\\tables\\WeaponTable.txt").exists()){
			new File(".\\tables\\WeaponTable.txt").createNewFile();
		}
	}
	public Display(){
		createAndShowGUI();
		try {
			populateDirectory();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			LoadAllObjects.loadMaterials();
			LoadAllObjects.loadWeapons();
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
