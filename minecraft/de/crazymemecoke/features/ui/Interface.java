package de.crazymemecoke.features.ui;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.modules.combat.Aura;
import de.crazymemecoke.features.ui.guiscreens.clienthelper.GuiClientHelper;
import de.crazymemecoke.features.ui.tabgui.TabGUI;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class Interface extends GuiIngame {

    Minecraft mc = Wrapper.mc;
    FontManager font = Client.instance().getFontManager();

    public Interface(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        if (!(Client.instance().modManager().getByName("Invis").getState())) {
            Display.setTitle(Client.instance().getClientName() + " " + Client.instance().getClientVersion() + " | made by " + Client.instance().getClientCoder());
            if (Client.instance().modManager().getByName("HUD").getState()) {
                if (Client.instance().getSetmgr().getSettingByName("Developer Mode", Client.instance().modManager().getByName("HUD")).getBool()) {
                    doRenderStuff();
                } else {
                    if (mc.currentScreen == null && !mc.gameSettings.showDebugInfo) {
                        doRenderStuff();
                    }
                }
            }
        } else {
            Display.setTitle("Minecraft 1.8.8");
        }
    }

    private void doRenderStuff() {
        if (Client.instance().getSetmgr().getSettingByName("Watermark", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderWatermark();
        }
        if (Client.instance().getSetmgr().getSettingByName("TabGUI", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderTabGUI();
        }
        if (Client.instance().getSetmgr().getSettingByName("ArrayList", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderArrayList();
        }
        if (Client.instance().getSetmgr().getSettingByName("Target HUD", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderTargetHUD();
        }
        if (Client.instance().getSetmgr().getSettingByName("KeyStrokes", Client.instance().modManager().getByName("HUD")).getBool()) {
            renderKeyStrokes();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            mc.displayGuiScreen(new GuiClientHelper());
        }
    }

    private void renderKeyStrokes() {
        ScaledResolution s = new ScaledResolution(mc);


    }

    private void renderTargetHUD() {
        ScaledResolution s = new ScaledResolution(mc);

        if (!(Aura.currentTarget == null) && Aura.currentTarget instanceof EntityPlayer && Client.instance().getSetmgr().getSettingByName("Target HUD", Client.instance().modManager().getByName("HUD")).getBool()) {
            RenderUtils.drawRect(s.width() / 2 - 130, s.height() / 2 - 50, s.width() / 2 + 130, s.height() / 2 + 50, new Color(0, 0, 0, 110).getRGB());

            EntityPlayer p = (EntityPlayer) Aura.currentTarget;
            Client.instance().getFontManager().cabin23.drawStringWithShadow("Spieler: " + p.getName(), s.width() / 2 - 125, s.height() / 2 - 45, -1);
            Client.instance().getFontManager().cabin23.drawStringWithShadow("Leben: " + p.getHealth() + " / " + p.getMaxHealth(), s.width() / 2 - 125, s.height() / 2 - 35, -1);

            ItemStack i = p.getCurrentEquippedItem();
            if (i == null) {
                Client.instance().getFontManager().cabin23.drawStringWithShadow("Item: Kein Item", s.width() / 2 - 125, s.height() / 2 - 25, -1);
            } else {
                Client.instance().getFontManager().cabin23.drawStringWithShadow("Item: " + i.getDisplayName(), s.width() / 2 - 125, s.height() / 2 - 25, -1);
            }

            // TODO: Player Model fixen (current state: not working)
            // GuiInventory.drawEntityOnScreen(51, 75, 30, (float) (51), (float) (75 - 50), p);

        }
    }

    private void renderWatermark() {
        ScaledResolution s = new ScaledResolution(mc);

        int width = 65;
        int height = 65;

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.instance().getClientIcon()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, s.width(), s.height(), width, height, s.width(), s.height());
    }

    private void renderTabGUI() {
        TabGUI gui = new TabGUI(mc);

        if (Client.instance().getSetmgr().getSettingByName("Watermark", Client.instance().modManager().getByName("HUD")).getBool()) {
            gui.drawGui(3, 68, 63);
        } else {
            gui.drawGui(1, 0, 63);
        }
    }

    private void renderArrayList() {
        ScaledResolution s = new ScaledResolution(mc);
        String module;
        int stringY = 2;
        int rectY = 1;

        String mode = Client.instance().getSetmgr().getSettingByName("ArrayList Rect Mode", Client.instance().modManager().getByName("HUD")).getMode();

        for (Module m : Client.instance().modManager().modules) {
            module = m.getName();
            if (m.getState() && !m.isCategory(Category.GUI)) {
                if (Client.instance().getSetmgr().getSettingByName("ArrayList Background", Client.instance().modManager().getByName("HUD")).getBool()) {
                    if (mode.equalsIgnoreCase("Left")) {
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 5, (rectY - 2), s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 3, (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                        Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                    } else if (mode.equalsIgnoreCase("Right")) {
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 5, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                        RenderUtils.drawRect(s.width() - 3, (rectY - 2), s.width(), (rectY + 10), Rainbow.rainbow(1, 1).getRGB());
                        Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 4, stringY, Rainbow.rainbow(1, 1).getRGB());
                    } else if (mode.equalsIgnoreCase("None")) {
                        RenderUtils.drawRect(s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 3, (rectY - 2), s.width(), (rectY + 10), new Color(0, 0, 0, 150).getRGB());
                        Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                    }
                } else {
                    Client.instance().getFontManager().comfortaa20.drawString(module, s.width() - Client.instance().getFontManager().comfortaa20.getStringWidth(module) - 1, stringY, Rainbow.rainbow(1, 1).getRGB());
                }
                rectY += 12;
                stringY += 12;
            }
        }
    }
}