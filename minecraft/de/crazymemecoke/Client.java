package de.crazymemecoke;

import de.crazymemecoke.features.commands.Friend;
import de.crazymemecoke.features.ui.guiscreens.GuiFirstUse;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.clickguimanager.clickgui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.commandmanager.CommandManager;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.manager.notificationmanager.Notification;
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
    private final String shaderLoc = "textures/client/shader/";
    private final String clientChangelog = "https://github.com/RealFantaCoke/minecraft_client_1.8.8/commits/master";
    private final int ambienBlueColor = new Color(32, 188, 240).getRGB();
    private final int GreyColor = new Color(168, 167, 169).getRGB();
    private final int vortexRedColor = new Color(0xE37974).getRGB();
    private final int suicideBlueGreyColor = new Color(0x1c293a).getRGB();
    private final int suicideBlueColor = new Color(0x0993b0).getRGB();
    private final int suicideDarkBlueGreyColor = new Color(0x1a1f24).getRGB();

    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private SettingsManager setmgr;
    private ClickGUI clickgui;
    private FontManager fontManager;
    private File clientDir;
    private Friend friend;
    private Shader shader;
    private AltManager altManager;
    private Notification notification;

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

    public Notification getNotification() {
        return notification;
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
}
