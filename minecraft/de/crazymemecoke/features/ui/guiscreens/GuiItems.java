package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GuiItems extends GuiScreen {
    public GuiScreen parent;

    FontManager fM = Client.main().fontMgr();

    private GuiScreen parentScreen;

    public GuiItems(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(1, width / 2 - 100, height - 30, 200, 20, "Zurück"));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            mc.displayGuiScreen(parent);
        }
    }

    public void drawScreen(int posX, int posY, float f) {
        drawString(mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution sr = new ScaledResolution(Wrapper.mc);
        if (Keyboard.isKeyDown(1)) {
            mc.displayGuiScreen(parent);
        }

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.main().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());

        RenderUtils.drawRect(5, 5, width - 5, height - 5, new Color(0, 0, 0, 155).getRGB());

        String title = "Items";
        UnicodeFontRenderer titleFont = fM.font("Comfortaa", 24, Font.PLAIN);
        titleFont.drawStringWithShadow(title, width / 2 - titleFont.getStringWidth(title) / 2, 10, -1);

        super.drawScreen(posX, posY, f);
    }
}