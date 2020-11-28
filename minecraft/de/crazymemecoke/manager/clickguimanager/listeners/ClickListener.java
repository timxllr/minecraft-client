package de.crazymemecoke.manager.clickguimanager.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.components.GuiButton;
import de.crazymemecoke.features.modules.Module;


/**
 * @author sendQueue <Vinii>
 *
 *         Further info at Vinii.de or github@vinii.de, file created at 11.11.2020. 
 *         Use is only authorized if given credit!
 * 
 */
public class ClickListener implements ActionListener {

	private GuiButton button;

	public ClickListener(GuiButton button) {
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Module m = Client.main().modMgr().getByName(button.getText());
		m.toggle();
	}
}
