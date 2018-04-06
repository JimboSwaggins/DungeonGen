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
			 
			 if(minXX <= maxXX){//if rooms have x range where path can be
				this.xLoc = (minXX + maxXX)/2; //xlocs are same
				n.xLoc = (minXX + maxXX)/2;
				
				if(this.parent.getY()==room.getY()) {//if rooms are the same get rid of line
					this.yLoc = 0;
					this.xLoc = 0;
					n.yLoc = 0;
					n.xLoc = 0; 
				}//else put lines at edges of rooms
				else if(Math.abs(this.parent.getY()-room.getMaxY()) < Math.abs(this.parent.getMaxY()-room.getY())){
					this.yLoc = this.parent.getY();
					 n.yLoc = room.getMaxY();
				}else {
					this.yLoc = this.parent.getMaxY();
					n.yLoc = room.getY();
				}
				
				for (Room roomCheck: Room.listOfRooms) {
					if(this.yLoc <= roomCheck.getY() && roomCheck.getMaxY() <= n.yLoc) {
						this.yLoc = 0;
						this.xLoc = 0;
						n.yLoc = 0;
						n.xLoc = 0; 
					}
				}
				 
			}else if(minYY <= maxYY){
				this.yLoc = (minYY + maxYY)/2;
				n.yLoc = (minYY+maxYY)/2;
				
				if(this.parent.getX()==room.getX()){
					//if rooms are the same get rid of the line
					this.yLoc = 0;
					this.xLoc = 0;
					n.yLoc = 0;
					n.xLoc = 0; 
				}//else put the line on edges of box
				else if(Math.abs(this.parent.getX()-room.getMaxX()) < Math.abs(this.parent.getMaxX()-room.getX())){
						this.xLoc = this.parent.getX();
						 n.xLoc = room.getMaxX();
				}else {
						this.xLoc = this.parent.getMaxX();
						n.xLoc = room.getX();
				}
				
				for (Room roomCheck: Room.listOfRooms) {//make sure line does not intersect
					if((this.xLoc <= roomCheck.getX() && roomCheck.getMaxX() <= n.xLoc) || (n.xLoc <= roomCheck.getX() && roomCheck.getMaxX() <= this.xLoc)) {
						this.yLoc = 0;
						this.xLoc = 0;
						n.yLoc = 0;
						n.xLoc = 0; 
					}
				}
			}else{
				this.yLoc = 0;
				this.xLoc = 0;
				n.yLoc = 0;
				n.xLoc = 0;
			 }
		}
		return n;
	}
	/**
	 * ensure every room creates a node to find a pair. 
	 * each room should pair with one other. 
	 * if a room has <2 nodes or 2 connections with another room,
	 * 	 remove one of the connections or create new node to find another path. 
	 */
	
	
	public void growNode(Graphics g) {//obsolete
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
	
	
	public void suicide() {
		this.parent.exits.remove(this);
		this.parent = null;
		this.xLoc = 0;
		this.yLoc = 0;
		nodeList.remove(this);
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Node(Room connection) {
		parent = connection;
		nodeList.add(this);
		parent.exits.add(this);//null pointer exception
	}
}