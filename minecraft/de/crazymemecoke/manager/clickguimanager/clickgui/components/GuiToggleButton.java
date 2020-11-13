package de.crazymemecoke.manager.clickguimanager.clickgui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import de.crazymemecoke.manager.clickguimanager.clickgui.Panel;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.RenderUtil;
import de.crazymemecoke.utils.render.RenderUtils;

/**
 * @author sendQueue <Vinii>
 *
 *         Further info at Vinii.de or github@vinii.de, file created at 11.11.2020. 
 *         Use is only authorized if given credit!
 * 
 */
public class GuiToggleButton implements GuiComponent {

	private String text;
	private boolean toggled;

	private int posX, posY;

	private ArrayList<ActionListener> clickListeners = new ArrayList<ActionListener>();

	/**
	 * 
	 */
	public GuiToggleButton(String text) {
		this.text = text;
	}

	@Override
	public void render(int posX, int posY, int width, int mouseX, int mouseY) {
		this.posX = posX;
		this.posY = posY;
		
		switch (Panel.theme) {
		case "Caesium":
			renderCaesium(posX, posY);
			break;
		default:
			break;
		}

	}

	/**
	 * Renders toggleButton for theme Caesium
	 */
	private void renderCaesium(int posX, int posY) {
		RenderUtils.drawFilledCircle(posX + 8, posY + 7, 6, Panel.intColor);
		if(!toggled)
			RenderUtils.drawFilledCircle(posX + 8, posY + 7, 5, Panel.intgrey40_240);
		Panel.fR.drawStringWithShadow(text, posX + 17, posY + 3, Panel.fontColor);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		final int width = Panel.fR.getStringWidth(text) + 10;
		if (RenderUtil.isHovered(posX, posY + 2, width, getHeight(), mouseX, mouseY)) {
			toggled = !toggled;
			for (ActionListener listener : clickListeners) {
				listener.actionPerformed(new ActionEvent(this, hashCode(), "click", System.currentTimeMillis(), 0));
			}
		}

	}

	@Override
	public void keyTyped(int keyCode, char typedChar) {

	}

	@Override
	public int getWidth() {
		return Panel.fR.getStringWidth(text) + 20;
	}

	@Override
	public int getHeight() {
		return Panel.fR.FONT_HEIGHT + 5;
	}


	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public void addClickListener(ActionListener actionlistener) {
		clickListeners.add(actionlistener);
	}
}
