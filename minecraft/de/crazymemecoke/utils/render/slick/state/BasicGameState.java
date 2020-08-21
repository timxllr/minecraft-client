package de.crazymemecoke.utils.render.slick.state;

import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Input;
import de.crazymemecoke.utils.render.slick.SlickException;

/**
 * A simple state used an adapter so we don't have to implement all the event methods
 * every time.
 *
 * @author kevin
 */
public abstract class BasicGameState implements GameState {
	/**
	 * @see de.crazymemecoke.utils.render.slick.ControlledInputReciever#inputStarted()
	 */
	public void inputStarted() {
		
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#isAcceptingInput()
	 */
	public boolean isAcceptingInput() {
		return true;
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#setInput(de.crazymemecoke.utils.render.slick.Input)
	 */
	public void setInput(Input input) {
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#inputEnded()
	 */
	public void inputEnded() {
	}
	
	/**
	 * @see GameState#getID()
	 */
	public abstract int getID();

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerButtonPressed(int, int)
	 */
	public void controllerButtonPressed(int controller, int button) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerButtonReleased(int, int)
	 */
	public void controllerButtonReleased(int controller, int button) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerDownPressed(int)
	 */
	public void controllerDownPressed(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerDownReleased(int)
	 */
	public void controllerDownReleased(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerLeftPressed(int)
	 */
	public void controllerLeftPressed(int controller) {
		
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerLeftReleased(int)
	 */
	public void controllerLeftReleased(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerRightPressed(int)
	 */
	public void controllerRightPressed(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerRightReleased(int)
	 */
	public void controllerRightReleased(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerUpPressed(int)
	 */
	public void controllerUpPressed(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerUpReleased(int)
	 */
	public void controllerUpReleased(int controller) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#keyReleased(int, char)
	 */
	public void keyReleased(int key, char c) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseMoved(int, int, int, int)
	 */
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseDragged(int, int, int, int)
	 */
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseClicked(int, int, int, int)
	 */
	public void mouseClicked(int button, int x, int y, int clickCount) {
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mousePressed(int, int, int)
	 */
	public void mousePressed(int button, int x, int y) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseReleased(int, int, int)
	 */
	public void mouseReleased(int button, int x, int y) {
	}

	/**
	 * @see GameState#enter(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.state.StateBasedGame)
	 */
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	}

	/**
	 * @see GameState#leave(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.state.StateBasedGame)
	 */
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseWheelMoved(int)
	 */
	public void mouseWheelMoved(int newValue) {
	}

}
