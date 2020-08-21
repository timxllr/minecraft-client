package de.crazymemecoke.utils.render.slick.tests;
	
import de.crazymemecoke.utils.render.slick.AngelCodeFont;
import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.Font;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Image;
import de.crazymemecoke.utils.render.slick.Input;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * A test of the font rendering capabilities
 *
 * @author kevin
 */
public class PureFontTest extends BasicGame {
	/** The font we're going to use to render */
	private Font font;
	/** The image */
	private Image image;
	
	/**
	 * Create a new test for font rendering
	 */
	public PureFontTest() {
		super("Hiero Font Test");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.Game#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		image = new Image("testdata/sky.jpg");
		font = new AngelCodeFont("testdata/hiero.fnt","testdata/hiero.png");
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		image.draw(0,0,800,600);
		font.drawString(100, 32, "On top of old smokey, all");
		font.drawString(100, 80, "covered with sand..");
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta) throws SlickException {
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}
	
	/** The container we're using */
	private static AppGameContainer container;
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed in the test
	 */
	public static void main(String[] argv) {
		try {
			container = new AppGameContainer(new PureFontTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
