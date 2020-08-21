package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.Color;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * Test to view the effects of antialiasing on cirles
 *
 * @author kevin
 */
public class AntiAliasTest extends BasicGame {

	/**
	 * Create the test
	 */
	public AntiAliasTest() {
		super("AntiAlias Test");
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		container.getGraphics().setBackground(Color.green);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta) throws SlickException {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.Game#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		g.setColor(Color.red);
		g.drawOval(100,100,100,100);
		g.fillOval(300,100,100,100);
		g.setAntiAlias(false);
		g.setColor(Color.red);
		g.drawOval(100,300,100,100);
		g.fillOval(300,300,100,100);
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed to the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new AntiAliasTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
