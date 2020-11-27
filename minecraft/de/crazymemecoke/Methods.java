package de.crazymemecoke;

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
