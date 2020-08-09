package de.crazymemecoke.manager.clickguimanager.clickgui.util;

import de.crazymemecoke.Client;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StringUtils;

public class FontUtil {
    private static FontRenderer fontRenderer = Client.instance().getFontManager().comfortaa20;

    public static int getStringWidth(String text) {
        return fontRenderer.getStringWidth(StringUtils.stripControlCodes(text));
    }

    public static int getFontHeight() {
        return fontRenderer.FONT_HEIGHT;
    }

    public static void drawString(String text, int x, int y, int color) {
        fontRenderer.drawString(text, x, y, color);
    }

    public static void drawAmbienString(String text, int x, int y, int color) {
        Client.instance().getFontManager().bebasNeue30.drawString(text, x, y, color);
    }

    public static void drawStringWithShadow(String text, double x, double y, int color) {
        fontRenderer.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public static void drawAmbienStringWithShadow(String text, double x, double y, int color) {
        Client.instance().getFontManager().bebasNeue28.drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public static void drawCenteredString(String text, int x, int y, int color) {
        drawString(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static void drawCenteredStringWithShadow(String text, double x, double y, int color) {
        drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static void drawTotalCenteredString(String text, int x, int y, int color) {
        drawString(text, x - fontRenderer.getStringWidth(text) / 2, y - fontRenderer.FONT_HEIGHT / 2, color);
    }

    public static void drawAmbienTotalCenteredString(String text, int x, int y, int color) {
        drawAmbienString(text, x - Client.instance().getFontManager().bebasNeue30.getStringWidth(text) / 2, y - Client.instance().getFontManager().bebasNeue30.FONT_HEIGHT / 2, color);
    }

    public static void drawTotalCenteredStringWithShadow(String text, double x, double y, int color) {
        drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2, y - fontRenderer.FONT_HEIGHT / 2F, color);
    }
}
