package main;

import processing.core.PApplet;

public class SimRunner extends PApplet {
	
	// Size of world in tiles
	private static final int WORLDWIDTH = 25;
	private static final int WORLDHEIGHT = 25;
	
	// Milliseconds per update tick
	private static final int MILLISPERTICK = 20;
	private long lastTime = 0;
	 
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
		worldGen = new WorldGenerator(this);
		worldGen.generateNoiseTerrain(world.getTileMap());
		simView = new SimView(world, this, width - height, 0, height, height);
	}
	
	public void draw() {
		if (millis() - lastTime >= MILLISPERTICK) {
			lastTime = millis();
			world.update();
		}
		simView.draw();
	}
}