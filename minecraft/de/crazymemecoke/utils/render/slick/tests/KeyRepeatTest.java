package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Input;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * A test for basic image rendering
 *
 * @author kevin
 */
public class KeyRepeatTest extends BasicGame {
	/** The number of times the key pressed event has been fired */
	private int count;
	/** The input sub system */
	private Input input;
	
	/**
	 * Create a new image rendering test
	 */
	public KeyRepeatTest() {
		super("KeyRepeatTest");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		input = container.getInput();
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		g.drawString("Key Press Count: "+count, 100,100);
		g.drawString("Press Space to Toggle Key Repeat", 100,150);
		g.drawString("Key Repeat Enabled: "+input.isKeyRepeatEnabled(), 100,200);
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
			AppGameContainer container = new AppGameContainer(new KeyRepeatTest());
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
		count++;
		if (key == Input.KEY_SPACE) {
			if (input.isKeyRepeatEnabled()) {
				input.disableKeyRepeat();
			} else {
			}
		}
	}
}
