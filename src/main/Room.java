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
	
	public Room(Graphics g){
		width = RandomGenerator.randomInteger(40, 175);
		height = RandomGenerator.randomInteger(40, 175);
		xLoc = RandomGenerator.randomInteger(20, 325);
		yLoc = RandomGenerator.randomInteger(20, 325);
		this.draw(g);
	}
	
	public Room(Graphics g, int x, int y) {
		width = RandomGenerator.randomInteger(40, 175);
		height = RandomGenerator.randomInteger(40, 175);
		xLoc = x;
		yLoc = y;
		this.draw(g);
	}
	
	public Room(Graphics g, int x, int y, int h, int w) {
		width = w;
		height = h;
		xLoc = x;
		yLoc = y;
		this.draw(g);
	}
	
	private void draw(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(xLoc, yLoc, width, height);
	}
	
}
