package com.masterof13fps;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.ModuleManager;
import com.masterof13fps.features.modules.impl.combat.Aura;
import com.masterof13fps.manager.altmanager.AltManager;
import com.masterof13fps.manager.eventmanager.EventManager;
import com.masterof13fps.manager.fontmanager.FontManager;
import com.masterof13fps.manager.settingsmanager.Setting;
import com.masterof13fps.manager.settingsmanager.SettingsManager;
import com.masterof13fps.utils.entity.EntityUtils;
import com.masterof13fps.utils.entity.PlayerUtil;
import com.masterof13fps.utils.render.Colors;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.BlockPos;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Methods extends Wrapper {
    Minecraft mc = Minecraft.getInstance();

    default EntityPlayerSP getPlayer() {
        return mc.thePlayer;
    }

    default double getBaseMoveSpeed() {
        return 0.27;
    }

    default PlayerControllerMP getPlayerController() {
        return mc.playerController;
    }

    default WorldClient getWorld() {
        return mc.theWorld;
    }

    default SettingsManager getSettingsManager() {
        return Client.main().setMgr();
    }

    default ModuleManager getModuleManager() {
        return Client.main().modMgr();
    }

    default FontManager getFontManager() {
        return Client.main().fontMgr();
    }

    default Setting getSettingByName(String setting, Module mod) {
        return getSettingsManager().settingByName(setting, mod);
    }

    default EventManager getEventManager() {
        return new EventManager();
    }

    default double getClientVersion() {
        return Client.main().getClientVersion();
    }

    default String getClientName() {
        return Client.main().getClientName();
    }

    default void sendPacket(Packet<? extends INetHandler> packet) {
        try {
            getPlayer().sendQueue.addToSendQueue(packet);
        } catch (NullPointerException ignored) {
        }
    }

    default void reloadClient() {
        Client.main().modMgr().loadModules();
        Client.main().modMgr().loadBinds();
        AltManager.loadAlts();
        setGuiScreen(mc.currentScreen);
    }

    default void setGuiScreen(GuiScreen screen) {
        mc.displayGuiScreen(screen);
    }

    default void sendChatMessage(String message) {
        sendPacket(new C01PacketChatMessage(message));
    }

    default NetHandlerPlayClient getNetHandler() {
        return mc.getNetHandler();
    }

    default String getClientPrefix() {
        return Client.main().getClientPrefix();
    }

    default GameSettings getGameSettings() {
        return mc.gameSettings;
    }

    default double getX() {
        return getPlayer().posX;
    }

    default double getY() {
        return getPlayer().posY;
    }

    default double getZ() {
        return getPlayer().posZ;
    }

    default void setX(double x) {
        getPlayer().posX = x;
    }

    default void setY(double y) {
        getPlayer().posY = y;
    }

    default void setZ(double z) {
        getPlayer().posZ = z;
    }

    default void setMotionX(double x) {
        getPlayer().motionX = x;
    }

    default void setMotionY(double y) {
        getPlayer().motionY = y;
    }

    default void setSpeed(double speed){
        PlayerUtil.setSpeed(speed);
    }

    default void setMotionZ(double z) {
        getPlayer().motionZ = z;
    }

    default boolean isMoving() {
        return getPlayer().moveForward != 0 || getPlayer().moveStrafing != 0;
    }

    default void setTimerSpeed(float timerSpeed){
        mc.timer.timerSpeed = timerSpeed;
    }

    default Gson gson() {
        return new Gson();
    }

    default JsonParser jsonParser() {
        return new JsonParser();
    }

    default String getClientChangelog(){
        return "https://pastebin.com/raw/3NiZ3SMC";
    }

    default String getClientCredits(){
        return "https://pastebin.com/raw/u66J4vGm";
    }

    default Entity getCurrentTarget() {
        return Aura.currentTarget;
    }

    static IBlockState getBlock(final BlockPos pos) {
        return mc.theWorld.getBlockState(pos);
    }

    static String getTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date time = new Date(System.currentTimeMillis());
        return formatter.format(time);
    }

    default String getDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd. MM yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    static Colors getColors() {
        return new Colors();
    }

}
