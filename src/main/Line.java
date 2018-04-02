package main;

import java.awt.Point;

public class Line {
	private Point startPoint;
	private Point endPoint;
	
	public Point getStart() {
		return startPoint;
	}
	
	public Point getEnd() {
		return endPoint;
	}
	
	public double getStartX() {
		return startPoint.getX();
	}
	
	public double getStartY() {
		return startPoint.getY();
	}
	
	public double getEndX() {
		return endPoint.getX();
	}
	
	public double getEndY() {
		return endPoint.getY();
	}
	
	public Line(int x1, int y1, int x2, int y2) {
		startPoint = new Point(x1, y1);
		endPoint = new Point(x2, y2);
	}
}
