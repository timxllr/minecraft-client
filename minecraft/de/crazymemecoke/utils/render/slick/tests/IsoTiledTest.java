package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.tiled.TiledMap;
import de.crazymemecoke.utils.render.slick.util.Bootstrap;

/**
 * Simple test for isometric map rendering
 * 
 * @author kevin
 */
public class IsoTiledTest extends BasicGame {
	/** The tilemap we're going to render */
	private TiledMap tilemap;
	
	/**
	 * Create a new test
	 */
	public IsoTiledTest() {
		super("Isometric Tiled Map Test");
	}

	/*
	 * (non-Javadoc)
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		tilemap = new TiledMap("testdata/isoexample.tmx", "testdata/");
	}

	/*
	 * (non-Javadoc)
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	/*
	 * (non-Javadoc)
	 * @see de.crazymemecoke.utils.render.slick.Game#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		tilemap.render(350,150);
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed in from the command line
	 */
	public static void main(String[] argv) {
		Bootstrap.runAsApplication(new IsoTiledTest(), 800,600,false);
	}
}
