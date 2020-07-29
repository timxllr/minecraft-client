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

    public void initFonts() {
        rainbowVeins50 = new UnicodeFontRenderer(getFont("Rainbow Veins", 50, FontExtension.TTF), true, 8);
        rainbowVeins80 = new UnicodeFontRenderer(getFont("Rainbow Veins", 80, FontExtension.TTF), true, 8);
        verdana19 = new UnicodeFontRenderer(getFont("Verdana", 19, FontExtension.TTF), true, 8);
        verdana17 = new UnicodeFontRenderer(getFont("Verdana", 17, FontExtension.TTF), true, 8);
        arialBold15 = new UnicodeFontRenderer(getFont("Arial Bold", 15, FontExtension.TTF), true, 8);
        verdana23 = new UnicodeFontRenderer(getFont("Verdana", 23, FontExtension.TTF), true, 8);
        iconsSouthSt14 = new UnicodeFontRenderer(getFont("Icons South St", 14, FontExtension.TTF), true, 8);
        modernpics22 = new UnicodeFontRenderer(getFont("modernpics", 22, FontExtension.OTF), true, 8);
        glyphyxOneNF19 = new UnicodeFontRenderer(getFont("GlyphyxOneNF", 19, FontExtension.TTF), true, 8);
        centuryGothic45 = new UnicodeFontRenderer(getFont("Century Gothic", 45, FontExtension.TTF), true, 8);
        centuryGothic30 = new UnicodeFontRenderer(getFont("Century Gothic", 30, FontExtension.TTF), true, 8);
        centuryGothic20 = new UnicodeFontRenderer(getFont("Century Gothic", 20, FontExtension.TTF), true, 8);
        american100 = new UnicodeFontRenderer(getFont("American", 100, FontExtension.TTF), true, 8);
        american66 = new UnicodeFontRenderer(getFont("American", 66, FontExtension.TTF), true, 8);
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
