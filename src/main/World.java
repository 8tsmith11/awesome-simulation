package main;

import java.awt.Point;
import java.util.Arrays;

public class World {
	// World size in tiles
	private int width, height;
	private Tile[][] tileMap;
	private int[][] flow;
	private static final float FLOWRATE = .5f; // percentage of a tiles water that can flow in one tick
	private int waterVapor;
	private int vaporCapacity;
	private static final int TILECAPACITY = 50; // vapor capacity each tile will contribute to total
	private static final int INITIALVAPORMULTIPLIER = 4; // the world starts with this many vaporcapacities worth of vapor
	private boolean isRaining;
	
	 
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		tileMap = new Tile[height][width];
		flow = new int[height][width];
		vaporCapacity = width * height * TILECAPACITY;
		waterVapor = vaporCapacity * INITIALVAPORMULTIPLIER;
		isRaining = false;
	}
	
	public void update() {
		waterFlowLoop();
		if (waterVapor > vaporCapacity) {
			isRaining = true;
		}
		
		if (isRaining) {
			rainLoop();
		} else {
			evaporationLoop();
		}
	}
	private void rainLoop() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (waterVapor == 0) {
					isRaining = false;
					return;
				} else {
					waterVapor--;
					tileMap[y][x].addWater(1);
				}
			}
		}
	}
	private void evaporationLoop() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (waterVapor > vaporCapacity) {
					isRaining = true;
					return;
				} else {
					waterVapor += tileMap[y][x].evaporate();
				}
			}
		}
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
	
	private void waterFlowLoop() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tileMap[y][x].getSurfaceWater() > 0) {
					waterFlow(x, y);
				}
			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileMap[y][x].addWater(flow[y][x]);
				flow[y][x] = 0;
			}
		}
	}
	
	private void waterFlow(int x, int y) {
		Point[] neighbors = getDownhillNeighbors(x, y); // all downhill neighbors
		if (neighbors.length == 0) {
			return;
		}
		int totalSent = 0;
		
		int waterHeight = getWaterHeight(x, y);
		for (Point n : neighbors) {
			int dh = waterHeight - getWaterHeight(n.x, n.y); // height difference between tile and neighbor
			int water = Math.min(dh, tileMap[y][x].getSurfaceWater()); // portion of dh that is water
			int sent = (int)(water * FLOWRATE / neighbors.length); // Water sent to neighbor
			flow[n.y][n.x] += sent;
			flow[y][x] -= sent;
			totalSent += sent;
		}
		 
		int remainder = tileMap[y][x].getSurfaceWater() - totalSent; // water that wasn't sent
		for (Point n : neighbors) {
			if (remainder > 0) {
				flow[n.y][n.x] += 1;
				flow[y][x] -= 1;
				remainder--;
			}
		}
	}
	public int getWaterHeight(int x, int y) { // Total height in water including terrain
		return getWaterHeight(tileMap[y][x]);
	}
	private int getWaterHeight(Tile tile) {
		return tile.getHeight() * Tile.WATERPERHEIGHT + tile.getSurfaceWater();
	}
	
	/* Returns coordinates of valid neighbors of tile at (x, y) */
	/* 2 <= returned array length <= 4 */
	private Point[] getNeighbors(int x, int y) {
		Point[] neighbors = new Point[4];
		int count = 0;
		
		if (x > 0) {
			neighbors[count] = new Point(x - 1, y);
			count++;
		}
		if (y > 0) {
			neighbors[count] = new Point(x, y - 1);
			count++;
		}
		if (x < width - 1) {
			neighbors[count] = new Point(x + 1, y);
			count++;
		}
		if (y < height - 1) {
			neighbors[count] = new Point(x, y + 1);
			count++;
		}
		
		return Arrays.copyOf(neighbors, count);
	}
	/* Get neighbors with a lower water height */
	private Point[] getDownhillNeighbors(int x, int y) {
		Point[] neighbors = getNeighbors(x, y);
		Point[] downhillNeighbors = new Point[4];
		int count = 0;
		
		for (Point n : neighbors) {
			if (getWaterHeight(n.x, n.y) < getWaterHeight(x, y)) {
				downhillNeighbors[count] = n;
				count++;
			}
		}
		
		return Arrays.copyOf(downhillNeighbors, count);
	}
}


