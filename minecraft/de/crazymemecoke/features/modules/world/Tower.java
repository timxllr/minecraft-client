package de.crazymemecoke.features.modules.world;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.events.PacketSendEvent;
import de.crazymemecoke.utils.events.eventapi.EventTarget;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;

public class Tower extends Module {
    public Tower() {
        super("Tower", Keyboard.KEY_NONE, Category.WORLD, -1);

        Client.main().setMgr().newSetting(new Setting("Slow", this, false));
    }

    private final TimeHelper time = new TimeHelper();

    @Override
    public void onPreMotionUpdate() {
        super.onPreMotionUpdate();

        if (getState()) {
            final BlockPos pos = new BlockPos(Tower.mc.thePlayer.posX, Tower.mc.thePlayer.posY - 1.0, Tower.mc.thePlayer.posZ);
            final EnumFacing face = getFacingDirection(pos);
            try {
                boolean slow = Client.main().setMgr().settingByName("Slow", this).getBool();
                if (time.hasReached(slow ? 150 : 75) && Tower.mc.thePlayer.getCurrentEquippedItem().getItem() != null && Tower.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
                    Tower.mc.thePlayer.setPosition(Tower.mc.thePlayer.posX, Tower.mc.thePlayer.posY + 1.1, Tower.mc.thePlayer.posZ);
                    final float[] rotations = BlockHelper.getBlockRotations(Tower.mc.thePlayer.posX, Tower.mc.thePlayer.posY - 1.0, Tower.mc.thePlayer.posZ);
                    if (!Tower.mc.thePlayer.onGround) {
                        mc.rightClickMouse();
                        Tower.mc.thePlayer.swingItem();
                    }
                    time.reset();
                }
            } catch (Exception ignored) {
            }
        }
    }

    @EventTarget
    public void onPacket(PacketSendEvent e) {
        if (getState()) {
            if (e.getPacket() instanceof C03PacketPlayer) {
                final C03PacketPlayer player = (C03PacketPlayer) e.getPacket();
                final float[] rotations = BlockHelper.getBlockRotations(Tower.mc.thePlayer.posX, Tower.mc.thePlayer.posY - 1.0, Tower.mc.thePlayer.posZ);
                player.yaw = rotations[0];
                player.pitch = rotations[1];
            }
        }
    }

    private EnumFacing getFacingDirection(final BlockPos pos) {
        EnumFacing direction = null;
        if (!Tower.mc.theWorld.getBlockState(pos.add(0, 1, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.UP;
        } else if (!Tower.mc.theWorld.getBlockState(pos.add(0, -1, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.DOWN;
        } else if (!Tower.mc.theWorld.getBlockState(pos.add(1, 0, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.EAST;
        } else if (!Tower.mc.theWorld.getBlockState(pos.add(-1, 0, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.WEST;
        } else if (!Tower.mc.theWorld.getBlockState(pos.add(0, 0, 1)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.SOUTH;
        } else if (!Tower.mc.theWorld.getBlockState(pos.add(0, 0, 1)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.NORTH;
        }
        return direction;
    }
}