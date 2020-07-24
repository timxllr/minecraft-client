package net.minecraft.client.gui;

import de.crazymemecoke.Client;
import de.crazymemecoke.ui.menus.ClientMenu;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {

    public void initGui() {
        int i = 24;
        int j = height / 4 + 48;

        addSingleplayerMultiplayerButtons(j, 24);
        buttonList.add(
                new GuiButton(0, width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        buttonList
                .add(new GuiButton(5, width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
    }

    private void addSingleplayerMultiplayerButtons(int height, int p_73969_2_) {
        buttonList
                .add(new GuiButton(1, width / 2 - 100, height, I18n.format("menu.singleplayer")));
        buttonList.add(new GuiButton(2, width / 2 - 100, height + p_73969_2_ * 1,
                I18n.format("menu.multiplayer")));
        buttonList.add(new GuiButton(6, width / 2 - 100, height + p_73969_2_ * 2,
                I18n.format(Client.getInstance().getClientName())));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if (button.id == 1) {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 5) {
            mc.shutdown();
        }
        if (button.id == 6) {
            mc.displayGuiScreen(new ClientMenu());
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
        Gui.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), new Color(28, 26, 28).getRGB());
        //mc.getTextureManager().bindTexture(new ResourceLocation(Client.getInstance().getClientBackground()));
        //Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
        //        width, height, sr.getScaledWidth(), sr.getScaledHeight());

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(
                MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scale(f, f, f);
        GlStateManager.popMatrix();

        String s = Client.getInstance().getClientName() + " " + Client.getInstance().getClientVersion();
        Client.getInstance().getFontManager().comfortaa20.drawString(s, 2, height - 12, -1);

        String s1 = "made by " + Client.getInstance().getClientAuthor();
        Client.getInstance().getFontManager().comfortaa20.drawString(s1, width - Client.getInstance().getFontManager().comfortaa20.getStringWidth(s1) - 2, height - 12, -1);

        String header = Client.getInstance().getClientName();
        Client.getInstance().getFontManager().comfortaa40.drawString(header, width / 2 - Client.getInstance().getFontManager().comfortaa40.getStringWidth(header) / 2, height / 4, Rainbow.rainbowNormal(1, 1).hashCode());

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}