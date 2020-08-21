package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.Color;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Image;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * A test for transparent colour specification
 *
 * @author kevin
 */
public class TransparentColorTest extends BasicGame {
	/** The image we're currently displaying */
	private Image image;
	/** The image we're currently displaying */
	private Image timage;
	
	/**
	 * Create a new image rendering test
	 */
	public TransparentColorTest() {
		super("Transparent Color Test");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		image = new Image("testdata/transtest.png");
		timage = new Image("testdata/transtest.png",new Color(94,66,41,255));
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		g.setBackground(Color.red);
		image.draw(10,10);
		timage.draw(10,310);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new TransparentColorTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
	}
}
