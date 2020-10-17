package de.crazymemecoke.manager.fontmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private static final Map<String, UnicodeFontRenderer> FONT_RENDERER_HASH_MAP = new HashMap<>();
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
    public UnicodeFontRenderer verdana23;
    public UnicodeFontRenderer iconsSouthSt14;
    public UnicodeFontRenderer modernpics22;
    public UnicodeFontRenderer glyphyxOneNF19;
    public UnicodeFontRenderer statusFont;
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
    public UnicodeFontRenderer roboto20;
    public UnicodeFontRenderer roboto25;
    public UnicodeFontRenderer raleWay20;
    public UnicodeFontRenderer raleWay25;
    public UnicodeFontRenderer esp30;
    public UnicodeFontRenderer esp35;
    public UnicodeFontRenderer ambien20;
    public UnicodeFontRenderer ambien25;
    public UnicodeFontRenderer ambien30;
    public UnicodeFontRenderer ambien35;
    public UnicodeFontRenderer ambien40;
    public UnicodeFontRenderer ambien45;
    public UnicodeFontRenderer ambien50;
    public UnicodeFontRenderer vortex20;
    public UnicodeFontRenderer vortex25;
    public UnicodeFontRenderer vortex30;
    public UnicodeFontRenderer vortex35;
    public UnicodeFontRenderer vortex40;
    public UnicodeFontRenderer vortex45;
    public UnicodeFontRenderer vortex50;
    public UnicodeFontRenderer arial20;

    public void initFonts() {
        bebasNeue20 = new UnicodeFontRenderer(font("BebasNeue", 20, FontExtension.OTF), true, 8);
        bebasNeue25 = new UnicodeFontRenderer(font("BebasNeue", 25, FontExtension.OTF), true, 8);
        bebasNeue28 = new UnicodeFontRenderer(font("BebasNeue", 28, FontExtension.OTF), true, 8);
        bebasNeue30 = new UnicodeFontRenderer(font("BebasNeue", 30, FontExtension.OTF), true, 8);
        bebasNeue35 = new UnicodeFontRenderer(font("BebasNeue", 35, FontExtension.OTF), true, 8);
        bebasNeue40 = new UnicodeFontRenderer(font("BebasNeue", 40, FontExtension.OTF), true, 8);
        bebasNeue45 = new UnicodeFontRenderer(font("BebasNeue", 45, FontExtension.OTF), true, 8);
        bebasNeue50 = new UnicodeFontRenderer(font("BebasNeue", 50, FontExtension.OTF), true, 8);
        rainbowVeins50 = new UnicodeFontRenderer(font("Rainbow Veins", 50, FontExtension.TTF), true, 8);
        rainbowVeins80 = new UnicodeFontRenderer(font("Rainbow Veins", 80, FontExtension.TTF), true, 8);
        comfortaa18 = new UnicodeFontRenderer(font("Comfortaa", 18, FontExtension.TTF), true, 8);
        comfortaa20 = new UnicodeFontRenderer(font("Comfortaa", 20, FontExtension.TTF), true, 8);
        comfortaa22 = new UnicodeFontRenderer(font("Comfortaa", 22, FontExtension.TTF), true, 8);
        comfortaa40 = new UnicodeFontRenderer(font("Comfortaa", 40, FontExtension.TTF), true, 8);
        comfortaa45 = new UnicodeFontRenderer(font("Comfortaa", 45, FontExtension.TTF), true, 8);
        comfortaa50 = new UnicodeFontRenderer(font("Comfortaa", 50, FontExtension.TTF), true, 8);
        cabin19 = new UnicodeFontRenderer(font("Cabin", 19, FontExtension.TTF), true, 8);
        cabin18 = new UnicodeFontRenderer(font("Cabin", 18, FontExtension.TTF), true, 8);
        cabin23 = new UnicodeFontRenderer(font("Cabin", 23, FontExtension.TTF), true, 8);
        cabin35 = new UnicodeFontRenderer(font("Cabin", 35, FontExtension.TTF), true, 8);
        roboto20 = new UnicodeFontRenderer(font("Roboto-Light", 20, FontExtension.TTF), true, 8);
        roboto25 = new UnicodeFontRenderer(font("Roboto-Light", 25, FontExtension.TTF), true, 8);
        raleWay20 = new UnicodeFontRenderer(font("Raleway-Light", 20, FontExtension.TTF), true, 8);
        raleWay25 = new UnicodeFontRenderer(font("Raleway-Light", 25, FontExtension.TTF), true, 8);
        esp30 = new UnicodeFontRenderer(font("esp", 30, FontExtension.TTF), true, 8);
        esp35 = new UnicodeFontRenderer(font("esp", 35, FontExtension.TTF), true, 8);
        ambien20 = new UnicodeFontRenderer(font("fifawelcome1.3", 20, FontExtension.TTF), true, 8);
        ambien25 = new UnicodeFontRenderer(font("fifawelcome1.3", 25, FontExtension.TTF), true, 8);
        ambien30 = new UnicodeFontRenderer(font("fifawelcome1.3", 30, FontExtension.TTF), true, 8);
        ambien35 = new UnicodeFontRenderer(font("fifawelcome1.3", 35, FontExtension.TTF), true, 8);
        ambien40 = new UnicodeFontRenderer(font("fifawelcome1.3", 40, FontExtension.TTF), true, 8);
        ambien45 = new UnicodeFontRenderer(font("fifawelcome1.3", 45, FontExtension.TTF), true, 8);
        ambien50 = new UnicodeFontRenderer(font("fifawelcome1.3", 50, FontExtension.TTF), true, 8);
        vortex20 = new UnicodeFontRenderer(font("DANUBE", 20, FontExtension.TTF), true, 8);
        vortex25 = new UnicodeFontRenderer(font("DANUBE", 25, FontExtension.TTF), true, 8);
        vortex30 = new UnicodeFontRenderer(font("DANUBE", 30, FontExtension.TTF), true, 8);
        vortex35 = new UnicodeFontRenderer(font("DANUBE", 35, FontExtension.TTF), true, 8);
        vortex40 = new UnicodeFontRenderer(font("DANUBE", 40, FontExtension.TTF), true, 8);
        vortex45 = new UnicodeFontRenderer(font("DANUBE", 45, FontExtension.TTF), true, 8);
        vortex50 = new UnicodeFontRenderer(font("DANUBE", 50, FontExtension.TTF), true, 8);
        arial20 = new UnicodeFontRenderer(font("Arial", 20, FontExtension.TTF), true, 8);
    }

    private static Font font(String name, int size, FontExtension fe) {
        Font font = null;
        try {
            InputStream ex = Minecraft.mc().getResourceManager()
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

    /**
     * @param fontName font identifying name
     * @param size     the size of the font
     * @param fontType Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC (Bold and Italic)
     * @return new font instance
     * @author Trol
     * <p>
     * Gets the font, if absent creates a new one and puts it into a hashmap
     */
    public UnicodeFontRenderer font(String fontName, int size, int fontType) {
        final String id = fontName + "-" + size + "-" + fontType;
        return FONT_RENDERER_HASH_MAP.computeIfAbsent(id, (idx) -> new UnicodeFontRenderer(new Font(fontName, fontType, size), true, 8));
    }
}