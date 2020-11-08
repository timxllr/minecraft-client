package de.crazymemecoke.features.ui.guiscreens.altmanager;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.Colors;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class GuiTheAltening extends GuiScreen {
    public GuiScreen parent;

    private GuiScreen parentScreen;
    private GuiTextField token;

    public GuiTheAltening(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(0, width / 2 - 100, height - 185, 200, 20, "Token verwenden"));
        buttonList.add(new GuiButton(1, width / 2 - 100, height - 160, 200, 20, I18n.format("gui.cancel")));
        token = new GuiTextField(0, fontRendererObj, width / 2 - 100, height - 250, 200, 20);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                mc.displayGuiScreen(parent);
                break;
            }
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        token.textboxKeyTyped(typedChar, keyCode);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        token.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawScreen(int posX, int posY, float f) {
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
        RenderUtils.drawBorderedRect(width / 2 - 150, height / 2 - 150, width / 2 + 150, height / 2 + 150, 1, darkGray, lightGray);
        UnicodeFontRenderer comfortaa20 = Client.main().fontMgr().font("Comfortaa", 20, Font.PLAIN);
        String rectTitle = "REEDEM TOKEN (THEALTENING)";
        comfortaa20.drawString(rectTitle, width / 2 - comfortaa20.getStringWidth(rectTitle) / 2, height / 2 - 140, Colors.main().getGrey());
        String textBoxTitle = "TOKEN";
        comfortaa20.drawString(textBoxTitle, width / 2 - comfortaa20.getStringWidth(textBoxTitle) / 2, height - 265, -1);

        token.drawTextBox();
        super.drawScreen(posX, posY, f);
    }
}