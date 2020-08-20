package de.crazymemecoke.features.modules.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.Friend;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.utils.entity.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ESP extends Module {

    SettingsManager sM = Client.instance().setMgr();

    public ESP() {
        super("ESP", Keyboard.KEY_NUMPAD2, Category.RENDER, -1);

        ArrayList<String> mode = new ArrayList<>();

        mode.add("Box");
        mode.add("Outline");
        mode.add("Prophunt");

        sM.newSetting(new Setting("Mode", this, "Outline", mode));
        sM.newSetting(new Setting("Players", this, true));
        sM.newSetting(new Setting("Mobs", this, false));
        sM.newSetting(new Setting("Animals", this, false));

    }

    @Override
    public void onRender() {
        if (getState()) {
            if (sM.getSettingByName("Mode", this).getMode().equalsIgnoreCase("Box")) {
                Iterator var3 = mc.theWorld.loadedEntityList.iterator();

                while (true) {
                    EntityPlayer player;
                    do {
                        do {
                            Object object;
                            do {
                                if (!var3.hasNext()) {
                                    return;
                                }

                                object = var3.next();
                            } while (!(object instanceof EntityPlayer));

                            player = (EntityPlayer) object;
                        } while (player == mc.thePlayer);
                    } while (player.rotationPitch != 0.0F);

                    double[] pos = EntityUtils.interpolate(player);
                    double x = pos[0] - RenderManager.renderPosX;
                    double y = pos[1] - RenderManager.renderPosY;
                    double z = pos[2] - RenderManager.renderPosZ;
                    GL11.glPushMatrix();
                    GL11.glTranslated(x, y, z);
                    GL11.glRotatef(-player.rotationYaw, 0.0F, 1.0F, 0.0F);
                    int color;
                    if (Friend.friends.contains(player)) {
                        color = 65280;
                    } else {
                        color = 68029;
                    }

                    RenderUtils.drawOutlinedBox(new AxisAlignedBB((double) player.width / 2.0D, 0.0D, -((double) player.width / 2.0D), (double) (-player.width) / 2.0D, (double) player.height + 0.1D, (double) player.width / 2.0D), color);
                    GL11.glPopMatrix();
                }

            } else if (sM.getSettingByName("Mode", this).getMode().equalsIgnoreCase("Prophunt")) {
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
