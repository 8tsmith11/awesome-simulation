package main;

<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
=======
import processing.core.PApplet;
>>>>>>> 6612f7093746411e01ba3afc73f3992fb52fc7d8

public class SimRunner extends PApplet {
	
	// Size of world in tiles
	private static final int WORLDWIDTH = 100;
	private static final int WORLDHEIGHT = 100;
	
	// Milliseconds per update tick
	private static final int MILLISPERTICK = 1;
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
		
<<<<<<< HEAD
		// Screen dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		
		// View initializations
		SimView simView = new SimView(world);
		
		// Frame setup
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		Frame frame = new Frame(gd.getDefaultConfiguration());
		frame.setLayout(null);
		frame.setUndecorated(true);
		gd.setFullScreenWindow(frame);
		frame.setVisible(true);
		frame.requestFocus();
		
		// View setups
		frame.add(simView);
		simView.setBounds(width - height, 0, height, height);
		
		// Simulation Loop
		long lastTime = System.nanoTime();
		while (true) {
			long thisTime = System.nanoTime();
			if (thisTime - lastTime > TICKNANOS) {
				lastTime = thisTime;
				world.update();
				simView.repaint();
			}
=======
		simView = new SimView(world, this, width - height, 0, height, height);
	}
	
	public void draw() {
		if (millis() - lastTime >= MILLISPERTICK) {
			lastTime = millis();
			world.update();
>>>>>>> 6612f7093746411e01ba3afc73f3992fb52fc7d8
		}
		simView.draw();
	}
}