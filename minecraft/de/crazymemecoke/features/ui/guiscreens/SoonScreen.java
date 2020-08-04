package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class SoonScreen extends GuiScreen {
    public GuiScreen parent;

    public SoonScreen(GuiScreen parentScreen) {
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
            Minecraft.getMinecraft().displayGuiScreen(parent);
        }
    }

    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        drawString(this.mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution sr = new ScaledResolution(this.mc);
        mc.getTextureManager().bindTexture(new ResourceLocation(Client.getInstance().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
                width, height, sr.getScaledWidth(), sr.getScaledHeight());

        Client.getInstance().getFontManager().comfortaa20.drawString("Dieses Feature ist noch nicht bereit für die breite Masse!", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, -1);
        Client.getInstance().getFontManager().comfortaa20.drawString("Schaue zu einem späteren Zeitpunkt erneut vorbei!", sr.getScaledWidth() / 2 + 12, sr.getScaledHeight() / 2 + 10, -1);
        super.drawScreen(x, y, f);
    }
}