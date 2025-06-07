package main;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public class SimRunner {
	
	// Size of world in tiles
	private static final int WORLDWIDTH = 10;
	private static final int WORLDHEIGHT = 10;
	
	// Nanoseconds per tick
	private static final long TICKNANOS = 1_000_000_000;

	public static void main(String[] args) {
		World world = new World(WORLDWIDTH, WORLDHEIGHT);
		
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
		}
	}
}