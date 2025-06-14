package main;

import processing.core.PApplet;

public class WorldGenerator {
	
	private PApplet p;
	
	// Maximum height for noise terrain
	private int maxHeight;
	
	public WorldGenerator(PApplet parent, int maxHeight) {
		p = parent;
		this.maxHeight = maxHeight;
	}
	
	/* Initializes the tiles in world */
	/* with heights from Perlin noise */
	public void generateNoiseTerrain(Tile[][] world) {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				float noiseSample = p.noise(x * 0.1f, y * 0.1f);
				world[y][x] = new Tile( (int) (noiseSample * (maxHeight + 1)));
			}
		}
	}
	
	// Initialize a tile matrix to be flat
	public void generateFlatTerrain(Tile[][] world) {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				world[y][x] = new Tile(0);
			}
		}
	}
}
