package de.crazymemecoke;

import de.crazymemecoke.features.commands.Friend;
import de.crazymemecoke.features.modules.exploits.Crasher;
import de.crazymemecoke.features.ui.guiscreens.GuiFirstUse;
import de.crazymemecoke.manager.altmanager.AltManager;
import de.crazymemecoke.manager.clickguimanager.clickgui.ClickGUI;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.commandmanager.CommandManager;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.utils.render.Colors;
import de.crazymemecoke.utils.render.Shader;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.io.File;

public class Client {

    private final static Client instance = new Client();
    private final Minecraft mc = Minecraft.mc();

    private final String clientName = "Splash";
    private final String clientVersion = "1.0";
    private final String clientCoder = "CrazyMemeCoke";
    private final String clientPrefix = ".";
    private final String clientBackground = "textures/client/background.jpg";
    private final String clientIcon = "textures/client/icon.png";
    private final String wurstWatermark = "textures/client/wurst.png";
    private final String shaderLoc = "textures/client/shader/";
    private final String clientChangelog = "https://github.com/RealFantaCoke/minecraft_client_1.8.8/commits/master";

    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private SettingsManager setmgr;
    private ClickGUI clickgui;
    private FontManager fontManager;
    private File clientDir;
    private Friend friend;
    private Shader shader;
    private AltManager altManager;

    public static Client main() {
        return instance;
    }

    public void startClient() {
        clientDir = new File(Minecraft.mc().mcDataDir + "/" + getClientName());
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

    public File getClientDir() {
        return clientDir;
    }

    public void onShutdown() {
        setmgr.saveSettings();
        moduleManager.saveModules();
        moduleManager.saveBinds();
        AltManager.saveAlts();

        Client.main().modMgr().getModule(Crasher.class).setState(false);
        System.out.println("Disabled Crasher module");
    }

    public ModuleManager modMgr() {
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

    public int getAmbienOldBlueColor() {
        return Colors.ambienOldBlueColor;
    }

    public int getGrey() {
        return Colors.greyColor;
    }

    public int getVortexRedColor() {
        return Colors.vortexRedColor;
    }

    public int getSuicideBlueGreyColor() {
        return Colors.suicideBlueGreyColor;
    }

    public int getSuicideBlueColor() {
        return Colors.suicideBlueColor;
    }

    public int getSuicideDarkBlueGreyColor() {
        return Colors.suicideDarkBlueGreyColor;
    }

    public int getGreyColor() {
        return Colors.greyColor;
    }

    public int getApinityGreyColor() {
        return Colors.apinityGreyColor;
    }

    public int getApinityBlueColor() {
        return Colors.apinityBlueColor;
    }

    public int getHuzuniBlueColor() {
        return Colors.huzuniBlueColor;
    }

    public int getHuzuniGreyColor() {
        return Colors.huzuniGreyColor;
    }

    public String getWurstWatermark() {
        return wurstWatermark;
    }

    public int getNodusPurpleColor() {
        return Colors.nodusPurpleColor;
    }

    public int getNodusTealColor() {
        return Colors.nodusTealColor;
    }

    public int getSaintDarkBlueColor() {
        return Colors.saintDarkBlueColor;
    }

    public int getSaintDarkTealColor() {
        return Colors.saintDarkTealColor;
    }

    public int getSaintOrangeColor() {
        return Colors.saintOrangeColor;
    }

    public int getIcarusNewBlueColor() {
        return Colors.icarusNewBlueColor;
    }

    public int getIcarusNewGreyColor() {
        return Colors.icarusNewGreyColor;
    }

    public int getIcarusOldGreyColor() {
        return Colors.icarusOldGreyColor;
    }

    public int getIcarusOldOrangeColor() {
        return Colors.icarusOldOrangeColor;
    }

    public int getAmbienNewBlueColor() {
        return Colors.ambienNewBlueColor;
    }

    public int getAmbienNewDarkGreyColor() {
        return Colors.ambienNewDarkGreyColor;
    }

    public int getHeroGreenColor() {
        return Colors.heroGreenColor;
    }

    public int getHeroGreyColor() {
        return Colors.heroGreyColor;
    }

    public int getVantaGreyColor() {
        return Colors.vantaGreyColor;
    }

    public int getVantaBlueColor() {
        return Colors.vantaBlueColor;
    }
}
