package de.crazymemecoke.module.modules.combat;

import de.crazymemecoke.ui.clickgui.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

public class Aura extends Module {

    double range;
    double delay;
    boolean teams;
    boolean players;
    boolean animals;
    boolean mobs;
    private Entity target;
    private long current, last;
    private float yaw, pitch;

    public Aura() {
        super("Aura", Keyboard.KEY_NONE, Category.COMBAT, -1);

        Client.getInstance().getSetmgr().rSetting(new Setting("Range", this, 4, 3.5, 7, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Delay", this, 100, 1, 150, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Teams", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Players", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Animals", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Mobs", this, false));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            range = Client.getInstance().getSetmgr().getSettingByName("Range", this).getValDouble();
            delay = Client.getInstance().getSetmgr().getSettingByName("Delay", this).getValDouble();
            teams = Client.getInstance().getSetmgr().getSettingByName("Teams", this).getValBoolean();
            players = Client.getInstance().getSetmgr().getSettingByName("Players", this).getValBoolean();
            animals = Client.getInstance().getSetmgr().getSettingByName("Animals", this).getValBoolean();
            mobs = Client.getInstance().getSetmgr().getSettingByName("Mobs", this).getValBoolean();
        }
    }

    @Override
    public void onPostMotionUpdate() {
        if (getState()) {
            if (target == null)
                return;

            mc.thePlayer.rotationYaw = yaw;
            mc.thePlayer.rotationPitch = pitch;
        }
    }

    @Override
    public void onPreMotionUpdate() {
        if (getState()) {
            target = getClosest(mc.playerController.getBlockReachDistance());

            if (target == null)
                return;

            updateTime();

            yaw = mc.thePlayer.rotationYaw;
            pitch = mc.thePlayer.rotationPitch;

            if (current - last > 1000 / delay) {
                attack(target);
                resetTime();
            }
        }
    }

    private void attack(Entity entity) {
        if ((entity instanceof EntityPlayer && players) || (entity instanceof EntityMob && mobs) || (entity instanceof EntityAnimal && animals)) {
            mc.thePlayer.swingItem();
            mc.playerController.attackEntity(mc.thePlayer, entity);
        }
    }

    private void updateTime() {
        current = (System.nanoTime() / 1000000L);
    }

    private void resetTime() {
        last = (System.nanoTime() / 1000000L);
    }

    private Entity getClosest(double range) {
        double dist = range;
        Entity target = null;
        for (Object object : mc.theWorld.loadedEntityList) {
            Entity entity = (Entity) object;
            if (canAttack(entity)) {
                double currentDist = mc.thePlayer.getDistanceToEntity(entity);
                if (currentDist <= dist) {
                    dist = currentDist;
                    target = entity;
                }
            }
        }
        return target;
    }

    private boolean canAttack(Entity entity) {
        return entity != mc.thePlayer && entity.isEntityAlive() && mc.thePlayer.getDistanceToEntity(entity) <= mc.playerController.getBlockReachDistance() && entity.ticksExisted > 30;
    }
}