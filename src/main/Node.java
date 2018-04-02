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
	
	
	public void growNode() {
		int whichSide = RandomGenerator.randomInteger(3);
		switch(whichSide) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3: 
			break;
		default:
			System.out.println("Error, cannot find a side");
			break;
		}
	}
	public Node(Room connection) {
		parent = connection;
		parent.exits.add(this);
	}
}
