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

	
	
	/**
	 * Returns a rectangle with the same area as the room. Can be used to check for intersections.
	 * @return A rectangle with the same area as the room.
	 */
	public Rectangle getArea() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	
	/**
	 * Returns x-coordinate of the right side of the room.
	 * @return x-coordinate of the right side of the room.
	 */
	public int getMaxX() {
		return this.xLoc + this.width;
	}
	
	
	/**
	 * Returns the y-coordinate of the bottom of the room.
	 * @return the y-coordinate of the bottom of the room.
	 */
	public int getMaxY() {
		return this.yLoc + this.height;
	}
	
	/**
	 * Returns the upper left corner of the rectangle as a point.
	 * @return the upper left corner of the rectangle as a point.
	 */
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
			
			int rWidth = 0;
			int rHeight = 0;
			while(true) {
				rWidth = RandomGenerator.randomInteger(Display.HEIGHT/4) + 10;
				rHeight = RandomGenerator.randomInteger(Display.WIDTH/4) + 10;
				
				randomY = RandomGenerator.randomInteger(Display.HEIGHT/2);
				randomX = RandomGenerator.randomInteger(Display.WIDTH/2);
						
				for(Room testing:listOfRooms) {
					//System.out.println("checking room " + rWidth +"  "+ rHeight +"  "+ randomX +"  "+ randomY);
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
			//e.hallwayCheck(g);
			listOfRooms.add(e);
			
		}
	}
	
	
	/*private void hallwayCheck(Graphics g) {
		if(this.width * 2 < this.height || this.height * 2 < this.width&& this.height + this.width > 150) {
			boolean tall = this.height > this.width ? true : false;
			boolean io = RandomGenerator.randomBoolean();
				if(tall) {
					if(io) {
						hallRooms(g, this.getY(), this.getMaxY(), this.xLoc, false, false);
					}if(!io) {
						hallRooms(g, this.getY(), this.getMaxY(), this.xLoc, true, false);
					}
				}else {
					if(io) {
						hallRooms(g, this.getX(), this.getMaxX(), this.yLoc, false, true);
					}if(!io) {
						hallRooms(g, this.getX(), this.getMaxX(), this.yLoc, true, true);
					}
				}
		}
	}*/
	
	
	
	public void populateRoom(int numOfFurniture) {
		for(int i = 0; i < numOfFurniture;i++) {
			
		}
	}
	public void hallRooms(Graphics g, int starta, int enda, int xory, boolean addorSub, boolean atX) {
	
		for(int i = starta; i < enda; i+= 30) {
			boolean SENTINEL = false;
			int solid = addorSub ? xory + 30 : xory - 30;
			for(Room e: listOfRooms) {
				if(new Rectangle(solid, i, 15, 15).intersects(e.getArea())){
					SENTINEL = true;
					break;
				}			
			}
			if(i + 15 > enda) {
				SENTINEL = true;
			}
			
			if(!SENTINEL) {
				if(!atX) {
					listOfRooms.add(new Room(g, solid, i, 15, 15));
				}else {
					listOfRooms.add(new Room(g, i, solid, 15, 15));
				}
			}else {
				SENTINEL = false;
				continue;
			}
		}
	}
	public static void drawAllRooms(Graphics g) {
		for(Room e: listOfRooms) {
			e.draw(g);
		}
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
		exits = new ArrayList<Node>();
		exits.add(new Node(this));//null pointer exception here
		this.draw(g);
	}
	
	private void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xLoc, yLoc, width, height);
	}
	
	
	public void connectRooms(Graphics g){
		//getExits().get(0).growNode(g);

		getExits().get(0).buildNode(g);
	}
	
}