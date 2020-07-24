package de.crazymemecoke.ui.menus;

import de.crazymemecoke.Client;
import de.crazymemecoke.ui.alts.GuiAltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ClientMenu extends GuiScreen implements GuiYesNoCallback {

    public void initGui() {
        int i = 24;
        int j = this.height / 4 + 48;

        buttonList.add(new GuiButton(0, width - 120, 0, 120, 20, "Zurück"));
        buttonList.add(new GuiButton(1, width - 120, 40, 120, 20, "Alt Login"));
        buttonList.add(new GuiButton(2, width - 120, 60, 120, 20, "Session Stealer"));
        buttonList.add(new GuiButton(3, width - 120, 80, 120, 20, "Alt Manager"));
        buttonList.add(new GuiButton(4, width - 120, 100, 120, 20, "Changelog"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new AltLogin(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new SessionStealer(this));
        }
        if (button.id == 3) {
            this.mc.displayGuiScreen(new GuiAltManager(this));
        }
        if (button.id == 4) {
            mc.displayGuiScreen(new Changelog());
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int i = 274;
        int j = this.width / 2 - i / 2;
        int k = 30;
        this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        ScaledResolution scaledRes = new ScaledResolution(this.mc);
        Gui.drawRect(0, 0, scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), new Color(28, 26, 28).getRGB());

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(
                MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scale(f, f, f);
        GlStateManager.popMatrix();

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}