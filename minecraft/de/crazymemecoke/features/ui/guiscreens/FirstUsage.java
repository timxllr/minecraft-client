package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

public class FirstUsage extends GuiScreen implements GuiYesNoCallback {

    public void initGui() {
        buttonList.add(new GuiButton(0, width / 2 - 50, 250, 120, 20, "Ok, verstanden!"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_F6) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            return;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_X)) {
            mc.displayGuiScreen(new ClientMenu());
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        drawGradientRect(0, 0, width, height, -2130706433, 16777215);
        drawGradientRect(0, 0, width, height, 0, Integer.MIN_VALUE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        ScaledResolution scaledRes = new ScaledResolution(mc);
        Gui.drawRect(0, 0, scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), new Color(28, 26, 28).getRGB());

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(
                MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scale(f, f, f);
        GlStateManager.popMatrix();

        Client.getInstance().getFontManager().comfortaa50.drawStringWithShadow("Willkommen!", width / 2 - 70, 40, -1);
        Client.getInstance().getFontManager().comfortaa22.drawStringWithShadow("Wenn du den Client das 1. Mal verwendest:", width / 2 - 115, 80, -1);
        Client.getInstance().getFontManager().comfortaa22.drawStringWithShadow("RSHIFT - ClickGUI", width / 2 - 40, 100, -1);
        Client.getInstance().getFontManager().comfortaa22.drawStringWithShadow("RCONTROL - Info HUD", width / 2 - 55, 110, -1);
        Client.getInstance().getFontManager().comfortaa22.drawStringWithShadow("Chat-Prefix - Punkt (.)", width / 2 - 50, 120, -1);

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}