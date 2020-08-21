package de.crazymemecoke.utils.render.slick;


/**
 * A basic implementation of a game to take out the boring bits
 *
 * @author kevin
 */
public abstract class BasicGame implements Game, InputListener {
	/** The maximum number of controllers supported by the basic game */
	private static final int MAX_CONTROLLERS = 20;
	/** The maximum number of controller buttons supported by the basic game */
	private static final int MAX_CONTROLLER_BUTTONS = 100;
	/** The title of the game */
	private String title;
	/** The state of the left control */
	protected boolean[] controllerLeft = new boolean[MAX_CONTROLLERS];
	/** The state of the right control */
	protected boolean[] controllerRight = new boolean[MAX_CONTROLLERS];
	/** The state of the up control */
	protected boolean[] controllerUp = new boolean[MAX_CONTROLLERS];
	/** The state of the down control */
	protected boolean[] controllerDown = new boolean[MAX_CONTROLLERS];
	/** The state of the button controlls */
	protected boolean[][] controllerButton = new boolean[MAX_CONTROLLERS][MAX_CONTROLLER_BUTTONS];
	
	/**
	 * Create a new basic game
	 * 
	 * @param title The title for the game
	 */
	public BasicGame(String title) {
		this.title = title;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#setInput(Input)
	 */
	public void setInput(Input input) {	
	}
	
	/**
	 * @see Game#closeRequested()
	 */
	public boolean closeRequested() {
		return true;
	}

	/**
	 * @see Game#getTitle()
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @see Game#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public abstract void init(GameContainer container) throws SlickException;

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
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerButtonPressed(int, int)
	 */
	public void controllerButtonPressed(int controller, int button) {
		controllerButton[controller][button] = true;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerButtonReleased(int, int)
	 */
	public void controllerButtonReleased(int controller, int button) {
		controllerButton[controller][button] = false;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerDownPressed(int)
	 */
	public void controllerDownPressed(int controller) {
		controllerDown[controller] = true;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerDownReleased(int)
	 */
	public void controllerDownReleased(int controller) {
		controllerDown[controller] = false;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerLeftPressed(int)
	 */
	public void controllerLeftPressed(int controller) {
		controllerLeft[controller] = true;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerLeftReleased(int)
	 */
	public void controllerLeftReleased(int controller) {
		controllerLeft[controller] = false;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerRightPressed(int)
	 */
	public void controllerRightPressed(int controller) {
		controllerRight[controller] = true;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerRightReleased(int)
	 */
	public void controllerRightReleased(int controller) {
		controllerRight[controller] = false;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerUpPressed(int)
	 */
	public void controllerUpPressed(int controller) {
		controllerUp[controller] = true;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#controllerUpReleased(int)
	 */
	public void controllerUpReleased(int controller) {
		controllerUp[controller] = false;
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseReleased(int, int, int)
	 */
	public void mouseReleased(int button, int x, int y) {
	}

	/**
	 * @see Game#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public abstract void update(GameContainer container, int delta) throws SlickException;

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#mouseWheelMoved(int)
	 */
	public void mouseWheelMoved(int change) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#isAcceptingInput()
	 */
	public boolean isAcceptingInput() {
		return true;
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.InputListener#inputEnded()
	 */
	public void inputEnded() {
		
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.ControlledInputReciever#inputStarted()
	 */
	public void inputStarted() {
		
	}
}
