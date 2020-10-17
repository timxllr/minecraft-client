package de.crazymemecoke.features.ui.guiscreens.clienthelper;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class GuiClientHelper extends GuiScreen {

    FontManager fM = Client.main().fontMgr();

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(1, width / 2 - 50, height - 22, 100, 20, "Zurück"));
        buttonList.add(new GuiButton(2, width / 2 - 100, 50, 200, 20, "Alle Modules"));
        buttonList.add(new GuiButton(3, width / 2 - 100, 80, 200, 20, "Alle Commands"));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        // Back
        if (button.id == 1) {
            mc.displayGuiScreen(null);
        }

        // GuiScreen All Modules
        if (button.id == 2) {
            mc.displayGuiScreen(new GuiModules());
        }

        // GuiScreen All Commands
        if (button.id == 3) {
            mc.displayGuiScreen(new GuiCommands());
        }

        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        ScaledResolution sr = new ScaledResolution(mc);
        RenderUtils.drawRect(20, 20, width - 20, height - 25, new Color(0, 0, 0, 150).getRGB());

        String title = Client.main().getClientName() + " | Client Helper | Startseite";
        fM.cabin23.drawString(title, width / 2 - fM.cabin23.getStringWidth(title) / 2, 25, Rainbow.rainbow(1, 1).getRGB());

        RenderUtils.drawRect(width / 2 - 100, height - 110, width / 2 + 100, height - 45, new Color(0, 0, 0, 150).getRGB());

        String subTitle = "Client Informationen";
        String clientName = "Name: " + Client.main().getClientName();
        String clientVersion = "Version: " + Client.main().getClientVersion();
        String clientCoder = "Coder: " + Client.main().getClientCoder();

        fM.cabin35.drawStringWithShadow(subTitle, width / 2 - fM.cabin35.getStringWidth(subTitle) / 2, height - 105, -1);
        fM.cabin23.drawStringWithShadow(clientName, width / 2 - fM.cabin23.getStringWidth(clientName) / 2, height - 85, -1);
        fM.cabin23.drawStringWithShadow(clientVersion, width / 2 - fM.cabin23.getStringWidth(clientVersion) / 2, height - 75, -1);
        fM.cabin23.drawStringWithShadow(clientCoder, width / 2 - fM.cabin23.getStringWidth(clientCoder) / 2, height - 65, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
