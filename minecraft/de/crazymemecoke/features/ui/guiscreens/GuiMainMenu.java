package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.ui.guiscreens.altmanager.GuiAltManager;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {

    public void initGui() {
        buttonList.add(new GuiButton(0, width / 2 - 120, height / 2 - 60, 120, 20, I18n.format("menu.singleplayer")));
        buttonList.add(new GuiButton(1, width / 2 - 120, height / 2 - 30, 120, 20, I18n.format("menu.multiplayer")));
        buttonList.add(new GuiButton(2, width / 2 + 10, height / 2 - 60, 120, 20, I18n.format("menu.options")));
        buttonList.add(new GuiButton(3, width / 2 + 10, height / 2 - 30, 120, 20, I18n.format("menu.quit")));
        buttonList.add(new GuiButton(4, width / 2 - 120, height / 2, 120, 20, "AltManager"));
        buttonList.add(new GuiButton(5, width / 2 + 10, height / 2, 120, 20, "Credits"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 1) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 2) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if (button.id == 3) {
            mc.shutdown();
        }
        if (button.id == 4) {
            mc.displayGuiScreen(new GuiAltManager(this));
        }
        if (button.id == 5) {
            mc.displayGuiScreen(new GuiCredits(this));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int i = 274;
        int j = width / 2 - i / 2;
        int k = 30;
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

        RenderUtils.drawRect(width / 2 - 130, height / 2 - 70, width / 2 + 140, height / 2 + 30, new Color(55, 55, 55, 150).getRGB());

        String header = Client.getInstance().getClientName() + " " + Client.getInstance().getClientVersion() + " by " + Client.getInstance().getClientCoder();
        RenderUtils.drawRect(0, 0, Client.getInstance().getFontManager().rainbowVeins50.getStringWidth(header) + 5, Client.getInstance().getFontManager().rainbowVeins50.getStringHeight(header) + 5, new Color(55, 55, 55, 150).getRGB());
        Client.getInstance().getFontManager().rainbowVeins50.drawString(header, 2, 4, -1);

        String s1 = "Username: " + mc.session.getUsername();
        RenderUtils.drawRect(15, height - 15, Client.getInstance().getFontManager().comfortaa22.getStringWidth(s1) + 25, height - 35, new Color(55, 55, 55, 150).getRGB());
        Client.getInstance().getFontManager().comfortaa22.drawString(s1, 20, height - 29, -1);

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}