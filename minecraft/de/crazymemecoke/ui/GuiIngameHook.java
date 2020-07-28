package de.crazymemecoke.ui;

import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.module.modules.combat.Aura;
import de.crazymemecoke.ui.guiscreens.ClientManager;
import de.crazymemecoke.ui.tabgui.TabGUI;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class GuiIngameHook extends GuiIngame {

    Minecraft mc = Wrapper.mc;

    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        if (!(Client.getInstance().getModuleManager().getModByName("Invis").getState())) {
            Display.setTitle(Client.getInstance().getClientName() + " " + Client.getInstance().getClientVersion() + " | made by " + Client.getInstance().getClientAuthor());
            if (Client.getInstance().getModuleManager().getModByName("HUD").getState()) {
                if (Client.getInstance().getSetmgr().getSettingByName("Developer Mode", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
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
        if (Client.getInstance().getSetmgr().getSettingByName("Watermark", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderWatermark();
        }
        if (Client.getInstance().getSetmgr().getSettingByName("TabGUI", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderTabGUI();
        }
        if (Client.getInstance().getSetmgr().getSettingByName("ArrayList", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderArrayList();
        }
        if (Client.getInstance().getSetmgr().getSettingByName("Target HUD", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            renderTargetHUD();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
            mc.displayGuiScreen(new ClientManager());
        }
    }

    private void renderTargetHUD() {
        ScaledResolution s = new ScaledResolution(mc);

        if (!(Aura.target == null) && Aura.target instanceof EntityPlayer) {
            RenderUtils.drawRect(s.getScaledWidth() / 2 - 130, s.getScaledHeight() / 2 - 50, s.getScaledWidth() / 2 + 130, s.getScaledHeight() / 2 + 50, new Color(0, 0, 0, 110).getRGB());

            EntityPlayer p = (EntityPlayer) Aura.target;
            Client.getInstance().getFontManager().cabin23.drawStringWithShadow("Spieler: " + p.getName(), s.getScaledWidth() / 2 - 125, s.getScaledHeight() / 2 - 45, -1);
            Client.getInstance().getFontManager().cabin23.drawStringWithShadow("Leben: " + p.getHealth() + " / " + p.getMaxHealth(), s.getScaledWidth() / 2 - 125, s.getScaledHeight() / 2 - 35, -1);

            ItemStack i = p.getCurrentEquippedItem();
            if (i == null) {
                Client.getInstance().getFontManager().cabin23.drawStringWithShadow("Item: Kein Item", s.getScaledWidth() / 2 - 125, s.getScaledHeight() / 2 - 25, -1);
            } else {
                Client.getInstance().getFontManager().cabin23.drawStringWithShadow("Item: " + i.getDisplayName(), s.getScaledWidth() / 2 - 125, s.getScaledHeight() / 2 - 25, -1);
            }

            // TODO: Player Model fixen (current state: not working)
            // GuiInventory.drawEntityOnScreen(51, 75, 30, (float) (51), (float) (75 - 50), p);

        }
    }

    private void renderWatermark() {
        ScaledResolution s = new ScaledResolution(mc);
        Client.getInstance().getFontManager().comfortaa40.drawString(Client.getInstance().getClientName(), s.getScaledWidth() - 85, 2, Rainbow.rainbow(1, 1).getRGB());
    }

    private void renderTabGUI() {
        TabGUI gui = new TabGUI(mc);
        gui.drawGui(5, 5, 55);
    }

    private void renderArrayList() {
        ScaledResolution s = new ScaledResolution(mc);
        if (!Client.getInstance().getSetmgr().getSettingByName("Watermark", Client.getInstance().getModuleManager().getModByName("HUD")).getValBoolean()) {
            int yDist = 1;
            for (Module m : Client.getInstance().getModuleManager().modules) {
                {
                    if (m.getState() && !m.isCategory(Category.GUI)) {
                        Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                yDist, Rainbow.rainbow(1, 1).getRGB());
                        yDist += 10;
                    }
                }
            }
        } else {
            int yDist = 20;
            for (Module m : Client.getInstance().getModuleManager().modules) {
                {
                    if (m.getState() && !m.isCategory(Category.GUI)) {
                        Client.getInstance().getFontManager().comfortaa20.drawString(m.getName(), s.getScaledWidth() - Client.getInstance().getFontManager().comfortaa20.getStringWidth(m.getName()) - 4,
                                yDist, Rainbow.rainbow(1, 1).getRGB());
                        yDist += 10;
                    }
                }
            }
        }
    }
}