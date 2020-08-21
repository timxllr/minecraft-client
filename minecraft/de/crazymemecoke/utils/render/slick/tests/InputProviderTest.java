package de.crazymemecoke.utils.render.slick.tests;

import de.crazymemecoke.utils.render.slick.AppGameContainer;
import de.crazymemecoke.utils.render.slick.BasicGame;
import de.crazymemecoke.utils.render.slick.GameContainer;
import de.crazymemecoke.utils.render.slick.Graphics;
import de.crazymemecoke.utils.render.slick.Input;
import de.crazymemecoke.utils.render.slick.SlickException;
import de.crazymemecoke.utils.render.slick.command.BasicCommand;
import de.crazymemecoke.utils.render.slick.command.Command;
import de.crazymemecoke.utils.render.slick.command.ControllerButtonControl;
import de.crazymemecoke.utils.render.slick.command.ControllerDirectionControl;
import de.crazymemecoke.utils.render.slick.command.InputProvider;
import de.crazymemecoke.utils.render.slick.command.InputProviderListener;
import de.crazymemecoke.utils.render.slick.command.KeyControl;
import de.crazymemecoke.utils.render.slick.command.MouseButtonControl;

/**
 * A test for abstract input via InputProvider
 *
 * @author kevin
 */
public class InputProviderTest extends BasicGame implements InputProviderListener {
	/** The command for attack */
	private Command attack = new BasicCommand("attack");
	/** The command for jump */
	private Command jump = new BasicCommand("jump");
	/** The command for jump */
	private Command run = new BasicCommand("run");
	/** The input provider abstracting input */
	private InputProvider provider;
	/** The message to be displayed */
	private String message = "";
	
	/**
	 * Create a new image rendering test
	 */
	public InputProviderTest() {
		super("InputProvider Test");
	}
	
	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#init(de.crazymemecoke.utils.render.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		provider = new InputProvider(container.getInput());
		provider.addListener(this);
		
		provider.bindCommand(new KeyControl(Input.KEY_LEFT), run);
		provider.bindCommand(new KeyControl(Input.KEY_A), run);
		provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), run);
		provider.bindCommand(new KeyControl(Input.KEY_UP), jump);
		provider.bindCommand(new KeyControl(Input.KEY_W), jump);
		provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.UP), jump);
		provider.bindCommand(new KeyControl(Input.KEY_SPACE), attack);
		provider.bindCommand(new MouseButtonControl(0), attack);
		provider.bindCommand(new ControllerButtonControl(0, 1), attack);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#render(de.crazymemecoke.utils.render.slick.GameContainer, de.crazymemecoke.utils.render.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g) {
		g.drawString("Press A, W, Left, Up, space, mouse button 1,and gamepad controls",10,50);
		g.drawString(message,100,150);
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.BasicGame#update(de.crazymemecoke.utils.render.slick.GameContainer, int)
	 */
	public void update(GameContainer container, int delta) {
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.command.InputProviderListener#controlPressed(de.crazymemecoke.utils.render.slick.command.Command)
	 */
	public void controlPressed(Command command) {
		message = "Pressed: "+command;
	}

	/**
	 * @see de.crazymemecoke.utils.render.slick.command.InputProviderListener#controlReleased(de.crazymemecoke.utils.render.slick.command.Command)
	 */
	public void controlReleased(Command command) {
		message = "Released: "+command;
	}
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new InputProviderTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
