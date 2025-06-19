package main;

public class Tile {
	
	public static final int WATERPERHEIGHT = 100; // 100 water = 1 height
	
	private int height;
	private int water;
	
	public Tile() {
		height = 0;
		water = 0;
	}
	
	public Tile(int height) {
		this.height = height;
		water = 0;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWater() {
		return water;
	}
	public void addWater(int w) {
		water += w;
	}
	public int removeWater(int w) { // returns water actually removed
		if (w > water) {
			int temp = water;
			water = 0;
			return temp;
		} else {
			water -= w;
			return w;
		}
	}
	public int getGroundWater() { // 1 tile stores 1 height worth of water before it reaches its surface
		return Math.min(water, WATERPERHEIGHT);
	}
	public int getSurfaceWater() {
		return Math.max(0, water - WATERPERHEIGHT);
	}
	public int evaporate() { // remove and return 1 surface water if it exists
		if (water > WATERPERHEIGHT) {
			water--;
			return 1;
		}
		else return 0;
	}
}
