package main;

public class Node {
	private Room parent;

	private int xLoc;
	private int yLoc;
	
	public Node(Room connection) {
		parent = connection;
	}
}
