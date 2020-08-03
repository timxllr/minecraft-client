package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.altmanager.GuiAltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClientMenu extends GuiScreen implements GuiYesNoCallback {

    public void initGui() {
        buttonList.add(new GuiButton(1, width / 2 - 50, height / 2 - 60, 120, 20, "AltLogin"));
        buttonList.add(new GuiButton(2, width / 2 - 50, height / 2 - 30, 120, 20, "AltManager"));
        buttonList.add(new GuiButton(3, width / 2 - 50, height / 2, 120, 20, "SessionStealer"));
        buttonList.add(new GuiButton(4, width / 2 - 50, height / 2 + 30, 120, 20, "Changelog"));
        buttonList.add(new GuiButton(5, width / 2 - 50, height / 2 + 60, 120, 20, "Alpha AltManager"));
        buttonList.add(new GuiButton(0, width / 2 - 50, height - 40, 120, 20, "Zurück"));
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiMainMenu());
        }
        if (button.id == 1) {
            mc.displayGuiScreen(new AltLogin(this));
        }
        if (button.id == 3) {
            mc.displayGuiScreen(new SessionStealer(this));
        }
        if (button.id == 2) {
            mc.displayGuiScreen(new GuiAltManager(this));
        }
        if (button.id == 4) {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(Client.getInstance().getClientChangelog()));
                }
            } catch (Exception ignored) {
            }
        }
        if (button.id == 5) {
            mc.displayGuiScreen(new AltMgr(this));
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_X)) {
            mc.displayGuiScreen(new FirstUsage());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        drawGradientRect(0, 0, width, height, -2130706433, 16777215);
        drawGradientRect(0, 0, width, height, 0, Integer.MIN_VALUE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation(Client.getInstance().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
                width, height, sr.getScaledWidth(), sr.getScaledHeight());

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(
                MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scale(f, f, f);
        GlStateManager.popMatrix();

        Client.getInstance().getFontManager().comfortaa50.drawStringWithShadow(Client.getInstance().getClientName() + " Menü", width / 2 - 80, 20, -1);

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}