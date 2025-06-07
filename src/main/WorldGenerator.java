package main;

import java.awt.geom.Point2D;

public class WorldGenerator {
	
	/* Initializes the tiles in world */
	/* with heights from Perlin noise */
	public static void generateTerrain(Tile[][] world) {
		
	}
	
	// Initialize a tile matrix to be flat
	public static void generateFlatTerrain(Tile[][] world) {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				world[y][x] = new Tile();
				world[y][x].setHeight(0);
			}
		}
	}
}
