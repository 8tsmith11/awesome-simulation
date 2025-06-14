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
		for (int y = 0; y < worldHeight; y++) {
			for (int x = 0; x < worldWidth; x++) {
				// Draw grid	
				// Darker color cell = lower height
				parent.fill((tileMap[y][x].getHeight() * 255) / 9);
				parent.stroke(0);
				parent.strokeWeight(2);
				parent.rect(viewX + x * tileWidth, viewY + y * tileHeight, tileWidth, tileHeight);
				
				
				// Draw heights
				parent.textSize(tileWidth / 2);
				parent.textAlign(PApplet.CENTER, PApplet.CENTER);
				parent.fill(255, 0, 0);
				parent.text(tileMap[y][x].getHeight(), viewX + x * tileWidth + tileWidth / 2, viewY + y * tileHeight + tileHeight / 2);
			}
		}
	}
}