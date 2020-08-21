package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.Color;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Image;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * A test to demonstrate world clipping as opposed to screen clipping
 *
 * @author kevin
 */
public class AlphaMapTest extends BasicGame {
	/** The alpha map being applied */
	private Image alphaMap;
	/** The texture to apply over the top */
	private Image textureMap;
	
	/**
	 * Create a new tester for the clip plane based clipping
	 */
	public AlphaMapTest() {
		super("AlphaMap Test");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		alphaMap = new Image("testdata/alphamap.png");
		textureMap = new Image("testdata/grass.png");
		container.getGraphics().setBackground(Color.black);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.Game#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.clearAlphaMap();
		g.setDrawMode(Graphics.MODE_NORMAL);
		textureMap.draw(10,50);
		g.setColor(Color.red);
		g.fillRect(290,40,200,200);
		g.setColor(Color.white);
		// write only alpha
		g.setDrawMode(Graphics.MODE_ALPHA_MAP);
		alphaMap.draw(300,50);
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
		textureMap.draw(300,50);
		g.setDrawMode(Graphics.MODE_NORMAL);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new AlphaMapTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
