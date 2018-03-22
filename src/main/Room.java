package main;

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
	
	public Room(int x, int y, int width, int height) {
		
	}
}
