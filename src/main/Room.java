package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Room {
	private int width;
	private int height;
	
	private int xLoc;
	private int yLoc;
	
	private ArrayList<Node> exits;
	private static ArrayList<Room> listOfRooms = new ArrayList<Room>();
		
	public ArrayList<Node> getExits(){return this.exits;}
	
	
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public int getX() {return this.xLoc;}	
	public int getY() {return this.yLoc;}
	
	public int getMaxX() {
		return this.xLoc + this.width;
	}
	
	public int getMaxY() {
		return this.yLoc + this.height;
	}
	public Point getLocation() {
		return new Point(this.xLoc, this.yLoc);
	}
	
	public static void makeMultipleRooms(Graphics g, int numOfRooms) {
		listOfRooms.clear();
		for(int roomsMade = 0; roomsMade < numOfRooms; roomsMade++) {
			boolean a  = true;
			while(true) {
				int dx = RandomGenerator.randomInteger(20, 325);
				int dy = RandomGenerator.randomInteger(20, 325);
				int dc = RandomGenerator.randomInteger(40, 175);
				int dw = RandomGenerator.randomInteger(40, 175);
				if(listOfRooms.size() != 0) {
					for(Room toCheck:listOfRooms) {
					//if otherMinx < x < other maxX
					//if otherMinY < y < other MaxY
						if(toCheck.getX() <= dx && dx <= toCheck.getMaxX() && toCheck.getY() <= dy && dy <= toCheck.getMaxY()) {
							a = false;
							break;
						}else {
							a = true;
						}
					}
				}
				if(a) {
					listOfRooms.add(new Room(g, dx, dy, dc, dw));
					a = false;
					break;
				}
				
			}
		}
	}
	
	public static void drawAllRooms(Graphics g) {
		for(Room e: listOfRooms) {
			e.draw(g);
		}
	}
	/**
	 * Generates a room at a random location with a random width and height.
	 * @param g The Graphics unit to draw the room.
	 */
	public Room(Graphics g){
		width = RandomGenerator.randomInteger(40, 175);
		height = RandomGenerator.randomInteger(40, 175);
		xLoc = RandomGenerator.randomInteger(20, 325);
		yLoc = RandomGenerator.randomInteger(20, 325);
		this.draw(g);
	}
	
	/**
	 * Generates a room at a specified location with a random width and height.
	 * @param g The Graphics unit to draw the room.
	 * @param x The x-Location of the room.
	 * @param y The y-Location of the room.
	 */
	public Room(Graphics g, int x, int y) {
		width = RandomGenerator.randomInteger(40, 175);
		height = RandomGenerator.randomInteger(40, 175);
		xLoc = x;
		yLoc = y;
		this.draw(g);
	}
	
	
	/**
	 * Generates a room at a specified location with a specified width and height.
	 * @param g The Graphics unit to draw the room.
	 * @param x The x-Location of the room.
	 * @param y The y-Location of the room.
	 * @param h The Height of the room.
	 * @param w The Width of the room.
	 */
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
