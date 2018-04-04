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
	
	public ArrayList<Node> exits;
	public static ArrayList<Room> listOfRooms = new ArrayList<Room>();
		
	public ArrayList<Node> getExits(){return exits;}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public int getX() {return this.xLoc;}	
	public int getY() {return this.yLoc;}
	@SuppressWarnings("unused")
	private Rectangle area;
	
	public Rectangle getArea() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	/**
	 * Returns an array with the corners of the room as points.
	 * @return An array containing the corners of the room, as points starting from the upper left corner and rotating around clockwise.
	 */
	
	
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
		
		while(listOfRooms.size()  < numOfRooms) {
			int randomX = RandomGenerator.randomInteger(Display.WIDTH/2);
			int randomY = RandomGenerator.randomInteger(Display.HEIGHT/2);
			boolean isValidLocation = true;
			if(listOfRooms.size() == 0 ) {
				int randomWidth = 50;
				int randomHeight = 50;
				
				Room roomX = new Room(g, randomX, randomY, randomWidth, randomHeight);
				listOfRooms.add(roomX);
				continue;
			}
			
			for(Room e:listOfRooms) {
				if(e.getArea().contains(new Point(randomX, randomY))) {
					isValidLocation = false;
				}	
			}if(!isValidLocation) {
				continue;
			}
			int rWidth = 0;
			int rHeight = 0;
			while(true) {
				rWidth = RandomGenerator.randomInteger(Display.HEIGHT/4) + 10;
				rHeight = RandomGenerator.randomInteger(Display.WIDTH/4) + 10;
				
				randomY = RandomGenerator.randomInteger(Display.HEIGHT/2);
				randomX = RandomGenerator.randomInteger(Display.WIDTH/2);
						
				for(Room testing:listOfRooms) {
					System.out.println("checking room " + rWidth +"  "+ rHeight +"  "+ randomX +"  "+ randomY);
					if(testing.getArea().intersects(new Rectangle(randomX, randomY, rWidth, rHeight))) {

						isValidLocation = false;
						break;
					}else {
						isValidLocation = true;
					}
				}
				if(isValidLocation) {
					break;
				}
			}
			Room e = new Room(g, randomX, randomY, rWidth, rHeight);
			listOfRooms.add(e);
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
		exits.add(new Node(this));
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
	public Room(Graphics g, int x, int y, int w, int h) {
		width = w;
		height = h;
		xLoc = x;
		yLoc = y;
		area = new Rectangle(x, y, w, h);
		exits = new ArrayList<Node>();
		exits.add(new Node(this));//null pointer exception here
		this.draw(g);
	}
	
	private void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xLoc, yLoc, width, height);
	}
	
	
	public void connectRooms(Graphics g){
		getExits().get(0).growNode(g);
	}
	
}
