package main;

public class Vector {
	public double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double dotProduct(Vector other) {
		return this.x * other.x + this.y * other.y;
	}
	
	// Set length to 1
	public void normalize() {
		double magnitude = Math.hypot(x, y);
		x /= magnitude;
		y /= magnitude;
	}
}
