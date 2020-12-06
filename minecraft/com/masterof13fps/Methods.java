package com.masterof13fps;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleManager;
import com.masterof13fps.manager.altmanager.AltManager;
import com.masterof13fps.manager.eventmanager.EventManager;
import com.masterof13fps.manager.fontmanager.FontManager;
import com.masterof13fps.manager.settingsmanager.Setting;
import com.masterof13fps.manager.settingsmanager.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Methods {
    public static Minecraft mc = Minecraft.getInstance();

    public EntityPlayerSP getPlayer() {
        return mc.thePlayer;
    }

    public PlayerControllerMP getPlayerController() {
        return mc.playerController;
    }

    public WorldClient getWorld() {
        return mc.theWorld;
    }

    public SettingsManager getSettingsManager() {
        return Client.main().setMgr();
    }

    public ModuleManager getModuleManager() {
        return Client.main().modMgr();
    }

    public FontManager getFontManager() {
        return Client.main().fontMgr();
    }

    public Setting getSettingByName(String setting, Module mod) {
        return getSettingsManager().settingByName(setting, mod);
    }

    public EventManager getEventManager() {
        return new EventManager();
    }

    public double getClientVersion() {
        return Client.main().getClientVersion();
    }

    public String getClientName() {
        return Client.main().getClientName();
    }

    public void sendPacket(Packet<? extends INetHandler> packet) {
        try {
            getPlayer().sendQueue.addToSendQueue(packet);
        } catch (NullPointerException ignored) {
        }
    }

    public void reloadClient() {
        Client.main().modMgr().loadModules();
        Client.main().modMgr().loadBinds();
        AltManager.loadAlts();
        setGuiScreen(mc.currentScreen);
    }

    public void setGuiScreen(GuiScreen screen) {
        mc.displayGuiScreen(screen);
    }

    public void sendChatMessage(String message) {
        sendPacket(new C01PacketChatMessage(message));
    }

    public NetHandlerPlayClient getNetHandler() {
        return mc.getNetHandler();
    }

    public String getClientPrefix() {
        return Client.main().getClientPrefix();
    }

    public GameSettings getGameSettings() {
        return mc.gameSettings;
    }

    public double getX() {
        return getPlayer().posX;
    }

    public double getY() {
        return getPlayer().posY;
    }

    public double getZ() {
        return getPlayer().posZ;
    }

    public Gson gson() {
        return new Gson();
    }

    public JsonParser jsonParser() {
        return new JsonParser();
    }

    public String getClientChangelog(){
        return "https://pastebin.com/raw/3NiZ3SMC";
    }

    public String getClientCredits(){
        return "https://pastebin.com/raw/u66J4vGm";
    }

}
