package main;

public class World {
	// World size in tiles
	private int width, height;
	private Tile[][] tileMap;
	 
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		tileMap = new Tile[height][width];
		buildTerrain(tileMap);
	}
	
	public void update() {
		
	}
	
	/* Initializes the tiles in map */
	/* with heights from Perlin noise */
	private void buildTerrain(Tile[][] map) {
		float noiseScale = 0.1f;    
		int maxHeight = 50;
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[0].length; c++) {
				
			}
		}
	}
}
