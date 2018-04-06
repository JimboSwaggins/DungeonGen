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
	
	public void setParent(Room r) {
		this.parent = r;
	}
	
	public void buildNode(Graphics g) {//write to this new method
		for (Room room: Room.listOfRooms){
			 int minXX = parent.getX() > room.getX() ? parent.getX() : room.getX();
			 int maxXX = parent.getMaxX() > room.getMaxX() ? room.getMaxX() : parent.getMaxX();
			 int minYY = parent.getY() > room.getY() ? parent.getY() : room.getY();
			 int maxYY = parent.getMaxY() > room.getMaxY() ? room.getMaxY() : parent.getMaxY();
			 
			 int nxLoc = -1;
			 int nyLoc = -1;
			 boolean createNode = false;
			 
			 if(minXX <= maxXX){//if rooms have x range where path can be
				this.xLoc = (minXX + maxXX)/2; //xlocs are same
				nxLoc = (minXX + maxXX)/2;
				
				if(this.parent.getY()==room.getY()) {
					createNode = false;
				}//else put lines at edges of rooms
				else if(Math.abs(this.parent.getY()-room.getMaxY()) < Math.abs(this.parent.getMaxY()-room.getY())){
					this.yLoc = this.parent.getY();
					 nyLoc = room.getMaxY();
					 createNode = true;
				}else {
					this.yLoc = this.parent.getMaxY();
					nyLoc = room.getY();
					createNode = true;
				}
				
				for (Room roomCheck: Room.listOfRooms) {
					if((this.yLoc <= roomCheck.getY() && roomCheck.getMaxY() <= nyLoc) || (nyLoc <= roomCheck.getY() && roomCheck.getMaxY() <= this.yLoc)) {
						createNode = false;
					}
				}
				 
			}else if(minYY <= maxYY){
				this.yLoc = (minYY + maxYY)/2;
				nyLoc = (minYY+maxYY)/2;
				
				if(this.parent.getX()==room.getX()){
					createNode = false;
				}//else put the line on edges of box
				else if(Math.abs(this.parent.getX()-room.getMaxX()) < Math.abs(this.parent.getMaxX()-room.getX())){
						this.xLoc = this.parent.getX();
						nxLoc = room.getMaxX();
						createNode = true;
				}else {
						this.xLoc = this.parent.getMaxX();
						nxLoc = room.getX();
						createNode = true;
				}
				
				for (Room roomCheck: Room.listOfRooms) {//make sure line does not intersect
					if((this.xLoc <= roomCheck.getX() && roomCheck.getMaxX() <= nxLoc) || (nxLoc <= roomCheck.getX() && roomCheck.getMaxX() <= this.xLoc)) {
						createNode = false;
					}
				}
			}
			 if(createNode) {
				Node n = new Node(room);
				n.xLoc = nxLoc;
				n.yLoc = nyLoc;
				connectNodes(n, g);
			}
		}
	}
		
	public Node(Room connection) {
		parent = connection;
		nodeList.add(this);
		parent.exits.add(this);//null pointer exception
	}
}