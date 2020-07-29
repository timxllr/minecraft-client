package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
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
            Minecraft.getMinecraft().displayGuiScreen(new ClientMenu());
        }
    }

    public void drawScreen(int x, int y, float f) {
        drawDefaultBackground();
        drawString(this.mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution s = new ScaledResolution(this.mc);
        Gui.drawRect(0, 0, s.getScaledWidth(), s.getScaledHeight(), new Color(28, 26, 28).getRGB());

        Client.getInstance().getFontManager().comfortaa20.drawString("Dieses Feature ist noch nicht bereit für die breite Masse!", s.getScaledWidth() / 2, s.getScaledHeight() / 2, -1);
        Client.getInstance().getFontManager().comfortaa20.drawString("Schaue zu einem späteren Zeitpunkt erneut vorbei!", s.getScaledWidth() / 2 + 12, s.getScaledHeight() / 2 + 10, -1);
        super.drawScreen(x, y, f);
    }
}