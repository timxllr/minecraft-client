package de.crazymemecoke.manager.clickguimanager.clickgui;

import java.awt.*;
import java.util.HashMap;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.gui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.clickgui.components.Frame;
import de.crazymemecoke.manager.clickguimanager.clickgui.components.GuiButton;
import de.crazymemecoke.manager.clickguimanager.clickgui.components.GuiFrame;
import de.crazymemecoke.manager.clickguimanager.clickgui.listeners.ClickListener;
import de.crazymemecoke.manager.clickguimanager.clickgui.listeners.ComponentsListener;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.FramePosition;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleManager;

/**
 * @author sendQueue <Vinii>
 *
 *         Further info at Vinii.de or github@vinii.de, file created at
 *         11.11.2020. Use is only authorized if given credit!
 * 
 *         Some renderstuff requires https://github.com/sendQueue/LWJGLUtil
 * 
 */
public class Panel extends ClickGui {
	public static HashMap<String, FramePosition> framePositions = new HashMap<String, FramePosition>();

	public static UnicodeFontRenderer fR = Client.main().fontMgr().font("Arial", 18, Font.PLAIN);

	public static String theme;

	public static int FRAME_WIDTH = 100;

	// colors
	public static int color = new Color(193, 105, 170, 220).getRGB();
//	public static int intColor = 12675498;
	public static int fontColor = Color.white.getRGB();
	public static int grey40_240 = new Color(40, 40, 40, 140).getRGB();
//	public static int intgrey40_240 = 2631720;
	public static int black195 = new Color(0, 0, 0, 195).getRGB();
	public static int black100 = new Color(0, 0, 0, 100).getRGB();

	/**
	 * Initializes Panel
	 * 
	 * @param theme
	 * @param fontSize
	 */
	public Panel(String theme, int fontSize) {
		Panel.theme = theme;
		fR = Client.main().fontMgr().font("BigNoodleTitling", fontSize, Font.PLAIN);
	}

	@Override
	public void initGui() {
		int x = 25;
		for (Category cat : Category.values()) {
			GuiFrame frame;
			// load frame positions
			if (framePositions.containsKey(cat.name())) {
				FramePosition curPos = framePositions.get(cat.name());
				frame = new GuiFrame(cat.name(), curPos.getPosX(), curPos.getPosY(), curPos.isExpanded());
			} else {
				frame = new GuiFrame(cat.name(), x, 50, true);
			}
			for (Module m : Client.main().modMgr().modules) {
				if (cat == m.category()) {
					GuiButton button = new GuiButton(m.name());
					button.addClickListener(new ClickListener(button));
					button.addExtendListener(new ComponentsListener(button));
					frame.addButton(button);
				}
			}
			addFrame(frame);
			x += 140;
		}
		super.initGui();
	}

	public void onGuiClosed() {
		// save positions to framePositions
		if (!getFrames().isEmpty()) {
			for (Frame frame : getFrames()) {
				GuiFrame guiFrame = ((GuiFrame) frame);
				framePositions.put(guiFrame.getTitle(),
						new FramePosition(guiFrame.getPosX(), guiFrame.getPosY(), guiFrame.isExpaned()));
			}
		}

		if (mc.entityRenderer.theShaderGroup != null) {
			mc.entityRenderer.theShaderGroup.deleteShaderGroup();
			mc.entityRenderer.theShaderGroup = null;
		}

		Client.main().modMgr().getModule(ClickGUI.class).onDisable();
	}
}
