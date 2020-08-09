package de.crazymemecoke.features.ui.guiscreens.clienthelper;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class GuiCommands extends GuiScreen {

    FontManager fM = Client.instance().getFontManager();

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(1, width / 2 - 50, height - 22, 100, 20, "Zurück"));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(new GuiClientHelper());
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            mc.displayGuiScreen(new GuiClientHelper());
        }

        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        ScaledResolution sr = new ScaledResolution(mc);
        RenderUtils.drawRect(20, 20, width - 20, height - 25, new Color(0, 0, 0, 150).getRGB());

        String title = Client.instance().getClientName() + " | Client Helper | Alle Commands";
        fM.cabin23.drawString(title, width / 2 - fM.cabin23.getStringWidth(title) / 2, 25, Rainbow.rainbow(1, 1).getRGB());

        fM.comfortaa22.drawString("Commands:", width / 2 - fM.comfortaa22.getStringWidth("Commands:") / 2, 45, -1);

        int yCmdPos = 60;
        int yCmdPos2 = 60;
        for (Command cmd : Client.instance().getCommandManager().getCommands()) {
            if (yCmdPos < height - 35) {
                fM.comfortaa20.drawCenteredString(cmd.getName(), width / 2 - 50, yCmdPos, -1);
                yCmdPos += 12;
            }
            if (yCmdPos > height - 35) {
                fM.comfortaa20.drawCenteredString(cmd.getName(), width / 2 + 50, yCmdPos2, -1);
                yCmdPos2 += 12;
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
