package de.crazymemecoke.features.modules.impl.render;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.commands.impl.Friend;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventOutline;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.utils.entity.EntityUtils;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Iterator;

@ModuleInfo(name = "ESP", category = Category.RENDER, description = "Shows selected targets")
public class ESP extends Module {

    public Setting mode = new Setting("Mode", this, "Shader", new String[] {"Shader", "Outline", "Box"});
    public Setting players = new Setting("Players", this, true);
    public Setting mobs = new Setting("Mobs", this, false);
    public Setting animals = new Setting("Animals", this, false);
    public Setting villager = new Setting("Villager", this, false);
    public Setting items = new Setting("Items", this, false);

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public void player(EntityLivingBase entity) {
        float red = 0.5F;
        float green = 0.5F;
        float blue = 1F;

        double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks)
                - RenderManager.renderPosX;
        double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks)
                - RenderManager.renderPosY;
        double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks)
                - RenderManager.renderPosZ;

        render(red, green, blue, xPos, yPos, zPos, entity.width, entity.height);
    }

    public void render(float red, float green, float blue, double x, double y, double z, float width, float height) {
        RenderUtils.drawEntityESP(x, y, z, width, height, red, green, blue, 0.45F, 0F, 0F, 0F, 1F, 1F);
    }

    @Override
    public void onEvent(Event event) {
        String mode = Client.main().setMgr().settingByName("Mode", this).getCurrentMode();

        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.threeD) {
                switch (mode) {
                    case "Box": {
                        doBox();
                        break;
                    }
                    case "Prophunt": {
                        doProphunt();
                        break;
                    }
                }
            }
        }
        if (event instanceof EventOutline) {
            switch (mode) {
                case "Shader": {
                    ((EventOutline) event).setOutline(true);
                    break;
                }
            }
        }
    }

    private void doProphunt() {
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

    private void doBox() {
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
    }
}
