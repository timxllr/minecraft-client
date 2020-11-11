package de.crazymemecoke;

import de.crazymemecoke.features.commands.Friend;
import de.crazymemecoke.features.modules.exploits.Crasher;
import de.crazymemecoke.features.ui.guiscreens.GuiFirstUse;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.clickguimanager.clickgui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.commandmanager.CommandManager;
import de.crazymemecoke.manager.eventmanager.EventManager;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.manager.particlemanager.FBP;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.render.Shader;
import net.minecraft.client.Minecraft;

import java.io.File;

public class Client {

    private static Client instance = new Client();
    private Minecraft mc = Minecraft.mc();

    private String clientName = "Vanity";
    private double clientVersion = 1.2;
    private String clientCoder = "CrazyMemeCoke";
    private String clientPrefix = ".";
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
    private ClickGUI clickgui;
    private FontManager fontManager;
    private File clientDir = new File(Minecraft.mc().mcDataDir + "/" + getClientName());
    private Friend friend;
    private Shader shader;
    private AltManager altManager;
    private EventManager eventManager;

    public long initTime = System.currentTimeMillis();

    public static Client main() {
        return instance;
    }

    public void startClient() {
        if (!clientDir.exists()) {
            clientDir.mkdir();
            mc.displayGuiScreen(new GuiFirstUse());
        }
        eventManager = new EventManager();
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
        new FBP().onStart();
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
    }

    public void onShutdown() {
        setmgr.saveSettings();
        moduleManager.saveModules();
        moduleManager.saveBinds();
        AltManager.saveAlts();

        Client.main().modMgr().getModule(Crasher.class).setState(false);
        NotifyUtil.debug("Disabled Crasher module");
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

    public ClickGUI getClickGui() {
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

}
