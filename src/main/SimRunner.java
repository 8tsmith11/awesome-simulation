package main;

import processing.core.PApplet;

public class SimRunner extends PApplet {
	
	// Size of world in tiles
	private static final int WORLDWIDTH = 100;
	private static final int WORLDHEIGHT = 100;
	
	private World world;
	private WorldGenerator worldGen;
	
	private SimView simView;

	public static void main(String[] args) {
		PApplet.main("main.SimRunner");
	}
	
	public void settings() {
		fullScreen();
	}
	
	public void setup() {
		world = new World(WORLDWIDTH, WORLDHEIGHT);
		worldGen = new WorldGenerator(this, World.MAXHEIGHT);
		worldGen.generateNoiseTerrain(world.getTileMap());
		
		simView = new SimView(world, this, width - height, 0, height, height);
	}
	
	public void draw() {
		simView.draw();
	}
}