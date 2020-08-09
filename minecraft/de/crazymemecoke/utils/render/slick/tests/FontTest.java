package de.crazymemecoke.utils.render.slick.tests;
	
import de.crazymemecoke.utils.render.slick.AngelCodeFont;
import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.Color;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Image;
import de.crazymemecoke.utils.render.slick.Input;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.util.Log;

/**
 * A test of the font rendering capabilities
 *
 * @author kevin
 */
public class FontTest extends BasicGame {
	/** The font we're going to use to render */
	private AngelCodeFont font;
	/** The font we're going to use to render */
	private AngelCodeFont font2;
	/** The image of the font to compare against */
	private Image image;
	
	/**
	 * Create a new test for font rendering
	 */
	public FontTest() {
		super("Font Test");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.Game#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
		font2 = new AngelCodeFont("testdata/hiero.fnt","testdata/hiero.png");
		image = new Image("testdata/demo2_00.tga", false);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		font.drawString(80, 5, "A Font Example", Color.red);
		font.drawString(100, 32, "We - AV - Here is a more complete line that hopefully");
		font.drawString(100, 36 + font.getHeight("We Here is a more complete line that hopefully"), 
				             "will show some kerning.");
		
		font2.drawString(80, 85, "A Font Example", Color.red);
		font2.drawString(100, 132, "We - AV - Here is a more complete line that hopefully");
		font2.drawString(100, 136 + font2.getHeight("We - Here is a more complete line that hopefully"), 
				             "will show some kerning.");
		image.draw(100,400);
		
		String testStr = "Testing Font";
		font2.drawString(100, 300, testStr);
		g.setColor(Color.white);
		g.drawRect(100,300+font2.getYOffset(testStr),font2.getWidth(testStr),font2.getHeight(testStr)-font2.getYOffset(testStr));
		font.drawString(500, 300, testStr);
		g.setColor(Color.white);
		g.drawRect(500,300+font.getYOffset(testStr),font.getWidth(testStr),font.getHeight(testStr)-font.getYOffset(testStr));
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
		if (key == Input.KEY_SPACE) {
			try {
				container.setDisplayMode(640, 480, false);
			} catch (SlickException e) {
				Log.error(e);
			}
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
			container = new AppGameContainer(new FontTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
