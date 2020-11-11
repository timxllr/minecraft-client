package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

public class GuiCredits extends GuiScreen {

    public GuiScreen parent;
    FontManager fM = Client.main().fontMgr();

    private GuiScreen parentScreen;

    public GuiCredits(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(1, width / 2 - 100, height - 30, 200, 20, "Zurück"));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(parentScreen);
        }
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
        ScaledResolution sr = new ScaledResolution(Wrapper.mc);

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.main().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());

        RenderUtils.drawRect(5, 0, width - 5, height, new Color(0, 0, 0, 155).getRGB());

        UnicodeFontRenderer cabin35 = fM.font("Cabin", 35, Font.PLAIN);
        UnicodeFontRenderer cabin23 = fM.font("Cabin", 23, Font.PLAIN);

        String title = "Credits";
        cabin35.drawStringWithShadow(title, width / 2 - cabin35.getStringWidth(title) / 2, 10, -1);

        String credits = "HeroCode - SettingsManager API\n\n" +
                "Kriteax - Client Port from 1.8 to 1.8.8 | General Help\n\n" +
                "MinecraftGEMA / Sam - Font System | General Help\n\n" +
                "Nero - Fonts | Crasher | Shaders | Design Ideas from Ambien\n\n" +
                "Kroko - Event System | ESP (Shader) | Aura Rotations | General Help\n\n" +
                "W4z3d - Hit Animations (Build / BlockHit) | PlayerModel Fixes | General Help\n\n" +
                "Deleteboys - TrailESP | MotionGraph | Advanced RainbowColor Method\n\n" +
                "superblaubeere27 - Shader System | Notification System\n\n" +
                "Vinii - Render Methods | ClickGUI API | General Help\n\n" +
                "EcstasyCode - General Help (with ParticleSystem)";
        cabin23.drawStringWithShadow(credits, 10, 40, -1);

        super.drawScreen(posX, posY, f);
    }
}