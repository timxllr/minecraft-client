package de.crazymemecoke.manager.clickguimanager.clickgui.elements;

import de.crazymemecoke.manager.clickguimanager.clickgui.Panel;
import de.crazymemecoke.manager.clickguimanager.clickgui.elements.menu.ElementCheckBox;
import de.crazymemecoke.manager.clickguimanager.clickgui.elements.menu.ElementComboBox;
import de.crazymemecoke.manager.clickguimanager.clickgui.elements.menu.ElementSlider;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.ColorUtil;
import de.crazymemecoke.manager.clickguimanager.clickgui.util.FontUtil;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Made by HeroCode it's free to use but you have to credit me
 *
 * @author HeroCode
 */
public class ModuleButton {
    public Module mod;
    public ArrayList<Element> menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended = false;
    public boolean listening = false;

    /*
     * Konstrukor
     */
    public ModuleButton(Module imod, Panel pl) {
        mod = imod;
        height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2;
        parent = pl;
        menuelements = new ArrayList<>();
        if (Client.getInstance().getSetmgr().getSettingsByMod(imod) != null)
            for (Setting s : Client.getInstance().getSetmgr().getSettingsByMod(imod)) {
                if (s.isCheck()) {
                    menuelements.add(new ElementCheckBox(this, s));
                } else if (s.isSlider()) {
                    menuelements.add(new ElementSlider(this, s));
                } else if (s.isCombo()) {
                    menuelements.add(new ElementComboBox(this, s));
                }
            }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color temp = ColorUtil.getClickGUIColor();
        int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();

        if (isHovered(mouseX, mouseY)) {
            RenderUtils.drawRect(x - 2, y, x + width + 2, y + height + 1, 0x55111111);
        }

        if (mod.isEnabled()) {
            FontUtil.drawTotalCenteredStringWithShadow(mod.getName(), x + width / 2, y + 1 + height / 2, Rainbow.rainbow(1, 1).getRGB());
        } else {
            FontUtil.drawTotalCenteredStringWithShadow(mod.getName(), x + width / 2, y + 1 + height / 2, 0xffafafaf);
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!isHovered(mouseX, mouseY))
            return false;

        /*
         * Rechtsklick, wenn ja dann Module togglen,
         */
        if (mouseButton == 0) {
            mod.toggleModule();

            if (Client.getInstance().getSetmgr().getSettingByName("Sound", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValBoolean())
                Minecraft.getMinecraft().thePlayer.playSound("random.click", 0.5f, 0.5f);
        } else if (mouseButton == 1) {
            /*
             * Wenn ein Settingsmenu existiert dann sollen alle Settingsmenus geschlossen
             * werden und dieses ge�ffnet/geschlossen werden
             */
            if (menuelements != null && menuelements.size() > 0) {
                boolean b = !this.extended;
                //Client.getInstance().getClickGui().closeAllSettings();
                this.extended = b;

                if (Client.getInstance().getSetmgr().getSettingByName("Sound", Client.getInstance().getModuleManager().getModByName("ClickGUI")).getValBoolean())
                    if (extended)
                        Minecraft.getMinecraft().thePlayer.playSound("tile.piston.out", 1f, 1f);
                    else
                        Minecraft.getMinecraft().thePlayer.playSound("tile.piston.in", 1f, 1f);
            }
        }
		/*} else if (mouseButton == 2) {
			listening = true;
		}*/
        return true;
    }

    public boolean keyTyped(char typedChar, int keyCode) throws IOException {
        /*
         * Wenn listening, dann soll der n�chster Key (abgesehen 'ESCAPE') als Keybind
         * f�r mod danach soll nicht mehr gewartet werden!
         */
        if (listening) {
            ModuleManager moduleManager = Client.getInstance().getModuleManager();
            Module module = moduleManager.getModByName(mod.getName());
            int bind = moduleManager.convertKeyCodeToInt(mod.getName());
            module.setBind(bind);
            listening = false;
            return true;
        }
        return false;
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

}
