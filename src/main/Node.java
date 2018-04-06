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
		Node n = null;
		 for (Room room: Room.listOfRooms){
			 n = new Node(room);
			 int minXX = this.parent.getX() > room.getX() ? this.parent.getX() : room.getX();
			 int maxXX = this.parent.getMaxX() > room.getMaxX() ? room.getMaxX() : this.parent.getMaxX();
			 int minYY = this.parent.getY() > room.getY() ? this.parent.getY() : room.getY();
			 int maxYY = this.parent.getMaxY() > room.getMaxY() ? room.getMaxY() : this.parent.getMaxY();
			 
			 if(minXX <= maxXX){
				 this.xLoc = (minXX + maxXX)/2;
				 this.yLoc = (this.parent.getY() + this.parent.getMaxY())/2;
				 n.xLoc = (minXX + maxXX)/2;
				 n.yLoc = (room.getY()+room.getMaxY())/2;
			//for loop to check if other rooms intersect with this
			 }else if(minYY <= maxYY){
				 this.yLoc = (minYY + maxYY)/2;
				 this.xLoc = (this.parent.getX() + this.parent.getMaxX())/2;
				 n.yLoc = (minYY+maxYY)/2;
				 n.xLoc = (room.getX()+room.getMaxX())/2;
				 //n.xLoc = n.parent.getMaxX();
			 }else{
				 this.yLoc = 0;
				 this.xLoc = 0;
				 n.yLoc = 0;
				 n.xLoc = 0;
				 //here, the rooms have no matching stuff
				 //if this is true for all other rooms we have a problem but for now dw
			 }
		 }
		 return n;
	}
	/*
	 * Cleared some things up in code, minimal changes. 
	 * In progress:
	- make paths between nodes straight
	- minimize nodes rather than maximizing
	- ensure every room creates a node to find a pair. 
		each room should pair with one other. 
		if a room doesn't end up with 2 nodes or a room 
		has 2 connections with another room, remove one of the connections
	 	/ create new node to find another path. 
	should have done by fri.
	 */
	
	
	public void growNode(Graphics g) {
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
