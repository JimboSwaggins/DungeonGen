package main;

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
		
		mainWindow.add(display);
		
		
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
}
