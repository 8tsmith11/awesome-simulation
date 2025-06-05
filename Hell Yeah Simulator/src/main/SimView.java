package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/* Window that draws a grid world for the simulation */

public class SimView extends Canvas {
	private static final long serialVersionUID = 1L;
	
	
	/* The world in question */
	private World world;
	
	public SimView(World world) {
		this.world = world;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}	
}