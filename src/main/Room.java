package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Room{
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
	private Rectangle area;
	
	public Rectangle getArea() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	/**
	 * Returns an array with the corners of the room as points.
	 * @return An array containing the corners of the room, as points starting from the upper left corner and rotating around clockwise.
	 */
	public Point[] getCorners() {
		Point[] temp = new Point[4];
		
		temp[0] = new Point(this.getX(), this.getY());
		temp[1] = new Point(this.getMaxX(), this.getY());
		temp[2] = new Point(this.getMaxX(), this.getMaxY());
		temp[3] = new Point(this.getX(), this.getMaxY());
		return temp;
	}
	
	
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
			int dx = 10;
			int dy = 100;
			int dh = 50;
			int dw = 120;
			boolean hasRolledSuccessfully = false;
			while(!hasRolledSuccessfully) {
				
					if(listOfRooms.size() == 0) {
						break;
					}
					if(listOfRooms.size() > 1) {
						@SuppressWarnings("unused")
						int i = 99;
						i++;
					}
					for(Room e:listOfRooms) {
						if(!hasRolledSuccessfully) {
							dx = RandomGenerator.randomInteger(20, 325);
							dy = RandomGenerator.randomInteger(20, 325);
							dh = RandomGenerator.randomInteger(dy, dy + 100);
							dw = RandomGenerator.randomInteger(dx, dx + 100);
						}
						if(new Rectangle(dx, dy, dh, dw).intersects(e.getArea())) {
							hasRolledSuccessfully = false;
							break; 
						}else {
							hasRolledSuccessfully = true;
						}
					}
				if(hasRolledSuccessfully) {
					break;
				}
			}
			listOfRooms.add(new Room(g, dx, dy, dh, dw));
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
		area = new Rectangle(xLoc, yLoc, width, height);
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
		
		area = new Rectangle(x, y, width, height);
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
		area = new Rectangle(x, y, w, h);
		this.draw(g);
	}
	
	private void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(width, height, xLoc, yLoc);
		
	}
	
}
