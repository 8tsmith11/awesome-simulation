package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/* Window that draws a grid world for the simulation */

public class SimView extends Canvas {
	private static final long serialVersionUID = 1L;
	
	
	/* The world in question */
	private World world;
	
	public SimView(World world) {
		this.world = world;
	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics.create();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int worldHeight = world.getHeight();
		int worldWidth = world.getWidth();
		int tileWidth = getWidth() / worldWidth;
		
		g.setColor(Color.RED);
		for (int y = 0; y < worldHeight; y++) {
			for (int x = 0; x < worldWidth; x++) {
				g.drawString("" + world.getTileMap()[y][x].getHeight(), x * tileWidth, y * tileWidth);
			}
		}
	}	
}