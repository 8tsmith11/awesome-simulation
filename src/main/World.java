package main;

public class World {
	// World size in tiles
	private int width, height;
	private Tile[][] tileMap;
	 
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		tileMap = new Tile[height][width];
		WorldGenerator.generateFlatTerrain(tileMap);
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
