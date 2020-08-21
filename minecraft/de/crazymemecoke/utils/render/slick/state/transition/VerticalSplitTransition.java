package de.crazymemecoke.utils.render.slick.state.transition;

import de.crazymemecoke.utils.render.slick.Color;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.opengl.renderer.Renderer;
import de.crazymemecoke.utils.render.slick.opengl.renderer.SGL;
import de.crazymemecoke.utils.render.slick.state.GameState;
import de.crazymemecoke.utils.render.slick.state.StateBasedGame;

/**
 * Vertical split transition that causes the previous state to split vertically
 * revealing the new state underneath.
 * 
 * This state is an enter transition.
 * 
 * @author kevin
 */
public class VerticalSplitTransition implements Transition {
	/** The renderer to use for all GL operations */
	protected static SGL GL = Renderer.get();
	
	/** The previous game state */
	private GameState prev;
	/** The current offset */
	private float offset;
	/** True if the transition is finished */
	private boolean finish;
	/** The background to draw underneath the previous state (null for none) */
	private Color background;
	
	/**
	 * Create a new transition
	 */
	public VerticalSplitTransition() {
		
	}

	/**
	 * Create a new transition
	 * 
	 * @param background The background colour to draw under the previous state
	 */
	public VerticalSplitTransition(Color background) {
		this.background = background;
	}
	
	/**
	 * @see Transition#init(de.crazymemecoke.utils.render.slick.state.GameState, de.crazymemecoke.utils.render.slick.state.GameState)
	 */
	public void init(GameState firstState, GameState secondState) {
		prev = secondState;
	}

	/**
	 * @see Transition#isComplete()
	 */
	public boolean isComplete() {
		return finish;
	}

	/**
	 * @see Transition#postRender(de.crazymemecoke.utils.render.slick.state.StateBasedGame, de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		g.translate(0, -offset);
		g.setClip(0,(int)-offset,container.getWidth(),container.getHeight()/2);
		if (background != null) {
			Color c = g.getColor();
			g.setColor(background);
			g.fillRect(0,0,container.getWidth(),container.getHeight());
			g.setColor(c);
		}
		GL.glPushMatrix();
		prev.render(container, game, g);
		GL.glPopMatrix();
		g.clearClip();
		g.resetTransform();
		
		g.translate(0, offset);
		g.setClip(0,(int)((container.getHeight()/2)+(offset)),container.getWidth(),container.getHeight()/2);
		if (background != null) {
			Color c = g.getColor();
			g.setColor(background);
			g.fillRect(0,0,container.getWidth(),container.getHeight());
			g.setColor(c);
		}
		GL.glPushMatrix();
		prev.render(container, game, g);
		GL.glPopMatrix();
		g.clearClip();
		g.translate(0,-offset);
	}

	/**
	 * @see Transition#preRender(de.crazymemecoke.utils.render.slick.state.StateBasedGame, de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void preRender(StateBasedGame game, GameContainer container,
			Graphics g) throws SlickException {
	}

	/**
	 * @see Transition#update(de.crazymemecoke.utils.render.slick.state.StateBasedGame, de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		offset += delta * 1f;
		if (offset > container.getHeight() / 2) {
			finish = true;
		}
	}

}
