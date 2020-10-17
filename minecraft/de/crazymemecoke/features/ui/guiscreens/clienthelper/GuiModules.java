package de.crazymemecoke.features.ui.guiscreens.clienthelper;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class GuiModules extends GuiScreen {

    FontManager fM = Client.main().fontMgr();

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

        String title = Client.main().getClientName() + " | Client Helper | Alle Modules";
        fM.cabin23.drawString(title, width / 2 - fM.cabin23.getStringWidth(title) / 2, 25, Rainbow.rainbow(1, 1).getRGB());

        fM.comfortaa22.drawString("Modules:", width / 2 - fM.comfortaa22.getStringWidth("Modules:") / 2, 45, -1);

        int yModPos = 60;
        int yModPos2 = 60;
        for (Module mod : Client.main().modMgr().getModules()) {
            if (yModPos < height - 35) {
                fM.comfortaa20.drawCenteredString(mod.getName(), width / 2 - 50, yModPos, -1);
                yModPos += 12;
            }
            if (yModPos > height - 35) {
                fM.comfortaa20.drawCenteredString(mod.getName(), width / 2 + 50, yModPos2, -1);
                yModPos2 += 12;
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
