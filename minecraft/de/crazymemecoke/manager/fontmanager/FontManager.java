package de.crazymemecoke.manager.fontmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.InputStream;

public class FontManager {

    public UnicodeFontRenderer rainbowVeins50;
    public UnicodeFontRenderer verdana19;
    public UnicodeFontRenderer cabin19;
    public UnicodeFontRenderer comfortaa18;
    public UnicodeFontRenderer comfortaa20;
    public UnicodeFontRenderer comfortaa22;
    public UnicodeFontRenderer comfortaa40;
    public UnicodeFontRenderer comfortaa45;
    public UnicodeFontRenderer comfortaa50;
    public UnicodeFontRenderer notificationFont;
    public UnicodeFontRenderer american100;
    public UnicodeFontRenderer american66;
    public UnicodeFontRenderer verdana23;
    public UnicodeFontRenderer iconsSouthSt14;
    public UnicodeFontRenderer modernpics22;
    public UnicodeFontRenderer glyphyxOneNF19;
    public UnicodeFontRenderer centuryGothic45;
    public UnicodeFontRenderer centuryGothic30;
    public UnicodeFontRenderer centuryGothic20;
    public UnicodeFontRenderer statusFont;
    public UnicodeFontRenderer arialBold15;
    public UnicodeFontRenderer cabin18;
    public UnicodeFontRenderer cabin23;
    public UnicodeFontRenderer cabin35;
    public UnicodeFontRenderer bigKeyFont;
    public UnicodeFontRenderer verdana17;
    public UnicodeFontRenderer rainbowVeins80;
    public UnicodeFontRenderer bebasNeue20;
    public UnicodeFontRenderer bebasNeue25;
    public UnicodeFontRenderer bebasNeue28;
    public UnicodeFontRenderer bebasNeue30;
    public UnicodeFontRenderer bebasNeue35;
    public UnicodeFontRenderer bebasNeue40;
    public UnicodeFontRenderer bebasNeue45;
    public UnicodeFontRenderer bebasNeue50;

    public void initFonts() {
        bebasNeue20 = new UnicodeFontRenderer(getFont("BebasNeue", 20, FontExtension.OTF), true, 8);
        bebasNeue25 = new UnicodeFontRenderer(getFont("BebasNeue", 25, FontExtension.OTF), true, 8);
        bebasNeue28 = new UnicodeFontRenderer(getFont("BebasNeue", 28, FontExtension.OTF), true, 8);
        bebasNeue30 = new UnicodeFontRenderer(getFont("BebasNeue", 30, FontExtension.OTF), true, 8);
        bebasNeue35 = new UnicodeFontRenderer(getFont("BebasNeue", 35, FontExtension.OTF), true, 8);
        bebasNeue40 = new UnicodeFontRenderer(getFont("BebasNeue", 40, FontExtension.OTF), true, 8);
        bebasNeue45 = new UnicodeFontRenderer(getFont("BebasNeue", 45, FontExtension.OTF), true, 8);
        bebasNeue50 = new UnicodeFontRenderer(getFont("BebasNeue", 50, FontExtension.OTF), true, 8);
        rainbowVeins50 = new UnicodeFontRenderer(getFont("Rainbow Veins", 50, FontExtension.TTF), true, 8);
        rainbowVeins80 = new UnicodeFontRenderer(getFont("Rainbow Veins", 80, FontExtension.TTF), true, 8);
        comfortaa18 = new UnicodeFontRenderer(getFont("Comfortaa", 18, FontExtension.TTF), true, 8);
        comfortaa20 = new UnicodeFontRenderer(getFont("Comfortaa", 20, FontExtension.TTF), true, 8);
        comfortaa22 = new UnicodeFontRenderer(getFont("Comfortaa", 22, FontExtension.TTF), true, 8);
        comfortaa40 = new UnicodeFontRenderer(getFont("Comfortaa", 40, FontExtension.TTF), true, 8);
        comfortaa45 = new UnicodeFontRenderer(getFont("Comfortaa", 45, FontExtension.TTF), true, 8);
        comfortaa50 = new UnicodeFontRenderer(getFont("Comfortaa", 50, FontExtension.TTF), true, 8);
        cabin19 = new UnicodeFontRenderer(getFont("Cabin", 19, FontExtension.TTF), true, 8);
        cabin18 = new UnicodeFontRenderer(getFont("Cabin", 18, FontExtension.TTF), true, 8);
        cabin23 = new UnicodeFontRenderer(getFont("Cabin", 23, FontExtension.TTF), true, 8);
        cabin35 = new UnicodeFontRenderer(getFont("Cabin", 35, FontExtension.TTF), true, 8);
    }

    private static Font getFont(String name, int size, FontExtension fe) {
        Font font = null;
        try {
            InputStream ex = Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("client/fonts/" + name + "." + fe.name().toLowerCase())).getInputStream();
            font = Font.createFont(0, ex);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.  Using serif font.");
            font = new Font("default", 0, size);
        }
        return font;
    }

    public enum FontExtension {
        TTF, OTF;
    }
}
