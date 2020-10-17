package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GuiCredits extends GuiScreen {
    public GuiScreen parent;

    public static ArrayList altList = new ArrayList();
    public static ArrayList guiSlotList = new ArrayList();
    public static File altFile;

    FontManager fM = Client.main().fontMgr();

    private GuiScreen parentScreen;

    public File getAltFile() {
        return altFile;
    }

    public GuiCredits(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(1, width / 2 - 100, height - 30, 200, 20, "Zurück"));
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            mc.displayGuiScreen(parent);
        }
    }

    public void drawScreen(int posX, int posY, float f) {
        drawString(mc.fontRendererObj, "", width / 2 - 100, 79, 10526880);

        ScaledResolution sr = new ScaledResolution(Wrapper.mc);
        if (Keyboard.isKeyDown(1)) {
            mc.displayGuiScreen(parent);
        }

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.main().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());

        int x = width / 2 - 150;
        int darkGray = -15658735;
        int lightGray = -15066598;
        RenderUtils.drawRect(5, 5, width - 5, height - 5, new Color(0, 0, 0, 155).getRGB());

        String title = "Credits";
        fM.cabin35.drawStringWithShadow(title, width / 2 - fM.cabin35.getStringWidth(title) / 2, 10, -1);

        String c1 = "HeroCode - ClickGUI API";
        String c2 = "Kriteax - Client Port from 1.8 to 1.8.8 | General Help";
        String c3 = "Sam - Font System | General Help";
        String c4 = "Nero - Fonts | Crasher | Circle ESP";
        fM.cabin23.drawStringWithShadow(c1, width / 2 - fM.cabin23.getStringWidth(c1) / 2, height / 2 - 30, -1);
        fM.cabin23.drawStringWithShadow(c2, width / 2 - fM.cabin23.getStringWidth(c2) / 2, height / 2 - 10, -1);
        fM.cabin23.drawStringWithShadow(c3, width / 2 - fM.cabin23.getStringWidth(c3) / 2, height / 2 + 10, -1);
        fM.cabin23.drawStringWithShadow(c4, width / 2 - fM.cabin23.getStringWidth(c4) / 2, height / 2 + 30, -1);

        super.drawScreen(posX, posY, f);
    }
}