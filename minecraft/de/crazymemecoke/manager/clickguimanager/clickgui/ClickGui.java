package de.crazymemecoke.manager.clickguimanager.clickgui;

import java.io.IOException;
import java.util.ArrayList;

import de.crazymemecoke.manager.clickguimanager.clickgui.components.Frame;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author sendQueue <Vinii>
 *
 *         Further info at Vinii.de or github@vinii.de, file created at 11.11.2020. 
 *         Use is only authorized if given credit!
 * 
 */
public class ClickGui extends GuiScreen {

	public static int compID = 0;

	private ArrayList<Frame> frames = new ArrayList<Frame>();

	/**
	 * 
	 */
	public ClickGui() {
		compID = 0;
		
	}

	protected void addFrame(Frame frame) {
		if (!frames.contains(frame)) {
			frames.add(frame);
		}
	}

	@Override
	public void initGui() {
		for (Frame frame : frames) {
			frame.initialize();
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		for (Frame frame : frames) {
			frame.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);

		for (Frame frame : frames) {
			frame.keyTyped(keyCode, typedChar);
		}
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		for (Frame frame : frames) {
			frame.render(mouseX, mouseY);
		}
	}
}
