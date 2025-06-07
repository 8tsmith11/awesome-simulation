package main;

public class Noise {
	
	private static Vector[][] vectors;
	
	public static void generateVectors(int size) {
		// Grid of unit vectors with one at each corner of the noise map grid
		vectors = new Vector[size + 1][size + 1];
		
		for (int y = 0; y < vectors.length; y++) {
			for (int x = 0; x < vectors[0].length; x++) {
				
				// Generate random 2D vector
				Vector vector = new Vector(Math.random() - 0.5, Math.random() - 0.5);
				
				// Normalize vector so length = 1
				vector.normalize();
			
				vectors[y][x] = vector;
			}
		}
	}
	
	public static double sample(double x, double y) {
		return 0;
	}
}