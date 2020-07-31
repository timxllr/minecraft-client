package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.commandmanager.Command;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class ClientManager extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/client/background.jpg"));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
                width, height, sr.getScaledWidth(), sr.getScaledHeight());
        RenderUtils.drawBorderedRect(20, 20, width - 20, height - 20, 2, Rainbow.rainbow(1, 1).hashCode(), new Color(0, 0, 0, 150).hashCode());
        Client.getInstance().getFontManager().comfortaa22.drawString(Client.getInstance().getClientName(), width / 2 - 40, 25, Rainbow.rainbow(1, 1).hashCode());

        Client.getInstance().getFontManager().comfortaa22.drawString("Modules:", width / 4 - 100, 60, -1);
        int yModPos = 80;
        int yModPos2 = 80;
        for (Module mod : Client.getInstance().getModuleManager().getModules()) {
            if (yModPos < height - 30) {
                Client.getInstance().getFontManager().comfortaa20.drawString(mod.getName(), width / 4 - 100, yModPos, -1);
                yModPos += 10;
            }
            if (yModPos > height - 30) {
                Client.getInstance().getFontManager().comfortaa20.drawString(mod.getName(), width / 4, yModPos2, -1);
                yModPos2 += 10;
            }
        }

        Client.getInstance().getFontManager().comfortaa22.drawString("Commands:", width / 4 + width / 2, 60, -1);
        int yCmdPos = 80;
        for (Command cmd : Client.getInstance().getCommandManager().getCommands()) {
            if (yCmdPos < height - 30) {
                Client.getInstance().getFontManager().comfortaa20.drawString(Client.getInstance().getClientPrefix() + cmd.getName().toLowerCase(), width / 4 + width / 2, yCmdPos, -1);
                yCmdPos += 10;
            }
            if (yCmdPos > height - 30) {
                Client.getInstance().getFontManager().comfortaa20.drawString(Client.getInstance().getClientPrefix() + cmd.getName().toLowerCase(), width / 4 + width / 2 + 100, yCmdPos, -1);
                yCmdPos += 10;
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
