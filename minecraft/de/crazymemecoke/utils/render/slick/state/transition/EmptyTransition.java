package de.crazymemecoke.utils.render.slick.state.transition;

import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.state.GameState;
import de.crazymemecoke.utils.render.slick.state.StateBasedGame;

/**
 * A transition that has no effect and instantly finishes. Used as a utility for the people
 * not using transitions
 *
 * @author kevin
 */
public class EmptyTransition implements Transition {

	/**
	 * @see Transition#isComplete()
	 */
	public boolean isComplete() {
		return true;
	}


	/**
	 * @see Transition#postRender(de.crazymemecoke.utils.render.slick.state.StateBasedGame, de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		// no op
	}

	/**
	 * @see Transition#preRender(de.crazymemecoke.utils.render.slick.state.StateBasedGame, de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		// no op
	}

	/**
	 * @see Transition#update(de.crazymemecoke.utils.render.slick.state.StateBasedGame, de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(StateBasedGame game, GameContainer container, int delta) throws SlickException {
		// no op
	}


	public void init(GameState firstState, GameState secondState) {
		// TODO Auto-generated method stub
		
	}
}
