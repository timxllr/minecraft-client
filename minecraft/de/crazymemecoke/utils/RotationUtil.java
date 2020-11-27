package de.crazymemecoke.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class RotationUtil {
    private static Minecraft mc;

    public static float[] getYawAndPitch(Entity entity) {
        double posX = entity.posX;
        double n = posX - mc.thePlayer.posX;
        double posZ = entity.posZ;
        double n2 = posZ - mc.thePlayer.posZ;
        return new float[]{(float) (Math.atan2(n2, n) * 180.0D / 3.141592653589793D) - 90.0F,
                (float) (Math.atan2(mc.thePlayer.posY + 0.12D - (entity.posY + 1.82D), MathHelper.sqrt_double(n + n2)) *
                        180.0D / 3.141592653589793D)};
    }

    public static float getDistanceBetweenAngles(float paramFloat) {
        float n = Math.abs(paramFloat - mc.thePlayer.rotationYaw) % 360.0F;
        if (n > 180.0F) {
            n = 360.0F - n;
        }
        return n;
    }

    public static float[] getEntityRotations(EntityPlayer player, Entity target) {
        double posX = target.posX - player.posX;
        double posY = target.posY + target.getEyeHeight() - (player.posY + player.getEyeHeight());
        double posZ = target.posZ - player.posZ;
        double var14 = MathHelper.sqrt_double(posX * posX + posZ * posZ);
        float yaw = (float) (Math.atan2(posZ, posX) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float) -(Math.atan2(posY, var14) * 180.0D / 3.141592653589793D);
        return new float[]{yaw, pitch};
    }

    public static float getPitchChangeToEntity(Entity entity) {
        double deltaX = entity.posX - mc.thePlayer.posX;
        double deltaZ = entity.posZ - mc.thePlayer.posZ;
        double deltaY = entity.posY - 1.6D + entity.getEyeHeight() - mc.thePlayer.posY;
        double distanceXZ = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);

        double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));

        return -MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationPitch - (float) pitchToEntity);
    }

    public static float[] getAngles(Entity e) {
        return new float[]{getYawChangeToEntity(e) + mc.thePlayer.rotationYaw,
                getPitchChangeToEntity(e) + mc.thePlayer.rotationPitch};
    }

    public static float getYawChangeToEntity(Entity entity) {
        double deltaX = entity.posX - mc.thePlayer.posX;
        double deltaZ = entity.posZ - mc.thePlayer.posZ;
        double yawToEntity;
        if ((deltaZ < 0.0D) && (deltaX < 0.0D)) {
            yawToEntity = 90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
        } else {
            if ((deltaZ < 0.0D) && (deltaX > 0.0D)) {
                yawToEntity = -90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX));
            } else {
                yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
            }
        }
        return MathHelper.wrapAngleTo180_float(-(mc.thePlayer.rotationYaw - (float) yawToEntity));
    }

    public static float[] getFacingRotations(int x, int y, int z, EnumFacing facing) {
        Entity temp = new EntitySnowball(mc.theWorld);
        temp.posX = (x + 0.5D);
        temp.posY = (y + 0.5D);
        temp.posZ = (z + 0.5D);
        temp.posX += facing.getDirectionVec().getX() * 0.25D;
        temp.posY += facing.getDirectionVec().getY() * 0.25D;
        temp.posZ += facing.getDirectionVec().getZ() * 0.25D;
        return getAngles(temp);
    }

    public static float[] getRotations(final double posX, final double posY, final double posZ) {
        final EntityPlayerSP player = mc.thePlayer;
        final double x = posX - player.posX;
        final double y = posY - (player.posY + player.getEyeHeight());
        final double z = posZ - player.posZ;
        final double dist = MathHelper.sqrt_double(x * x + z * z);
        final float yaw = (float) (Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float) (-(Math.atan2(y, dist) * 180.0 / 3.141592653589793));
        return new float[]{yaw, pitch};
    }

    public static float[] getRotationsEntity(final Entity entity) {
        if (mc.thePlayer.moveForward != 0.0f || mc.thePlayer.moveStrafing != 0.0f) {
            return getRotations(entity.posX + MathUtils.randomNumber(0.03, -0.03), entity.posY + entity.getEyeHeight() - 0.4 + MathUtils.randomNumber(0.07, -0.07), entity.posZ + MathUtils.randomNumber(0.03, -0.03));
        }
        return getRotations(entity.posX, entity.posY + entity.getEyeHeight() - 0.4, entity.posZ);
    }

}
