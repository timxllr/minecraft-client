package de.crazymemecoke.utils.render;

import java.awt.*;

public enum Colors {
    BLACK(-16711423),
    BLUE(-16723258),
    DARKBLUE(-15698006),
    GREEN(-9581017),
    DARKGREEN(-11231458),
    WHITE(-65794),
    AQUA(-14163205),
    DARKAQUA(-16548724),
    GREY(-6710887),
    DARKGREY(-12303292),
    RED(-43691),
    DARKRED(-7864320),
    ORANGE(-21931),
    DARKORANGE(-7846912),
    YELLOW(-171),
    DARKYELLOW(-7829504),
    MAGENTA(-43521),
    DARKMAGENTA(-7864184);

    public static final int ambienOldBlueColor = new Color(32, 188, 240).getRGB();
    public static final int ambienNewBlueColor = new Color(0x0090ff).getRGB();
    public static final int ambienNewDarkGreyColor = new Color(0x20252b).getRGB();
    public static final int greyColor = new Color(168, 167, 169).getRGB();
    public static final int vortexRedColor = new Color(0xE37974).getRGB();
    public static final int suicideBlueGreyColor = new Color(0x1c293a).getRGB();
    public static final int suicideBlueColor = new Color(0x0993b0).getRGB();
    public static final int suicideDarkBlueGreyColor = new Color(0x1a1f24).getRGB();
    public static final int apinityBlueColor = new Color(0x4c80ee).getRGB();
    public static final int apinityGreyColor = new Color(0x312e30).getRGB();
    public static final int huzuniBlueColor = new Color(0x00a5ff).getRGB();
    public static final int huzuniGreyColor = new Color(0x191923).getRGB();
    public static final int nodusPurpleColor = new Color(0x5a0454).getRGB();
    public static final int nodusTealColor = new Color(0x94d6ce).getRGB();
    public static final int saintDarkBlueColor = new Color(0x11274c).getRGB();
    public static final int saintDarkTealColor = new Color(0x13a4a1).getRGB();
    public static final int saintOrangeColor = new Color(0xE0B71F).getRGB();
    public static final int icarusOldOrangeColor = new Color(0xdb9615).getRGB();
    public static final int icarusOldGreyColor = new Color(0x4a586a).getRGB();
    public static final int icarusNewBlueColor = new Color(0x92c2cc).getRGB();
    public static final int icarusNewGreyColor = new Color(0, 0, 0, 100).getRGB();
    public static final int heroGreenColor = new Color(0x1ab753).getRGB();
    public static final int heroGreyColor = new Color(0, 0, 0, 70).getRGB();
    public static final int vantaGreyColor = new Color(0x2e3b4a).getRGB();
    public static final int vantaBlueColor = new Color(0x0198d6).getRGB();

    public int c;

    Colors(int co) {
        this.c = co;
    }
}
