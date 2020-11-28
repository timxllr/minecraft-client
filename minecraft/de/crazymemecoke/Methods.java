package de.crazymemecoke;

import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.manager.modulemanager.ModuleManager;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.settingsmanager.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

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

    public double getClientVersion() {
        return Client.main().getClientVersion();
    }

    public String getClientName() {
        return Client.main().getClientName();
    }

    public void sendPacket(Packet<? extends INetHandler> packet) {
        getPlayer().sendQueue.addToSendQueue(packet);
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

}
