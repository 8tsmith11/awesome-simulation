package main;

import processing.core.PApplet;

public class WorldGenerator {
	
	// Maximum height for noise terrain
	public static final int MAXHEIGHT = 10;
	
	private static final float NOISESCALE = 0.1f;
	
	private PApplet p;
	
	public WorldGenerator(PApplet parent) {
		p = parent;
	}
	
	/* Initializes the tiles in world */
	/* with heights from Perlin noise */
	public void generateNoiseTerrain(Tile[][] world) {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				float noiseSample = p.noise(x * NOISESCALE, y * NOISESCALE);
				world[y][x] = new Tile( (int) (noiseSample * (MAXHEIGHT + 1)));
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
