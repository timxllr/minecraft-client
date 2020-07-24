package de.crazymemecoke.module.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import de.Hero.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.module.Category;
import de.crazymemecoke.module.Module;
import de.crazymemecoke.utils.entity.PlayerUtil;
import de.crazymemecoke.utils.events.MoveEvent;
import de.crazymemecoke.utils.events.UpdateEvent;
import de.crazymemecoke.utils.render.Rainbow;
import de.crazymemecoke.utils.time.TickEvent;
import de.crazymemecoke.utils.time.TimerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import org.lwjgl.input.Keyboard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Speed extends Module {
    ArrayList<String> mode = new ArrayList<>();

    private final TimerUtil framesDelay = new TimerUtil();

    String speedMode;
    boolean move;
    boolean hop;
    private double prevY;
    private int motionTicks;
    private final TimerUtil delayTimer = new TimerUtil();
    private double speedVal;
    private boolean speedUp;

    public Speed() {
        super("Speed", Keyboard.KEY_NONE, Category.MOVEMENT, Rainbow.rainbowNormal(1, 1).hashCode());
        mode.add("Latest OnGround");
        mode.add("AAC 1.9.10");
        mode.add("Frames");
        mode.add("New");
        mode.add("Jump");
        mode.add("Timer");

        Client.getInstance().getSetmgr().rSetting(new Setting("Speed Mode", this, "Jump", mode));
        Client.getInstance().getSetmgr().rSetting(new Setting("Frames Speed", this, 4.25, 0, 50, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Timer Speed", this, 4.25, 0, 50, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Wall Speed", this, 4.25, 0, 50, true));
        Client.getInstance().getSetmgr().rSetting(new Setting("Water Speed", this, 4.25, 0, 50, true));
    }

    private int buffer;

    public void onEnable() {
        framesDelay.setLastMS();
        delayTimer.setLastMS();
        motionTicks = 0;
        move = false;
        hop = false;
        prevY = 0.0D;
        buffer = 0;
        ticks = 0;
    }

    public void onDisable() {
        mc.timer.timerSpeed = 1.0F;
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
        mc.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
    }

    @Override
    public void onUpdate() {
        speedMode = Client.getInstance().getSetmgr().getSettingByName("Speed Mode", this).getValString();
        if (getState()) {
            if (speedMode.equalsIgnoreCase("AAC 1.9.10")) {
                doAACSpeed();
            } else {
                if (speedMode.equalsIgnoreCase("Latest OnGround")) {
                    doLatestOnGroundSpeed();
                } else {
                    if (speedMode.equalsIgnoreCase("Frames")) {
                        doFrames(Client.getInstance().getSetmgr().getSettingByName("Frames Speed", this).getValDouble());
                    } else {
                        if (speedMode.equalsIgnoreCase("New")) {
                            doNewSpeed();
                        } else {
                            if (speedMode.equalsIgnoreCase("Jump")) {
                                doJumpSpeed();
                            } else {
                                if (speedMode.equalsIgnoreCase("Timer")) {
                                    doTimerSpeed();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void doTimerSpeed() {
        mc.timer.timerSpeed = (float) Client.getInstance().getSetmgr().getSettingByName("Timer Speed", this).getValDouble();
    }

    private void doJumpSpeed() {
        if (((mc.thePlayer.moveForward == 0.0F)
                && (mc.thePlayer.moveStrafing == 0.0F))
                || (mc.thePlayer.isCollidedHorizontally)) {
            return;
        }
        if (mc.thePlayer.moveForward > 0.0F) {
            float var1 = mc.thePlayer.rotationYaw * 0.017453292F;
            mc.thePlayer.motionX -= MathHelper.sin(var1) * 0.2D;
            mc.thePlayer.motionZ += MathHelper.cos(var1) * 0.2D;
        }
    }

    private void doNewSpeed() {
        double speed = 3.15D;
        double slow = 1.49D;
        double offset = 4.9D;
        if ((mc.gameSettings.keyBindForward.isPressed())
                || (mc.gameSettings.keyBindBack.isPressed())
                || (mc.gameSettings.keyBindLeft.isPressed())
                || (mc.gameSettings.keyBindRight.isPressed())) {
            boolean shouldOffset = true;
            if ((mc.thePlayer.onGround) && (prevYaw < 1.0F)) {
                prevYaw += 0.2F;
            }
            if (!mc.thePlayer.onGround) {
                prevYaw = 0.0F;
            }
            if ((prevYaw == 1.0F) && (shouldSpeedUp())) {
                if (!mc.thePlayer.isSprinting()) {
                    offset += 0.8D;
                }
                if (mc.thePlayer.moveStrafing != 0.0F) {
                    speed -= 0.1D;
                    offset += 0.5D;
                }
                if (mc.thePlayer.isInWater()) {
                    speed -= 0.1D;
                }
                double ncT = 1.15D;
                ticks += 1;
                if (ticks == 1) {
                    mc.thePlayer.motionX *= speed;
                    mc.thePlayer.motionZ *= speed;
                } else if (ticks == 2) {
                    mc.timer.timerSpeed = 1.0F;
                    mc.thePlayer.motionX /= slow;
                    mc.thePlayer.motionZ /= slow;
                } else if ((ticks != 3) && (ticks == 4)) {
                    mc.timer.timerSpeed = 1.0F;
                    if (shouldOffset) {
                        mc.thePlayer.setPosition(
                                mc.thePlayer.posX + mc.thePlayer.motionX / offset,
                                mc.thePlayer.posY,
                                mc.thePlayer.posZ + mc.thePlayer.motionZ / offset);
                    }
                    ticks = 0;
                }
            }
        }
    }

    public boolean shouldSpeedUp() {
        boolean b = (mc.thePlayer.movementInput.moveForward != 0.0F)
                || (mc.thePlayer.movementInput.moveStrafe != 0.0F);
        return (!mc.thePlayer.isInWater()) && (!PlayerUtil.isOnLiquid())
                && (!mc.thePlayer.isCollidedHorizontally) && (!mc.thePlayer.isSneaking())
                && (mc.thePlayer.onGround) && (b);
    }

    public boolean defaultChecks() {
        return (!mc.thePlayer.isSneaking()) && (!mc.thePlayer.isCollidedHorizontally)
                && ((mc.thePlayer.isCollidedHorizontally) || (mc.thePlayer.moveForward != 0.0F)
                || (mc.thePlayer.moveStrafing != 0.0F))
                && (!mc.gameSettings.keyBindJump.isPressed()) && (!PlayerUtil.isOnLiquid())
                && (!PlayerUtil.isInLiquid());
    }

    public static float getDistanceBetweenAngles(float angle1, float angle2) {
        float angle = Math.abs(angle1 - angle2) % 360.0F;
        if (angle > 180.0F) {
            angle = 360.0F - angle;
        }
        return angle;
    }

    private boolean isTurning() {
        return getDistanceBetweenAngles(mc.thePlayer.rotationYaw, prevYaw) > 0.8D;
    }

    private boolean isStrafing() {
        return (mc.thePlayer.movementInput.moveStrafe != 0.0F) && (mc.thePlayer.moveForward != 0.0F);
    }

    private boolean isColliding(AxisAlignedBB bb) {
        boolean colliding = false;
        for (Object oboundingBox : mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, bb)) {
            colliding = true;
        }
        if ((getBlock(bb.offset(0.0D, -0.1D, 0.0D)) instanceof BlockAir)) {
            colliding = true;
        }
        return colliding;
    }

    public Block getBlock(AxisAlignedBB bb) {
        int y = (int) bb.minY;
        for (int x = MathHelper.floor_double(bb.minX); x < MathHelper.floor_double(bb.maxX) + 1; x++) {
            for (int z = MathHelper.floor_double(bb.minZ); z < MathHelper.floor_double(bb.maxZ) + 1; z++) {
                Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block != null) {
                    return block;
                }
            }
        }
        return null;
    }

    private int ticks = 0;
    private float prevYaw;
    private boolean turnCancel;
    public double speed;
    public int stage;
    private boolean prevStrafing;
    private boolean wasTurning;
    private int turnTicks;
    private boolean speed2;
    private boolean distShort;
    public int tickse;
    public double moveSpeed;
    private double lastDist;
    public static boolean canStep;

    private void doFrames(double speed) {
        if ((mc.thePlayer.movementInput.moveForward > 0.0F)
                || (mc.thePlayer.movementInput.moveStrafe > 0.0F)) {
            if (mc.thePlayer.onGround) {
                prevY = mc.thePlayer.posY;
                hop = true;
                mc.thePlayer.jump();
                if (motionTicks == 1) {
                    framesDelay.reset();
                    if (move) {
                        mc.thePlayer.motionX /= speed * 2.0D;
                        mc.thePlayer.motionZ /= speed * 2.0D;
                        move = false;
                    }
                    motionTicks = 0;
                } else {
                    motionTicks = 1;
                }
            } else if ((!move) && (motionTicks == 1) && (framesDelay.isDelayComplete(450L))) {
                mc.thePlayer.motionX *= speed;
                mc.thePlayer.motionZ *= speed;
                move = true;
            }
        }
    }

    @EventTarget
    public void onNewMove(MoveEvent e) {
        if (!speedMode.equalsIgnoreCase("Jump")) {
            return;
        }
        e.setX(e.getX() * 0.4D);
        e.setZ(e.getZ() * 0.4D);
    }

    public boolean checks() {
        return (!mc.thePlayer.isSneaking()) && (!mc.thePlayer.isCollidedHorizontally)
                && ((mc.thePlayer.isCollidedHorizontally) || (mc.thePlayer.moveForward != 0.0F)
                || (mc.thePlayer.moveStrafing != 0.0F))
                && (!mc.gameSettings.keyBindJump.isPressed()) && (!PlayerUtil.isOnLiquid())
                && (!PlayerUtil.isInLiquid());
    }

    @EventTarget
    public void onJumpUpdate(UpdateEvent e) {
        if (!speedMode.equalsIgnoreCase("Jump")) {
            return;
        }
        if (e.getState() == WorldRenderer.State.PRE) {
            if (((mc.thePlayer.moveForward == 0.0F) && (mc.thePlayer.moveStrafing == 0.0F))
                    || (mc.thePlayer.isCollidedHorizontally)) {
                return;
            }
            if (mc.thePlayer.isCollidedVertically) {
                mc.thePlayer.motionY = 0.145D;
                mc.thePlayer.isAirBorne = true;
            } else if (mc.thePlayer.isSprinting()) {
                float var1 = mc.thePlayer.rotationYaw * 0.017453292F;
                mc.thePlayer.motionX -= MathHelper.sin(var1) * 0.2D;
                mc.thePlayer.motionZ += MathHelper.cos(var1) * 0.2D;
            }
        }
    }

    @EventTarget
    public void onNewUpdate(UpdateEvent e) {
        if (!speedMode.equalsIgnoreCase("New")) {
            return;
        }
        if (e.getState() == WorldRenderer.State.PRE) {
            double speed = 3.15D;
            double slow = 1.49D;
            double offset = 4.9D;
            if ((mc.gameSettings.keyBindForward.isPressed())
                    || (mc.gameSettings.keyBindBack.isPressed())
                    || (mc.gameSettings.keyBindLeft.isPressed())
                    || (mc.gameSettings.keyBindRight.isPressed())) {
                boolean shouldOffset = true;
                if ((mc.thePlayer.onGround) && (prevYaw < 1.0F)) {
                    prevYaw += 0.2F;
                }
                if (!mc.thePlayer.onGround) {
                    prevYaw = 0.0F;
                }
                if ((prevYaw == 1.0F) && (shouldSpeedUp())) {
                    if (!mc.thePlayer.isSprinting()) {
                        offset += 0.8D;
                    }
                    if (mc.thePlayer.moveStrafing != 0.0F) {
                        speed -= 0.1D;
                        offset += 0.5D;
                    }
                    if (mc.thePlayer.isInWater()) {
                        speed -= 0.1D;
                    }
                    double ncT = 1.15D;
                    ticks += 1;
                    if (ticks == 1) {
                        mc.thePlayer.motionX *= speed;
                        mc.thePlayer.motionZ *= speed;
                    } else if (ticks == 2) {
                        mc.timer.timerSpeed = 1.0F;
                        mc.thePlayer.motionX /= slow;
                        mc.thePlayer.motionZ /= slow;
                    } else if ((ticks != 3) && (ticks == 4)) {
                        mc.timer.timerSpeed = 1.0F;
                        if (shouldOffset) {
                            mc.thePlayer.setPosition(mc.thePlayer.posX + mc.thePlayer.motionX / offset,
                                    mc.thePlayer.posY,
                                    mc.thePlayer.posZ + mc.thePlayer.motionZ / offset);
                        }
                        ticks = 0;
                    }
                }
            }
        }
    }

    private void doLatestOnGroundSpeed() {
        if ((hop) && (mc.thePlayer.posY >= prevY + 0.399994D)) {
            mc.thePlayer.motionY = -0.9D;
            mc.thePlayer.posY = prevY;
            hop = false;
        }
        if ((mc.thePlayer.moveForward != 0.0F) && (!mc.thePlayer.isCollidedHorizontally)
                && (!mc.thePlayer.isEating())) {
            if ((mc.thePlayer.moveForward == 0.0F) && (mc.thePlayer.moveStrafing == 0.0F)) {
                mc.thePlayer.motionX = 0.0D;
                mc.thePlayer.motionZ = 0.0D;
                if (mc.thePlayer.isCollidedVertically) {
                    mc.thePlayer.jump();
                    move = true;
                }
                if ((move) && (mc.thePlayer.isCollidedVertically)) {
                    move = false;
                }
            }
            if (mc.thePlayer.isCollidedVertically) {
                mc.thePlayer.motionX *= 1.0379D;
                mc.thePlayer.motionZ *= 1.0379D;
                doMiniHop();
            }
            if ((hop) && (!move) && (mc.thePlayer.posY >= prevY + 0.399994D)) {
                mc.thePlayer.motionY = -100.0D;
                mc.thePlayer.posY = prevY;
                hop = false;
            }
        }
    }

    private void doAACSpeed() {
        if ((hop) && (mc.thePlayer.posY >= prevY + 0.399994D)) {
            mc.thePlayer.motionY = -0.9D;
            mc.thePlayer.posY = prevY;
            hop = false;
        }
        if ((mc.thePlayer.moveForward != 0.0F) && (!mc.thePlayer.isCollidedHorizontally)
                && (!mc.thePlayer.isEating())) {
            if ((mc.thePlayer.moveForward == 0.0F) && (mc.thePlayer.moveStrafing == 0.0F)) {
                mc.thePlayer.motionX = 0.0D;
                mc.thePlayer.motionZ = 0.0D;
                if (mc.thePlayer.isCollidedVertically) {
                    mc.thePlayer.jump();
                    move = true;
                }
                if ((move) && (mc.thePlayer.isCollidedVertically)) {
                    move = false;
                }
            }
            if (mc.thePlayer.isCollidedVertically) {
                mc.thePlayer.motionX *= 1.2D;
                mc.thePlayer.motionZ *= 1.2D;
                doMiniHop();
            }
            if ((hop) && (!move) && (mc.thePlayer.posY >= prevY + 0.399994D)) {
                mc.thePlayer.motionY = -100.0D;
                mc.thePlayer.posY = prevY;
                hop = false;
            }
        }
    }

    private void doMiniHop() {
        hop = true;
        prevY = mc.thePlayer.posY;
        mc.thePlayer.jump();
    }

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double getBaseMoveSpeed() {
        double baseSpeed = 0.2873D;
        if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            int amplifier = mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= (1.0D + 0.2D * (amplifier + 1));
        }
        return baseSpeed;
    }

    @EventTarget
    public void onLatestMove(MoveEvent e) {
        if (!speedMode.equalsIgnoreCase("Latest OnGround")) {
            return;
        }
        prevY = mc.thePlayer.posY;
        if (mc.thePlayer.onGround) {
            tickse = 2;
        } else {
            mc.thePlayer.posY = prevY;
        }
        if (round(mc.thePlayer.posY - (int) mc.thePlayer.posY, 3) == round(0.138D, 3)) {
            mc.thePlayer.motionY -= 0.08D;
            e.setY(0.09316090325960147D);
            mc.thePlayer.posY -= 0.09316090325960147D;
        }
        if ((tickse == 1)
                && ((mc.thePlayer.moveForward != 0.0F) || (mc.thePlayer.moveStrafing != 0.0F))) {
            tickse = 2;
            moveSpeed = (1.35D * getBaseMoveSpeed() - 0.01D);
        } else if (tickse == 2) {
            tickse = 3;
            if ((mc.thePlayer.moveForward != 0.0F) || (mc.thePlayer.moveStrafing != 0.0F)) {
                prevY = mc.thePlayer.posY;
                mc.thePlayer.motionY = 0.4D;
                e.setY(0.4D);
                moveSpeed *= 2.149D;
            }
        } else if (tickse == 3) {
            tickse = 4;
            double difference = 0.66D * (lastDist - getBaseMoveSpeed());
            moveSpeed = (lastDist - difference);
        } else {
            if ((mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer,
                    mc.thePlayer.boundingBox.offset(0.0D, mc.thePlayer.motionY, 0.0D)).size() > 0)
                    || (mc.thePlayer.isCollidedVertically)) {
                tickse = 1;
            }
            moveSpeed = (lastDist - lastDist / 159.0D);
        }
        moveSpeed = Math.max(moveSpeed, getBaseMoveSpeed());

        MovementInput movementInput = mc.thePlayer.movementInput;
        float forward = movementInput.moveForward;
        float strafe = movementInput.moveStrafe;
        float yaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
        if ((forward == 0.0F) && (strafe == 0.0F)) {
            e.setX(0.0D);
            e.setZ(0.0D);
        } else if (forward != 0.0F) {
            if (strafe >= 1.0F) {
                yaw += (forward > 0.0F ? -45 : 45);
                strafe = 0.0F;
            } else if (strafe <= -1.0F) {
                yaw += (forward > 0.0F ? 45 : -45);
                strafe = 0.0F;
            }
            if (forward > 0.0F) {
                forward = 1.0F;
            } else if (forward < 0.0F) {
                forward = -1.0F;
            }
        }
        double mx = Math.cos(Math.toRadians(yaw + 90.0F));
        double mz = Math.sin(Math.toRadians(yaw + 90.0F));
        double motionX = forward * moveSpeed * mx + strafe * moveSpeed * mz;
        double motionZ = forward * moveSpeed * mz - strafe * moveSpeed * mx;
        e.setX((forward * moveSpeed * mx + strafe * moveSpeed * mz) * 1.0D);
        e.setZ((forward * moveSpeed * mz - strafe * moveSpeed * mx) * 1.0D);
        canStep = true;
        mc.thePlayer.stepHeight = 0.6F;
        if ((forward == 0.0F) && (strafe == 0.0F)) {
            e.setX(0.0D);
            e.setZ(0.0D);
        } else {
            boolean collideCheck = false;
            if (mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer,
                    mc.thePlayer.boundingBox.expand(0.5D, 0.0D, 0.5D)).size() > 0) {
                collideCheck = true;
            }
            if (forward != 0.0F) {
                if (strafe >= 1.0F) {
                    yaw += (forward > 0.0F ? -45 : 45);
                    strafe = 0.0F;
                } else if (strafe <= -1.0F) {
                    yaw += (forward > 0.0F ? 45 : -45);
                    strafe = 0.0F;
                }
                if (forward > 0.0F) {
                    forward = 1.0F;
                } else if (forward < 0.0F) {
                    forward = -1.0F;
                }
            }
        }
    }

    private boolean timer = true;

    @EventTarget
    public void onTick(TickEvent e) {
        if ((speedMode.equalsIgnoreCase("Latest OnGround")) || (speedMode.equalsIgnoreCase("AAC 1.9.10"))) {
            if (!mc.thePlayer.isEating()) {
                if (!timer) {
                    mc.timer.timerSpeed = 0.705F;
                    timer = true;
                } else {
                    mc.timer.timerSpeed = 1.55F;
                    timer = false;
                }
            } else {
                mc.timer.timerSpeed = 1.0F;
            }
        } else if (speedMode.equalsIgnoreCase("Timer")) {
            mc.timer.timerSpeed = 12.0F;
        }
    }

    @EventTarget
    public void onLatestTick(TickEvent e) {
        if (!speedMode.equalsIgnoreCase("Latest OnGround")) {
            return;
        }
        if (mc.thePlayer.isCollidedVertically) {
            mc.thePlayer.motionX *= 1.5D;
            mc.thePlayer.motionZ *= 1.5D;
        } else {
            mc.thePlayer.motionX *= 2.5D;
            mc.thePlayer.motionZ *= 2.5D;
        }
        if (!mc.thePlayer.isEating()) {
            if (!move) {
                mc.timer.timerSpeed = 0.705F;
                move = true;
            } else {
                mc.timer.timerSpeed = 1.55F;
                move = false;
            }
        } else {
            mc.timer.timerSpeed = 1.0F;
        }
    }
}
