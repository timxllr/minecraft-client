package de.crazymemecoke.features.modules.impl.render;

import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventOutline;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.manager.notificationmanager.NotificationType;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.utils.NotifyUtil;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@ModuleInfo(name = "ESP", category = Category.RENDER, description = "Shows selected targets")
public class ESP extends Module {

    public Setting mode = new Setting("Mode", this, "Shader", new String[]{"Shader", "Outline", "Murder", "Prophunt", "Box"});
    public Setting players = new Setting("Players", this, true);
    public Setting mobs = new Setting("Mobs", this, false);
    public Setting animals = new Setting("Animals", this, false);
    public Setting villager = new Setting("Villager", this, false);
    public Setting items = new Setting("Items", this, false);

    public EntityPlayer murderer;

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        murderer = null;
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
        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.threeD) {
                switch (mode.getCurrentMode()) {
                    case "Murder": {
                        doMurder();
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
            switch (mode.getCurrentMode()) {
                case "Shader": {
                    ((EventOutline) event).setOutline(true);
                    break;
                }
            }
        }

        if (event instanceof EventUpdate) {
            if (murderer == null) {
                for (Entity e : mc.theWorld.loadedEntityList) {
                    if (((e instanceof EntityPlayer)) && (((EntityPlayer) e).getCurrentEquippedItem() != null) && ((((EntityPlayer) e).getCurrentEquippedItem().getItem() instanceof ItemSword))) {
                        NotifyUtil.notification("Mörder erkannt!", "Achtung! " + e.getName() + " ist der Mörder!", NotificationType.INFO, 5);
                        murderer = (EntityPlayer) e;
                    }
                }
            }
        }
    }

    private void doMurder() {
        if (murderer != null) {
            float x = (float) (murderer.lastTickPosX + (murderer.posX - murderer.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX);
            float y = (float) (murderer.lastTickPosY + (murderer.posY - murderer.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY);
            float z = (float) (murderer.lastTickPosZ + (murderer.posZ - murderer.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ);
            GL11.glColor3f(0.5F, 0.0F, 0.0F);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
        }
        for (Entity e : mc.theWorld.loadedEntityList) {
            if ((e instanceof EntityItem)) {
                EntityItem item = (EntityItem) e;
                float x = (float) (item.lastTickPosX + (item.posX - item.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX);
                float y = (float) (item.lastTickPosY + (item.posY - item.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY);
                float z = (float) (item.lastTickPosZ + (item.posZ - item.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ);
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glDisable(3553);
                GL11.glEnable(2848);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GL11.glTranslated(x, y, z);
                GL11.glTranslated(-x, -y, -z);
                GL11.glColor4f(0.9F, 0.76F, 0.0F, 0.5F);
                RenderUtils.box(x - 0.2D, y + 0.1D, z - 0.2D, x + 0.2D, y + 0.5D, z + 0.2D, new Color(0,0,0));
                GL11.glDisable(2848);
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
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
}
