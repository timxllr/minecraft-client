package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Changelog extends GuiScreen {
    private final List<String> log;
    private float maxWidth;
    private float maxHeight;

    public Changelog() {
        log = new ArrayList<String>();
    }

    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final ScaledResolution scaledRes = new ScaledResolution(mc);
        Gui.drawRect(0, 0, scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), new Color(28, 26, 28).getRGB());
        RenderUtils.drawBorderedRect(width / 2 - maxWidth / 2.0f - 6.0f, 38.0f, width / 2 + maxWidth / 2.0f + 6.0f, maxHeight, 1.5f, -587202560, Integer.MIN_VALUE);
        if (log.isEmpty()) {
            Wrapper.mc.fontRendererObj.drawString("Loading...", width / 2 - Wrapper.mc.fontRendererObj.getStringWidth("Loading...") / 2, 40, -1);
        }
        int y = 40;
        for (final String text : log) {
            Wrapper.mc.fontRendererObj.drawString(text, width / 2 - Wrapper.mc.fontRendererObj.getStringWidth(text) / 2, y, -1);
            y += Client.getInstance().getFontManager().comfortaa20.getStringHeight(text);
            if (maxHeight < y) {
                maxHeight = (float) y;
            }
            if (maxWidth >= Wrapper.mc.fontRendererObj.getStringWidth(text)) {
                continue;
            }
            maxWidth = Wrapper.mc.fontRendererObj.getStringWidth(text);

        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void initGui() {
        try {
            final URL url = new URL("https://pastebin.com/raw/9zBXvkwD");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                log.add(line);
            }
            in.close();
        } catch (Exception ex) {
        }
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 200, "Zurück"));
    }

    protected void actionPerformed(final GuiButton p_146284_1_) {
        mc.displayGuiScreen(new ClientMenu());
    }
}
