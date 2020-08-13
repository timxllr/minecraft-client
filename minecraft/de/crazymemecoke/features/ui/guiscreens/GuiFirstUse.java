package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiFirstUse extends GuiScreen implements GuiYesNoCallback {

    public void initGui() {
        buttonList.add(new GuiButton(0, width / 2 - 50, 250, width / 2, height - 20, "Ok, verstanden!"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        UnicodeFontRenderer c22 = Client.instance().getFontManager().comfortaa22;
        UnicodeFontRenderer c50 = Client.instance().getFontManager().comfortaa50;

        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        drawGradientRect(0, 0, width, height, -2130706433, 16777215);
        drawGradientRect(0, 0, width, height, 0, Integer.MIN_VALUE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation(Client.instance().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(
                MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scale(f, f, f);
        GlStateManager.popMatrix();

        String s1 = "Willkommen!";
        String s2 = "Folgende Sachen musst du dir unbedingt merken (!):";
        String s3 = "RSHIFT - ClickGUI";
        String s4 = "LSHIFT + RSHIFT - Info HUD";
        String s5 = "Prefix: " + Client.instance().getClientPrefix();

        c50.drawStringWithShadow(s1, width / 2 - c50.getStringWidth(s1) / 2, 40, -1);
        c22.drawStringWithShadow(s2, width / 2 - c22.getStringWidth(s2) / 2, 80, -1);
        c22.drawStringWithShadow(s3, width / 2 - c22.getStringWidth(s3) / 2, 100, -1);
        c22.drawStringWithShadow(s4, width / 2 - c22.getStringWidth(s4) / 2, 110, -1);
        c22.drawStringWithShadow(s5, width / 2 - c22.getStringWidth(s5) / 2, 120, -1);

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}