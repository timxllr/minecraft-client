package de.crazymemecoke;

import de.Hero.clickgui.ClickGUI;
import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;
import de.crazymemecoke.command.CommandManager;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.module.ModuleManager;
import de.crazymemecoke.ui.GuiIngameHook;
import de.crazymemecoke.ui.alts.AltManager;
import de.crazymemecoke.ui.fontmanager.FontManager;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.io.File;

public class Client {

    private final static Client instance = new Client();
    private final Minecraft mc = Minecraft.getMinecraft();

    private final Color clientColor = new Color(255, 124, 25, 255);
    private final Color clientColorDarker = new Color(183, 74, 23, 255);

    // Naming Stuff
    private final String clientName = "Lifetime";
    private final String clientNamePrefix = "L";
    private final String clientVersion = "b1";
    private final String clientAuthor = "CrazyMemeCoke";
    private final String clientPrefix = ".";

    // Paths of Files
    private final String clientBackground = "client/background.jpg";

    private File raidriarDir;
    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private AltManager altManager;
    private SettingsManager setmgr;
    private ClickGUI clickgui;
    //private CSGOGui clickgui;
    private FontManager fontManager;
    private File clientDir;
    private GuiIngameHook guiIngameHook;

    private Setting colorMode;

    public void startClient() {
        clientDir = new File(Minecraft.getMinecraft().mcDataDir + "/" + getClientName());
        if (!clientDir.exists()) {
            clientDir.mkdir();
        }

        fontManager = new FontManager();
        fontManager.initFonts();

        setmgr = new SettingsManager();
        moduleManager = new ModuleManager();
        setmgr.loadSettings();

        moduleManager.loadModules();
        moduleManager.loadBinds();
        colorMode = getSetmgr().getSettingByName("Color Scheme", getModuleManager().getModByName("HUD"));

        commandManager = new CommandManager();

        AltManager.loadAlts();
        raidriarDir = new File(Minecraft.getMinecraft().mcDataDir + File.separator + getClientName());
        //clickgui = new CSGOGui();
        clickgui = new ClickGUI();
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
    }

    public static final Client getInstance() {
        return instance;
    }

    public File getClientDir() {
        return clientDir;
    }

    public void onShutdown() {
        setmgr.saveSettings();
        moduleManager.saveModules();
        moduleManager.saveBinds();
        AltManager.saveAlts();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getClientAuthor() {
        return clientAuthor;
    }

    public String getClientBackground() {
        return clientBackground;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Minecraft getMc() {
        return mc;
    }

    public SettingsManager getSetmgr() {
        return setmgr;
    }

    public ClickGUI getClickGui() {
        return clickgui;
    }

    public String getClientPrefix() {
        return clientPrefix;
    }

    public Color getClientColor() {
        return clientColor;
    }

    public Color getClientColorDarker() {
        return clientColorDarker;
    }

    public Setting getSettings(String setting, Module mod) {
        return Client.getInstance().getSetmgr().getSettingByName(setting, mod);
    }

    public Setting getColorMode() {
        return colorMode;
    }

    public FontManager getFontManager() {
        return fontManager;
    }
}
