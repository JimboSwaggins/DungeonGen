package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Room {
	private int width;
	private int height;
	
	private int xLoc;
	private int yLoc;
	
	private static ArrayList<Node> exits;
	
	public int getWidth() {return this.width;}
	
	public int getHeight() {return this.height;}
	
	public int getX() {return this.xLoc;}
	
	public int getY() {return this.yLoc;}
	
	public Room(Graphics g) {
		width = (int)(Math.random() * 250);
		height = (int)(Math.random() * 250);
		xLoc = (int)(Math.random() * 250);
		yLoc = (int)(Math.random() * 250);
		this.draw(g);
		
		
	}
	
	private void draw(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(xLoc, yLoc, width, height);
	}
	
}
