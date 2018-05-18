package main;

import java.awt.Color;
import java.awt.Graphics;

public class Furniture {
	private int xLocation;
	private int yLocation;
	
	private int width;
	private int height;
	
	private Room parent;
	
	public int rarity;
	
	
	public int getX() {return this.xLocation;}
	
	public int getY() {return this.yLocation;}
	
	
	public int getWidth(){return this.width;}
	public int getHeight(){return this.height;}
	
	public Room getParent() {
		return this.parent;
	}
	
	public Furniture(int Rarity) {
		this.rarity = Rarity;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(xLocation, yLocation, width, height);
	}
}
