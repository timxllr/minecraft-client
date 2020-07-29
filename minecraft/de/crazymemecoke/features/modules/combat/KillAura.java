package de.crazymemecoke.features.modules.combat;

import com.darkmagician6.eventapi.EventTarget;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.clickguimanager.settings.SettingsManager;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.events.PostMotion;
import de.crazymemecoke.utils.events.PreMotion;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;

public class KillAura extends Module {
    public ArrayList targets = new ArrayList();
    public ArrayList attackedTargets = new ArrayList();
    public static EntityLivingBase curTarget = null;
    public static EntityLivingBase curBot = null;
    public static EntityLivingBase lastTarget = null;
    private final TimeHelper test = new TimeHelper();
    private boolean doBlock = false;
    private boolean unBlock = false;
    private final Random random = new Random();
    private long lastMs;
    private int delay = 0;
    private float curYaw = 0.0F;
    private float curPitch = 0.0F;
    private int tick = 0;

    SettingsManager sM = Client.getInstance().getSetmgr();

    boolean autoBlock, randomAttacks, attackPlayers, attackAnimals, attackMobs, attackInvisibles, checkEntityID, attackWhileInv;
    double reach, auraDelay, entityID, switchTicks, maxTargets;

    public KillAura() {
        super("KillAura", Keyboard.KEY_NONE, Category.COMBAT, -1);

        sM.rSetting(new Setting("Auto Block", this, true));
        sM.rSetting(new Setting("Random Attacks", this, true));
        sM.rSetting(new Setting("Attack Players", this, true));
        sM.rSetting(new Setting("Attack Animals", this, false));
        sM.rSetting(new Setting("Attack Mobs", this, false));
        sM.rSetting(new Setting("Attack Invisibles", this, false));
        sM.rSetting(new Setting("Check Entity ID", this, true));
        sM.rSetting(new Setting("Attack while Inv", this, true));
        sM.rSetting(new Setting("Reach", this, 3.8, 1, 7.0, false));
        sM.rSetting(new Setting("Delay", this, 120.0, 0.0, 1000.0, false));
        sM.rSetting(new Setting("Entity ID", this, 2.0, 1.0, 5.0, false));
        sM.rSetting(new Setting("Switch Ticks", this, 2.0, 0.0, 10.0, false));
        sM.rSetting(new Setting("Max Targets", this, 2.0, 0.0, 10.0, false));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            autoBlock = sM.getSettingByName("Auto Block", this).getValBoolean();
            randomAttacks = sM.getSettingByName("Random Attacks", this).getValBoolean();
            attackPlayers = sM.getSettingByName("Attack Players", this).getValBoolean();
            attackAnimals = sM.getSettingByName("Attack Animals", this).getValBoolean();
            attackMobs = sM.getSettingByName("Attack Mobs", this).getValBoolean();
            attackInvisibles = sM.getSettingByName("Attack Invisibles", this).getValBoolean();
            //checkEntityID = sM.getSettingByName("Check Entity ID", this).getValBoolean();
            attackWhileInv = sM.getSettingByName("Attack while Inv", this).getValBoolean();

            reach = sM.getSettingByName("Reach", this).getValDouble();
            auraDelay = sM.getSettingByName("Delay", this).getValDouble();
            entityID = sM.getSettingByName("Entity ID", this).getValDouble();
            //switchTicks = sM.getSettingByName("Switch Ticks", this).getValDouble();
            maxTargets = sM.getSettingByName("Max Targets", this).getValDouble();
        }
    }

    @Override
    public void onPreMotionUpdate() {
        if (getState()) {
            if (attackWhileInv && mc.currentScreen != null) {
                lastMs = System.currentTimeMillis() + 1000L;
            } else {
                doBlock = false;
                clear();
                findTargets();
                setCurTarget();

                if (curTarget != null) {
                    switchDelay();
                    Random rand = new Random();
                    if (tick == 0) {
                        doAttack();
                        lastTarget = curTarget;
                        mc.thePlayer.rotationPitch = curPitch;
                        mc.thePlayer.rotationYaw = curYaw + (float) rand.nextInt(10) - 5.0F;
                    } else {
                        mc.thePlayer.rotationYaw = mc.thePlayer.rotationYaw + (curYaw + (float) rand.nextInt(10) - 5.0F - mc.thePlayer.rotationYaw) / 2.0F;
                    }
                } else {
                    targets.clear();
                    attackedTargets.clear();
                    lastMs = System.currentTimeMillis();
                    if (unBlock) {
                        mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                        mc.thePlayer.setItemInUseCount(0);
                        unBlock = false;
                    }
                }

            }
        }
        super.onPreMotionUpdate();
    }

    @Override
    public void onPostMotionUpdate() {
        if (getState()) {
            if (curTarget != null && (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && autoBlock || mc.thePlayer.isBlocking()) && doBlock) {
                mc.thePlayer.setItemInUseCount(mc.thePlayer.getHeldItem().getMaxItemUseDuration());
                mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
                unBlock = true;
            }
        }
        super.onPostMotionUpdate();
    }

    public static float[] getRotations(Entity entity) {
        double pX = Minecraft.getMinecraft().thePlayer.posX;
        double pY = Minecraft.getMinecraft().thePlayer.posY + (double) Minecraft.getMinecraft().thePlayer.getEyeHeight();
        double pZ = Minecraft.getMinecraft().thePlayer.posZ;
        double eX = entity.posX;
        double eY = entity.posY + (double) (entity.height / 2.0F);
        double eZ = entity.posZ;
        double dX = pX - eX;
        double dY = pY - eY;
        double dZ = pZ - eZ;
        double dH = Math.sqrt(Math.pow(dX, 2.0D) + Math.pow(dZ, 2.0D));
        double yaw = Math.toDegrees(Math.atan2(dZ, dX)) + 90.0D;
        double pitch = Math.toDegrees(Math.atan2(dH, dY));
        return new float[]{(float) yaw, (float) (90.0D - pitch)};
    }

    private void switchDelay() {
        if (lastTarget != null && lastTarget != curTarget) {
            ++tick;
        } else {
            tick = 0;
        }

        if ((double) tick > delay + (double) (randomAttacks ? random.nextInt(3) : 0)) {
            tick = 0;
        }

    }

    private void setRotation() {
        float[] rotations = getRotations(curTarget);
        curYaw = rotations[0] + (float) random.nextInt(15) - 8.0F;
        curPitch = rotations[1] + (float) random.nextInt(10) - 5.0F;
        if (curPitch > 90.0F) {
            curPitch = 90.0F;
        } else if (curPitch < -90.0F) {
            curPitch = -90.0F;
        }

    }

    private void doAttack() {
        setRotation();
        int ticks = 1;
        int MAX_TICK = 50;
        if (tick == 0 && System.currentTimeMillis() - lastMs > (long) delay && System.currentTimeMillis() - lastMs < (long) (delay + ticks * MAX_TICK)) {
            boolean miss = random.nextInt(50) + 1 == 38;
            if (mc.thePlayer.isBlocking() || mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && autoBlock) {
                mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                unBlock = false;
            }

            if (!mc.thePlayer.isBlocking() && !autoBlock && mc.thePlayer.getItemInUseCount() > 0) {
                mc.thePlayer.setItemInUseCount(0);
            }

            attack(miss);
            doBlock = true;
            if (!miss) {
                attackedTargets.add(curTarget);
            }
        }

        if (System.currentTimeMillis() - lastMs > (long) (delay + ticks * MAX_TICK)) {
            lastMs = System.currentTimeMillis();
            delay = (int) ((double) ((Double) auraDelay + (randomAttacks ? (double) random.nextInt(100) : 0.0D)) - ticks * MAX_TICK);
            if (delay < 0) {
                delay = 0;
            }
        }

    }

    private void attack(boolean fake) {
        if (mc.thePlayer.fallDistance > 0.0F && !mc.thePlayer.onGround) {
            mc.thePlayer.onCriticalHit(curTarget);
        }

        mc.thePlayer.swingItem();
        if (!fake) {
            doBlock = true;
            mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(curBot != null ? curBot : curTarget, C02PacketUseEntity.Action.ATTACK));
        }

    }

    private void setCurTarget() {
        Iterator var2 = targets.iterator();

        while (var2.hasNext()) {
            EntityLivingBase ent = (EntityLivingBase) var2.next();
            if (!attackedTargets.contains(ent)) {
                curTarget = ent;
                break;
            }

            if (attackedTargets.size() == targets.size()) {
                if (attackedTargets.size() > 0) {
                    attackedTargets.clear();
                }

                setCurTarget();
            }
        }

    }

    private void autoblock() {
    }

    private void clear() {
        curTarget = null;
        curBot = null;
        Iterator iterator = targets.iterator();

        while (iterator.hasNext()) {
            try {
                EntityLivingBase ent = (EntityLivingBase) iterator.next();
                if (!isValidEntity(ent)) {
                    targets.remove(ent);
                    attackedTargets.remove(ent);
                }
            } catch (ConcurrentModificationException ignored) {
            }
        }
    }

    private void findTargets() {
        try {
            int maxSize = (int) maxTargets;
            Iterator var4 = mc.theWorld.loadedEntityList.iterator();

            while (var4.hasNext()) {
                Object o = var4.next();
                if (o instanceof EntityLivingBase) {
                    EntityLivingBase curEnt = (EntityLivingBase) o;
                    if (isValidEntity(curEnt) && !targets.contains(curEnt)) {
                        targets.add(curEnt);
                    }
                }

                if (targets.size() >= maxSize) {
                    break;
                }
            }

            targets.sort((ent1, ent2) -> {
                float e1 = getRotations((Entity) ent1)[0];
                float e2 = getRotations((Entity) ent2)[0];
                return e1 < e2 ? 1 : (e1 == e2 ? 0 : -1);
            });
        } catch (NullPointerException ignored) {
        }
    }

    private boolean isValidEntity(EntityLivingBase ent) {
        if (ent == null) {
            return false;
        } else if (ent == mc.thePlayer) {
            return false;
        } else if (ent instanceof EntityPlayer && !attackPlayers) {
            return false;
        } else if ((ent instanceof EntityAnimal || ent instanceof EntitySquid) && !attackAnimals) {
            return false;
        } else if ((ent instanceof EntityMob || ent instanceof EntityVillager || ent instanceof EntityBat) && !attackMobs) {
            return false;
        } else if ((double) mc.thePlayer.getDistanceToEntity(ent) > reach) {
            return false;
            //} else if (ent instanceof EntityPlayer && FriendManager.isFriend((EntityPlayer) ent) && !ModManager.getModByName("Friends").isEnabled()) {
            //    return false;
        } else if (!ent.isDead && ent.getHealth() > 0.0F) {
            if (ent.isInvisible() && !attackInvisibles) {
                return false;
            } else if (ent.getEntityId() > entityID * 1000000.0D) {
                return false;
            } else if (mc.thePlayer.isDead) {
                return false;
                //} else {
                //    return !(ent instanceof EntityPlayer) || !Teams.isEnemy((EntityPlayer) ent);
            }
        } else {
            return false;
        }
        return true;
    }

    public void onEnable() {
        try {
            curYaw = mc.thePlayer.rotationYaw;
            curPitch = mc.thePlayer.rotationPitch;
        } catch (NullPointerException ignored) {
        }
        test.reset();
        super.onEnable();
    }

    public void onDisable() {
        targets.clear();
        attackedTargets.clear();
        curTarget = null;
        mc.thePlayer.setItemInUseCount(0);
        mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        super.onDisable();
    }
}
