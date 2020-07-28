package de.crazymemecoke.module.modules.combat;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import de.crazymemecoke.ui.clickgui.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.events.MoveEvent;
import de.crazymemecoke.utils.events.PacketSendEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

public class Aura extends Module {

    double range;
    double cps;
    boolean teams;
    boolean players;
    boolean animals;
    boolean mobs;
    boolean villager;
    boolean invisibles;
    public static Entity target;
    private long current, last;
    private float yaw, pitch;

    public Aura() {
        super("Aura", Keyboard.KEY_NONE, Category.COMBAT, -1);

        Client.getInstance().getSetmgr().rSetting(new Setting("Range", this, 4, 3.5, 7, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("CPS", this, 10, 1, 20, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Teams", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Players", this, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Animals", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Mobs", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Villager", this, false));
        Client.getInstance().getSetmgr().rSetting(new Setting("Invisibles", this, false));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            range = Client.getInstance().getSetmgr().getSettingByName("Range", this).getValDouble();
            cps = Client.getInstance().getSetmgr().getSettingByName("CPS", this).getValDouble();
            teams = Client.getInstance().getSetmgr().getSettingByName("Teams", this).getValBoolean();
            players = Client.getInstance().getSetmgr().getSettingByName("Players", this).getValBoolean();
            animals = Client.getInstance().getSetmgr().getSettingByName("Animals", this).getValBoolean();
            mobs = Client.getInstance().getSetmgr().getSettingByName("Mobs", this).getValBoolean();
            villager = Client.getInstance().getSetmgr().getSettingByName("Villager", this).getValBoolean();
            invisibles = Client.getInstance().getSetmgr().getSettingByName("Invisibles", this).getValBoolean();
        }
    }


    @Override
    public void onEnable() {
        EventManager.register(this);
    }

    @Override
    public void onDisable() {
        EventManager.unregister(this);
        target = null;
    }

    @Override
    public void onPostMotionUpdate() {
        if (getState()) {
            if (target == null)
                return;

            // clientside rotations
            //  mc.thePlayer.rotationYaw = yaw;
            // mc.thePlayer.rotationPitch = pitch;
        }
    }

    @Override
    public void onPreMotionUpdate() {
        if (getState()) {
            target = getClosest(mc.playerController.getBlockReachDistance());
            calcRotation(target);

            if (target == null)
                return;

            updateTime();

            //   yaw = mc.thePlayer.rotationYaw;
            //  pitch = mc.thePlayer.rotationPitch;

            if (current - last > 1000 / cps) {
                attack(target);
                resetTime();
            }
        }
    }

    @EventTarget
    public void onMove(MoveEvent e) {
        e.setYaw(yaw);
        e.setPitch(pitch);
    }

    public void calcRotation(Entity target) {
        if (target == null || mc.thePlayer == null)
            return;
/*
        Vec3 eyePosition = new Vec3(player.posX, player.getEntityBoundingBox().minY + player.getEyeHeight(), player.posZ);

        AxisAlignedBB tbb = target.getEntityBoundingBox();
        Vec3 targetVector = new Vec3(tbb.minX + (tbb.maxX - tbb.minX), tbb.minY + (tbb.maxY - tbb.minY), tbb.minZ + (tbb.maxZ - tbb.minZ));

        double xDiff = targetVector.xCoord - eyePosition.xCoord;
        double yDiff = targetVector.yCoord - eyePosition.yCoord;
        double zDiff = targetVector.zCoord - eyePosition.zCoord;

        yaw = -MathHelper.wrapAngleTo180_float((float) Math.toDegrees(Math.atan2(xDiff, zDiff)));
        pitch = MathHelper.wrapAngleTo180_float((float) -Math.toDegrees(Math.atan2(yDiff, Math.sqrt(xDiff * xDiff + zDiff * zDiff))));*/
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        double xDiff = target.posX - player.posX;
        double zDiff = target.posZ - player.posZ;
        double yDiff;
        if (target instanceof EntityLivingBase) {
            final EntityLivingBase targetEntity = (EntityLivingBase) target;
            yDiff = targetEntity.posY + targetEntity.getEyeHeight() - (player.posY + player.getEyeHeight());
        } else {
            yDiff = (target.getEntityBoundingBox().minY + target.getEntityBoundingBox().maxY) / 2.0 - (player.posY + player.getEyeHeight());
        }
        final double xzDiff = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        yaw = (float) (Math.atan2(zDiff, xDiff) * 180.0 / Math.PI) - 90.0f;
        pitch = (float) (-(Math.atan2(yDiff, xzDiff) * 180.0 / Math.PI));


    }


    private void attack(Entity entity) {
        if ((entity instanceof EntityPlayer && players) || (entity instanceof EntityMob && mobs) || (entity instanceof EntityAnimal && animals) || (entity instanceof EntityVillager && villager) || (invisibles)) {
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

    @EventTarget
    public void onPacket(PacketSendEvent e) {
/*        if (getState())
            if (e.getPacket() instanceof C03PacketPlayer) {
                C03PacketPlayer orig = (C03PacketPlayer) e.getPacket();
                orig.yaw = yaw;
                orig.pitch = pitch;
                orig.rotating = true;
                e.setPacket(orig);
            }*/
    }
}