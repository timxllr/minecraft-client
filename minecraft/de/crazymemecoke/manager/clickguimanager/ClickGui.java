package de.crazymemecoke.manager.clickguimanager;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.gui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.components.Frame;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

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
	//dont change
	private final UnicodeFontRenderer fr = Client.main().fontMgr().font("Arial", 12, Font.PLAIN);
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
		if (OpenGlHelper.shadersSupported && mc.getRenderViewEntity() instanceof EntityPlayer) {
			if (mc.entityRenderer.theShaderGroup != null) {
				mc.entityRenderer.theShaderGroup.deleteShaderGroup();
			}
			if (Client.main().setMgr().settingByName("Blur", Client.main().modMgr().getModule(ClickGUI.class)).getBool()) {
				mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
			}
		}

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

		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			mc.displayGuiScreen(null);
			Client.main().modMgr().getModule(ClickGUI.class).setState(false);
		}
	}

	public ArrayList<Frame> getFrames() {
		return frames;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		for (Frame frame : frames) {
			frame.render(mouseX, mouseY);
		}
	}
}
