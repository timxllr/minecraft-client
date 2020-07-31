package de.crazymemecoke.features.ui.guiscreens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class AltMgr extends GuiScreen {
    public GuiScreen parent;

    public AltMgr(GuiScreen parentScreen) {
        this.parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96 + 200, "Zurück"));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            Minecraft.getMinecraft().displayGuiScreen(new ClientMenu());
        }
    }

    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        drawString(this.mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution sr = new ScaledResolution(this.mc);
        mc.getTextureManager().bindTexture(new ResourceLocation("textures/client/background.jpg"));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
                width, height, sr.getScaledWidth(), sr.getScaledHeight());

        super.drawScreen(x, y, f);
    }
}