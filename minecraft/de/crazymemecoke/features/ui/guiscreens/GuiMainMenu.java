package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.ui.guiscreens.altmanager.GuiAltManager;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {

    double onlineVer;
    double localVer = Client.main().getClientVersion();

    public void initGui() {
        buttonList.add(new GuiButton(0, width / 2 - 120, height / 2 - 60, 120, 20, I18n.format("menu.singleplayer")));
        buttonList.add(new GuiButton(1, width / 2 + 10, height / 2 - 60, 120, 20, I18n.format("menu.multiplayer")));
        buttonList.add(new GuiButton(2, width / 2 - 120, height / 2, 120, 20, I18n.format("menu.options")));
        buttonList.add(new GuiButton(3, width / 2 + 10, height / 2, 120, 20, I18n.format("menu.quit")));
        buttonList.add(new GuiButton(4, width / 2 - 120, height / 2 - 30, 120, 20, "AltManager"));
        buttonList.add(new GuiButton(5, width / 2 + 10, height / 2 - 30, 120, 20, "Credits"));

        try {
            String versionCheck = "https://pastebin.com/raw/tcuBTU3x";
            URL url = new URL(versionCheck);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            onlineVer = Double.parseDouble(reader.readLine());

            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        drawGradientRect(0, 0, width, height, -2130706433, 16777215);
        drawGradientRect(0, 0, width, height, 0, Integer.MIN_VALUE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(new ResourceLocation(Client.main().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(
                MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
        GlStateManager.scale(f, f, f);
        GlStateManager.popMatrix();

        /**
         * START OF DRAWING STUFF
         *
         * ORDER:
         * - Background for Buttons
         * - Background for Informations
         * - Client Name
         * - Current Playername
         * - Client Version
         * - Version Check [latest or not?] (soon)
         */

        UnicodeFontRenderer font1 = Client.main().fontMgr().font("esp", 50, Font.BOLD);
        UnicodeFontRenderer font2 = Client.main().fontMgr().font("esp", 20, Font.PLAIN);
        UnicodeFontRenderer font3 = Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN);

        RenderUtils.drawRect(width / 2 - 130, height / 2 - 70, width / 2 + 140, height / 2 + 30, new Color(55, 55, 55, 150).getRGB());

        RenderUtils.drawRect(0, 0, width, 26, new Color(0, 0, 0, 100).getRGB());

        String name = Client.main().getClientName();
        font1.drawStringWithShadow(name, sr.width() / 2 - font1.getStringWidth(name) / 2, 5, -1);

        String version = "Version: " + Client.main().getClientVersion() + " / Latest: " + onlineVer;
        font2.drawStringWithShadow(version, 2, 5, -1);

        String playerName = "Name: " + mc.session.getUsername();
        font2.drawStringWithShadow(playerName, 2, 16, -1);

        String outdatedVer = "Du benutzt eine veraltete Version! Update noch jetzt um die neusten Features zu erhalten!";
        String latestVer = "Du benutzt die neuste Version!";
        String newerVer = "Du benutzt eine neuere Version als erschienen ist - Zeitreisender?";

        if (onlineVer > localVer) {
            font3.drawStringWithShadow(outdatedVer, 2, sr.height() - 10, -1);
        }
        if (onlineVer == localVer) {
            font3.drawStringWithShadow(latestVer, 2, sr.height() - 10, -1);
        }
        if(onlineVer < localVer){
            font3.drawStringWithShadow(newerVer, 2, sr.height() - 10, -1);
        }


        /**
         * END OF DRAWING STUFF
         */

        float scale = 5.0F;
        GL11.glScalef(scale, scale, scale);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}