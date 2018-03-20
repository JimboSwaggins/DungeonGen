package main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Display {
	public void createAndShowGUI() {
		JFrame mainWindow = new JFrame();
		mainWindow.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JTextArea display = new JTextArea();
		
		g.gridx = 0;
		g.gridy = 0;
		display.setColumns(50);
		display.setRows(50);
		display.setMinimumSize(new Dimension(500, 500));
		mainWindow.add(display, g);
	
		
		
		
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}
	
	public Display() {
		createAndShowGUI();
	}
	
	public static void main(String[] args) {
		new Display();
	}
}
