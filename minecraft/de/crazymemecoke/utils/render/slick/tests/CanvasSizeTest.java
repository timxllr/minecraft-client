package de.crazymemecoke.utils.render.slick.tests;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.CanvasGameContainer;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.util.Log;

/**
 * Quick test to confirm canvas size is reported correctly
 * 
 * @author kevin
 */
public class CanvasSizeTest extends BasicGame {
	
	/**
	 * Create test
	 */
	public CanvasSizeTest() {
		super("Test");
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		System.out.println(container.getWidth() + ", " + container.getHeight());
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.Game#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	/**
	 * Entry point to the test
	 * 
	 * @param args The command line arguments passed in (none honoured)
	 */
	public static void main(String[] args) {
		try {
			CanvasGameContainer container = new CanvasGameContainer(
					new CanvasSizeTest());
			container.setSize(640,480);
			Frame frame = new Frame("Test");
			frame.setLayout(new GridLayout(1,2));
			frame.add(container);
			frame.pack();
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			frame.setVisible(true);
	
			container.start();
		} catch (Exception e) {
			Log.error(e);
		}
	}
}
