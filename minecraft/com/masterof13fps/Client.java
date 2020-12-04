package com.masterof13fps;

import com.masterof13fps.utils.render.Shader;
import com.masterof13fps.features.commands.impl.Friend;
import com.masterof13fps.features.modules.impl.exploits.Crasher;
import com.masterof13fps.features.ui.guiscreens.GuiFirstUse;
import com.masterof13fps.manager.altmanager.AltManager;
import com.masterof13fps.manager.clickguimanager.ClickGui;
import com.masterof13fps.manager.settingsmanager.SettingsManager;
import com.masterof13fps.features.commands.CommandManager;
import com.masterof13fps.manager.eventmanager.EventManager;
import com.masterof13fps.manager.fontmanager.FontManager;
import com.masterof13fps.features.modules.ModuleManager;
import com.masterof13fps.manager.particlemanager.FBP;
import com.masterof13fps.utils.LoginUtil;
import com.masterof13fps.utils.NotifyUtil;
import net.minecraft.client.Minecraft;

import java.io.File;

public class Client {

    private static Client instance = new Client();
    private Minecraft mc = Minecraft.mc();

    private String clientName = "Vanity";
    private double clientVersion = 1.2;
    private String clientCoder = "CrazyMemeCoke";
    private String clientPrefix = ".";
    private String clientPrefixWorded = "Punkt";
    private String fakeVer = "OptiFine 1.8.8 HD_I7";
    private String clientBackground = "textures/client/background.jpg";
    private String clientIcon = "textures/client/icon.png";
    private String wurstWatermark = "textures/client/wurst.png";

    private String ambienWatermark = "textures/client/ambien-logo.png";

    private String shaderLoc = "textures/client/shader/";
    private String clientChangelog = "https://github.com/RealFantaCoke/minecraft-client/commits/master";
    private ModuleManager moduleManager;

    private CommandManager commandManager;
    private SettingsManager setmgr;
    private ClickGui clickgui;
    private FontManager fontManager;
    private File clientDir = new File(Minecraft.mc().mcDataDir + "/" + getClientName());
    private Friend friend;
    private Shader shader;
    private AltManager altManager;
    private EventManager eventManager;
    private LoginUtil loginUtil;

    public long initTime = System.currentTimeMillis();

    public static Client main() {
        return instance;
    }

    public void startClient() {
        if (!clientDir.exists()) {
            try{
                clientDir.mkdir();
                NotifyUtil.debug("Client-Ordner wurde erstellt!");
            }catch(Exception e) {
                NotifyUtil.debug("Client-Ordner konnte nicht erstellt werden!");
            }

            mc.displayGuiScreen(new GuiFirstUse());
            NotifyUtil.debug("GuiScreen 'FirstUse' wurde aufgerufen!");
        }
        loginUtil = new LoginUtil();
        NotifyUtil.debug("LoginUtil geladen!");
        eventManager = new EventManager();
        NotifyUtil.debug("EventManager geladen!");
        fontManager = new FontManager();
        NotifyUtil.debug("FontManager geladen!");
        fontManager.initFonts();
        NotifyUtil.debug("Schriftarten initialisiert!");
        setmgr = new SettingsManager();
        NotifyUtil.debug("SettingsManager geladen!");
        setmgr.loadSettings();
        NotifyUtil.debug("Einstellungen geladen!");
        moduleManager = new ModuleManager();
        NotifyUtil.debug("ModuleManager geladen!");
        moduleManager.loadModules();
        NotifyUtil.debug("Modules geladen!");
        moduleManager.loadBinds();
        NotifyUtil.debug("Keybinds geladen!");
        commandManager = new CommandManager();
        NotifyUtil.debug("CommandManager geladen!");
        AltManager.loadAlts();
        NotifyUtil.debug("Accounts geladen!");
        clickgui = new ClickGui();
        NotifyUtil.debug("ClickGUI geladen!");
        new FBP().onStart();
        NotifyUtil.debug("Partikelsystem geladen!");
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
        NotifyUtil.debug("ShutdownHook geladen!");

        NotifyUtil.debug("CLIENT VOLLSTÄNDIG GELADEN!");
    }

    public void onShutdown() {
        setmgr.saveSettings();
        moduleManager.saveModules();
        moduleManager.saveBinds();
        AltManager.saveAlts();

        Client.main().modMgr().getModule(Crasher.class).setState(false);
        NotifyUtil.debug("[ModuleManager] Crasher deaktiviert");
    }

    public File getClientDir() {
        return clientDir;
    }

    public EventManager eventMgr() {
        return eventManager;
    }

    public ModuleManager modMgr() {
        return moduleManager;
    }

    public String getClientName() {
        return clientName;
    }

    public double getClientVersion() {
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

    public ClickGui getClickGui() {
        return clickgui;
    }

    public String getClientPrefix() {
        return clientPrefix;
    }

    public FontManager fontMgr() {
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

    public String getWurstWatermark() {
        return wurstWatermark;
    }

    public String getAmbienWatermark() {
        return ambienWatermark;
    }

    public long getInitTime() {
        return initTime;
    }

    public String getFakeVer() {
        return fakeVer;
    }

    public static Client getInstance() {
        return instance;
    }

    public String getClientPrefixWorded() {
        return clientPrefixWorded;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public SettingsManager getSetmgr() {
        return setmgr;
    }

    public ClickGui getClickgui() {
        return clickgui;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public LoginUtil getLoginUtil() {
        return loginUtil;
    }
}
