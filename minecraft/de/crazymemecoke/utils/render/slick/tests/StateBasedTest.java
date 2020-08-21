package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.state.StateBasedGame;
import de.crazymemecoke.utils.render.slick.tests.states.TestState1;
import de.crazymemecoke.utils.render.slick.tests.states.TestState2;
import de.crazymemecoke.utils.render.slick.tests.states.TestState3;

/**
 * A test for the multi-state based functionality
 *
 * @author kevin
 */
public class StateBasedTest extends StateBasedGame {

	/**
	 * Create a new test
	 */
	public StateBasedTest() {
		super("State Based Test");
	}
	
	/**
	 * @see StateBasedGame#initStatesList(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void initStatesList(GameContainer container) {
		addState(new TestState1());
		addState(new TestState2());
		addState(new TestState3());
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new StateBasedTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
