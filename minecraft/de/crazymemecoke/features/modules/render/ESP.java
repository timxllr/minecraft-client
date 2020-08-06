package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;
import java.util.ArrayList;

public class ESP extends Module {

    SettingsManager sM = Client.getInstance().getSetmgr();

    public ESP() {
        super("ESP", Keyboard.KEY_NUMPAD2, Category.RENDER, Rainbow.rainbow(1, 1).hashCode());

        ArrayList<String> mode = new ArrayList<>();

        mode.add("Box");
        mode.add("Outline");
        mode.add("Prophunt");

        sM.rSetting(new Setting("Mode", this, "Outline", mode));
        sM.rSetting(new Setting("Players", this, true));
        sM.rSetting(new Setting("Mobs", this, false));
        sM.rSetting(new Setting("Animals", this, false));

    }

    @Override
    public void onRender() {
        if (getState()) {
            if (sM.getSettingByName("Mode", this).getValString().equalsIgnoreCase("Box")) {
                for (Object players : mc.theWorld.loadedEntityList) {
                    if (!(players instanceof EntityLivingBase))
                        continue;

                    EntityLivingBase entity = (EntityLivingBase) players;

                    if (entity instanceof EntityPlayer) {
                        if (entity != mc.thePlayer)
                            player(entity);
                        continue;
                    }
                }
            } else if (sM.getSettingByName("Mode", this).getValString().equalsIgnoreCase("Prophunt")) {
                for (Object entity : mc.theWorld.loadedEntityList)
                    if (entity instanceof EntityLiving && ((Entity) entity).isInvisible()) {
                        double x = ((Entity) entity).posX;
                        double y = ((Entity) entity).posY;
                        double z = ((Entity) entity).posZ;
                        Color color;
                        if (mc.thePlayer.getDistanceToEntity((Entity) entity) >= 0.5)
                            color = new Color(1F, 0F, 0F,
                                    0.5F - MathHelper.abs(
                                            MathHelper.sin(Minecraft.getSystemTime() % 1000L / 1000.0F * (float) Math.PI * 1.0F)
                                                    * 0.3F));
                        else
                            color = new Color(0, 0, 0, 0);
                        RenderUtils.box(x - 0.5, y - 0.1, z - 0.5, x + 0.5, y + 0.9, z + 0.5, color);
                    }
            }
        }
        super.onRender();
    }

    public void player(EntityLivingBase entity) {
        float red = 0.5F;
        float green = 0.5F;
        float blue = 1F;

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks)
                - mc.getRenderManager().renderPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks)
                - mc.getRenderManager().renderPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks)
                - mc.getRenderManager().renderPosZ;

        render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
    }

    public void render(float red, float green, float blue, double x, double y, double z, float width, float height) {
        RenderUtils.drawEntityESP(x, y, z, width, height, red, green, blue, 0.45F, 0F, 0F, 0F, 1F, 1F);
    }

}
