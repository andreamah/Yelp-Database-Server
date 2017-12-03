package ca.ece.ubc.cpen221.mp5;


public class Centroid {
	
	private double y;
	private double x;
	// Abstraction function: x and y represent the coordinates of
	// the centroid, so the centroid is at (x,y)
	
	
	public Centroid(double y, double x) {
		this.y = y; this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

}
