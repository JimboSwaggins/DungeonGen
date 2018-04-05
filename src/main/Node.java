package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Node {
	private Room parent;

	public static ArrayList<Node> nodeList = new ArrayList<Node>();
	
	private boolean isConnected = false;
	
	private int xLoc;
	private int yLoc;
	
	public Room getParent() {return this.parent;}
	public int getXLoc() {return this.xLoc;}
	public int getYLoc() {return this.yLoc;}
	public boolean isConnected() {return this.isConnected;}
	
	/**
	 * Draws a line between this node and another node.
	 * @param target The node that will connect to this node.
	 * @param g The Graphics unit that will be utilized.
	 */
	
	public void connectNodes(Node target, Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(this.xLoc, this.yLoc, target.getXLoc(), target.getYLoc());
		this.isConnected = true;
	}
	public Node addNode(Graphics g) {
		/*int whichSide = RandomGenerator.randomInteger(3);
		int xy = -1; //0 is x axis, 1 is y axis
		switch(whichSide) {
			case 0: xy = 1; break;
			case 1: xy = 0; break;
			case 2: xy = 1; break;
			case 3: xy = 0; break;
			default: System.out.println("Error, cannot find a side"); break;
		}*/
		Node n = null;
		 for (Room room: Room.listOfRooms){
			 n = new Node(room);
			 int minXX = n.parent.getX() > room.getX() ? n.parent.getX() : room.getX();
			 int maxXX = n.parent.getMaxX() > room.getMaxX() ? room.getMaxX() : n.parent.getMaxX();
			 int minYY = n.parent.getY() > room.getY() ? n.parent.getY() : room.getY();
			 int maxYY = n.parent.getMaxY() > room.getMaxY() ? room.getMaxY() : n.parent.getMaxY();
			 
			 if(minXX <= maxXX){
				 n.xLoc = minXX;
				 n.yLoc = n.parent.getMaxY();
			//for loop to check if other rooms intersect with this
			 }else if(minYY <= maxYY){
				 n.yLoc = minYY;
				 n.xLoc = n.parent.getMaxX();
			 }else{
				 //here, the rooms have no matching stuff
				 //if this is true for all other rooms we have a problem but for now dw
			 }
		 }
		 return n;
	}
	
	public void growNode(Graphics g) {
		/*int whichSide = RandomGenerator.randomInteger(3);
		int xy = -1; //0 is x axis, 1 is y axis
		switch(whichSide) {
			case 0: xy = 1; break;
			case 1: xy = 0; break;
			case 2: xy = 1; break;
			case 3: xy = 0; break;
			default: System.out.println("Error, cannot find a side"); break;
		}*/
		
		 for (Room room: Room.listOfRooms){
			 int minXX = parent.getX() > room.getX() ? parent.getX() : room.getX();
			 int maxXX = parent.getMaxX() > room.getMaxX() ? room.getMaxX() : parent.getMaxX();
			 int minYY = parent.getY() > room.getY() ? parent.getY() : room.getY();
			 int maxYY = parent.getMaxY() > room.getMaxY() ? room.getMaxY() : parent.getMaxY();
			 
			 if(minXX <= maxXX){
				 this.xLoc = minXX;
				 this.yLoc = parent.getMaxY();
				 Node n = addNode(g);
				 connectNodes(n, g);
			//for loop to check if other rooms intersect with this
			 }else if(minYY <= maxYY){
				 this.yLoc = minYY;
				 this.xLoc = parent.getMaxX();
				 Node n = addNode(g);
				 connectNodes(n, g);
			 }else{
				 //here, the rooms have no matching stuff
				 //if this is true for all other rooms we have a problem but for now dw
			 }
		 }
	}
	
	public Node(Room connection) {
		parent = connection;
		nodeList.add(this);
		parent.exits.add(this);//null pointer exception
	}
}

//start with node
//draw line to closest other node without current connection
///exception: path intersects room mid-path
///if so, find a path that will not intersect

//check if line intersects another room
//alt: find x or y coordinate that will only intersect with one other room
