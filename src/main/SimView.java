package main;

import processing.core.PApplet;

// Window that draws a grid world for the simulation

public class SimView {
	// The world in question
	private World world;
	private Tile[][] tileMap;
	
	private PApplet parent;
	
	// Size of this view in pixels
	private int viewWidth, viewHeight;
	
	// Position of this view
	private int viewX, viewY;
	
	// Size of the world in tiles
	private int worldWidth, worldHeight;
	
	// Size of a tile in pixels
	private float tileWidth, tileHeight;
	
	public SimView(World world, PApplet parent, int x, int y, int width, int height) {
		this.world = world;
		tileMap = world.getTileMap();
		
		this.parent = parent;
		
		viewWidth = width;
		viewHeight = height;
		viewX = x;
		viewY = y;
		
		worldWidth = world.getWidth();
		worldHeight = world.getHeight();
		
		tileWidth = (float) viewWidth / worldWidth;
		tileHeight = (float) viewHeight / worldHeight;
	}
	
	public void draw() {
		parent.noStroke();
		parent.textSize(tileWidth / 2);
		parent.textAlign(PApplet.CENTER, PApplet.CENTER);
		for (int y = 0; y < worldHeight; y++) {
			for (int x = 0; x < worldWidth; x++) {
				// Draw colors from tile heights	
				parent.fill(getHeightColor(tileMap[y][x].getHeight()));
				parent.rect(viewX + x * tileWidth, viewY + y * tileHeight, tileWidth, tileHeight);
				
				// Draw surface water
				parent.fill(getWaterColor(tileMap[y][x].getSurfaceWater()));
				parent.rect(viewX + x * tileWidth, viewY + y * tileHeight, tileWidth, tileHeight);
				
				// Draw height values
				parent.fill(255, 0, 0);
				//parent.text(tileMap[y][x].getHeight(), viewX + x * tileWidth + tileWidth / 2, viewY + y * tileHeight + tileHeight / 2);
				//parent.text(world.getWaterHeight(x, y), viewX + x * tileWidth + tileWidth / 2, viewY + y * tileHeight + tileHeight / 2);
			}
		}
	}
	
	private int getHeightColor(int h) {
		float percentage = (float) h / WorldGenerator.MAXHEIGHT; // h is percentage % of max height
		int lowColor  = parent.color(0);                      // black
	    int highColor = parent.color(222, 184, 135);          // brown
	    return parent.lerpColor(lowColor, highColor, percentage);
	}
	
	private int getWaterColor(int w) { // Calculate color of surface water based on depth
		int shallow = parent.color(64, 103, 245); 
		int deep = parent.color(0, 0, 140);
		int maxWater = (WorldGenerator.MAXHEIGHT * Tile.WATERPERHEIGHT) / 2; // max water that still makes a color difference
		
		if (w <= Tile.WATERPERHEIGHT) {
			float alpha = ((float) w * 255) / Tile.WATERPERHEIGHT;
			return parent.color(64, 103, 245, alpha);
		} else {
			return parent.lerpColor(shallow, deep, Math.min((float)(w - Tile.WATERPERHEIGHT) / (maxWater - Tile.WATERPERHEIGHT), 1));
		}
	}
}