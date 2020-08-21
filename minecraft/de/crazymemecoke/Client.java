package de.crazymemecoke;

import de.crazymemecoke.features.commands.Friend;
import de.crazymemecoke.features.ui.guiscreens.GuiFirstUse;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.clickguimanager.clickgui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.commandmanager.CommandManager;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.utils.render.Shader;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.io.File;

public class Client {

    private final static Client instance = new Client();
    private final Minecraft mc = Minecraft.getMinecraft();

    private final String clientName = "Ambien";
    private final String clientVersion = "5.2";
    private final String clientCoder = "CrazyMemeCoke";
    private final String clientPrefix = ".";
    private final String clientBackground = "textures/client/background.jpg";
    private final String clientIcon = "textures/client/icon.png";
    private final String wurstWatermark = "textures/client/wurst.png";
    private final String shaderLoc = "textures/client/shader/";
    private final String clientChangelog = "https://github.com/RealFantaCoke/minecraft_client_1.8.8/commits/master";

    private final int ambienBlueColor = new Color(32, 188, 240).getRGB();
    private final int GreyColor = new Color(168, 167, 169).getRGB();
    private final int vortexRedColor = new Color(0xE37974).getRGB();
    private final int suicideBlueGreyColor = new Color(0x1c293a).getRGB();
    private final int suicideBlueColor = new Color(0x0993b0).getRGB();
    private final int suicideDarkBlueGreyColor = new Color(0x1a1f24).getRGB();
    private final int apinityBlueColor = new Color(0x4c80ee).getRGB();
    private final int apinityGreyColor = new Color(0x312e30).getRGB();
    private final int huzuniBlueColor = new Color(0x00a5ff).getRGB();
    private final int huzuniGreyColor = new Color(0x191923).getRGB();
    private final int nodusPurpleColor = new Color(0x5a0454).getRGB();
    private final int nodusTealColor = new Color(0x94d6ce).getRGB();
    private final int saintDarkBlueColor = new Color(0x11274c).getRGB();
    private final int saintDarkTealColor = new Color(0x13a4a1).getRGB();
    private final int saintOrangeColor = new Color(0xE0B71F).getRGB();
    private final int icarusOldOrangeColor = new Color(0xdb9615).getRGB();
    private final int icarusOldGreyColor = new Color(0x4a586a).getRGB();
    private final int icarusNewBlueColor = new Color(0x92c2cc).getRGB();
    private final int icarusNewGreyColor = new Color(0, 0, 0, 100).getRGB();

    private ModuleManager moduleManager;

    private CommandManager commandManager;

    private SettingsManager setmgr;
    private ClickGUI clickgui;
    private FontManager fontManager;
    private File clientDir;
    private Friend friend;
    private Shader shader;
    private AltManager altManager;

    public void startClient() {
        clientDir = new File(Minecraft.getMinecraft().mcDataDir + "/" + getClientName());
        if (!clientDir.exists()) {
            clientDir.mkdir();
            mc.displayGuiScreen(new GuiFirstUse());
        }

        fontManager = new FontManager();
        fontManager.initFonts();
        setmgr = new SettingsManager();
        moduleManager = new ModuleManager();
        setmgr.loadSettings();
        moduleManager.loadModules();
        moduleManager.loadBinds();
        commandManager = new CommandManager();
        AltManager.loadAlts();
        clickgui = new ClickGUI();
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
    }

    public static Client instance() {
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

    public ModuleManager modManager() {
        return moduleManager;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getClientCoder() {
        return clientCoder;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Minecraft getMc() {
        return mc;
    }

    public SettingsManager setMgr() {
        return setmgr;
    }

    public ClickGUI getClickGui() {
        return clickgui;
    }

    public String getClientPrefix() {
        return clientPrefix;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public String getClientChangelog() {
        return clientChangelog;
    }

    public String getClientBackground() {
        return clientBackground;
    }

    public Friend getFriend() {
        return friend;
    }

    public AltManager getAltManager() {
        return altManager;
    }

    public String getClientIcon() {
        return clientIcon;
    }

    public String getShaderLoc() {
        return shaderLoc;
    }

    public Shader getShader() {
        return shader;
    }

    public int getAmbienBlueColor() {
        return ambienBlueColor;
    }

    public int getGrey() {
        return GreyColor;
    }

    public int getVortexRedColor() {
        return vortexRedColor;
    }

    public int getSuicideBlueGreyColor() {
        return suicideBlueGreyColor;
    }

    public int getSuicideBlueColor() {
        return suicideBlueColor;
    }

    public int getSuicideDarkBlueGreyColor() {
        return suicideDarkBlueGreyColor;
    }

    public int getGreyColor() {
        return GreyColor;
    }

    public int getApinityGreyColor() {
        return apinityGreyColor;
    }

    public int getApinityBlueColor() {
        return apinityBlueColor;
    }

    public int getHuzuniBlueColor() {
        return huzuniBlueColor;
    }

    public int getHuzuniGreyColor() {
        return huzuniGreyColor;
    }

    public String getWurstWatermark() {
        return wurstWatermark;
    }

    public int getNodusPurpleColor() {
        return nodusPurpleColor;
    }

    public int getNodusTealColor() {
        return nodusTealColor;
    }

    public int getSaintDarkBlueColor() {
        return saintDarkBlueColor;
    }

    public int getSaintDarkTealColor() {
        return saintDarkTealColor;
    }

    public int getSaintOrangeColor() {
        return saintOrangeColor;
    }

    public int getIcarusNewBlueColor() {
        return icarusNewBlueColor;
    }

    public int getIcarusNewGreyColor() {
        return icarusNewGreyColor;
    }

    public int getIcarusOldGreyColor() {
        return icarusOldGreyColor;
    }

    public int getIcarusOldOrangeColor() {
        return icarusOldOrangeColor;
    }
}
