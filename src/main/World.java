package main;

public class World {
	// World size in tiles
	private int width, height;
	private Tile[][] tileMap;
	 
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		tileMap = new Tile[height][width];
	}
	
	public void update() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tileMap[y][x].getSurfaceWater() > 0) {
					waterFlow(x, y);
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
	
	private void waterFlow(int x, int y) { // Handles water flow mechanics
		int waterHeight = getWaterHeight(x, y);
		Tile min = null;
		if (x > 0) {
			min = tileMap[y][x - 1];
		}
		if (y > 0 && (min == null || getWaterHeight(x, y - 1) < getWaterHeight(min))) {
			min = tileMap[y - 1][x];
		}
		if (x < width - 1 && (min == null || getWaterHeight(x + 1, y) < getWaterHeight(min))) {
			min = tileMap[y][x + 1];
		}
		if (y < height - 1 && (min == null || getWaterHeight(x, y + 1) < getWaterHeight(min))) {
			min = tileMap[y + 1][x];
		}
		
		if (getWaterHeight(min) < waterHeight) {
			System.out.println("hi");
			min.addWater(tileMap[y][x].removeWater(10));
		}
	}
	private int getWaterHeight(int x, int y) { // Total height in water including terrain
		return getWaterHeight(tileMap[y][x]);
	}
	private int getWaterHeight(Tile tile) {
		return tile.getHeight() * Tile.WATERPERHEIGHT + tile.getSurfaceWater();
	}
}
