package main;

import java.awt.geom.Point2D;

public class WorldGenerator {
	public static void generateNoiseMap(int size) {
		
		// Grid of unit vectors with one at each corner of the noise map grid
		Point2D.Double[][] vectors = new Point2D.Double[size + 1][size + 1];
		
		for (int y = 0; y < vectors.length; y++) {
			for (int x = 0; x < vectors[0].length; x++) {
				
				// Generate random 2D vector
				Point2D.Double vector = new Point2D.Double(Math.random() - 0.5, Math.random() - 0.5);
				
				// Normalize vector so length = 1
				double magnitude = Math.hypot(vector.x, vector.y);
				vector.x /= magnitude;
				vector.y /= magnitude;
				
				vectors[y][x] = vector;
			}
		}
	}
	
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
