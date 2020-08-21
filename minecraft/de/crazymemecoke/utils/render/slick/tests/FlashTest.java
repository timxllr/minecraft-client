package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.Color;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Image;
import de.crazymemecoke.utils.render.slick.Input;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * A test for image flashes
 *
 * @author kevin
 */
public class FlashTest extends BasicGame {
	/** The TGA image loaded */
	private Image image;
	/** True if the image is rendered flashed */
	private boolean flash;
	/** The container for the test */
	private GameContainer container;
	
	/**
	 * Create a new image rendering test
	 */
	public FlashTest() {
		super("Flash Test");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		
		image = new Image("testdata/logo.tga");
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		g.drawString("Press space to toggle",10,50);
		if (flash) {
			image.draw(100,100);
		} else {
			image.drawFlash(100,100,image.getWidth(), image.getHeight(), new Color(1,0,1f,1f));
		}
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
			AppGameContainer container = new AppGameContainer(new FlashTest());
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
		if (key == Input.KEY_SPACE) {
			flash = !flash;
		}
		if (key == Input.KEY_ESCAPE) {
			container.exit();
		}
	}
}
