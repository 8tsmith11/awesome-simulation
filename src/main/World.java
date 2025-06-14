package main;

public class World {
	public static final int MAXHEIGHT = 100;
	
	// World size in tiles
	private int width, height;
	private Tile[][] tileMap;
	 
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		tileMap = new Tile[height][width];
	}
	
	public void update() {
		
	}
	
	public Tile[][] getTileMap() {
		return tileMap;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
