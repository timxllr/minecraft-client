package de.crazymemecoke.features.modules.impl.world;

import de.crazymemecoke.features.modules.ModuleInfo;
import de.crazymemecoke.manager.settingsmanager.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

@ModuleInfo(name = "Fucker", category = Category.WORLD, description = "Automatically breaks selected blocks")
public class Fucker extends Module {

    private int xOffset;
    private int zOffset;
    private int yOffset;

    Setting modes = new Setting("Mode", this, "Beds", new String[] {"Beds", "Cores", "Eggs", "Cakes"});;

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public void smashBlock(BlockPos pos) {
        mc.thePlayer.sendQueue.addToSendQueue(
                new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
        mc.thePlayer.sendQueue.addToSendQueue(
                new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.UP));
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventUpdate) {
            String mode = Client.main().setMgr().settingByName("Mode", this).getCurrentMode();
            for (this.xOffset = -5; this.xOffset < 6; ++this.xOffset) {
                for (this.zOffset = -5; this.zOffset < 6; ++this.zOffset) {
                    for (this.yOffset = 5; this.yOffset > -5; --this.yOffset) {
                        double x = mc.thePlayer.posX + (double) this.xOffset;
                        double y = mc.thePlayer.posY + (double) this.yOffset;
                        double z = mc.thePlayer.posZ + (double) this.zOffset;
                        int id = Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock());
                        if (mode.equalsIgnoreCase("Beds")) {
                            if (id == 26) {
                                smashBlock(new BlockPos(x, y, z));
                                break;
                            }
                        }
                        if (mode.equalsIgnoreCase("Cores")) {
                            if (id == 138) {
                                this.smashBlock(new BlockPos(x, y, z));
                                break;
                            }
                        }
                        if (mode.equalsIgnoreCase("Eggs")) {
                            if (id == 122) {
                                this.smashBlock(new BlockPos(x, y, z));
                                break;
                            }
                        }
                        if (mode.equalsIgnoreCase("Cakes")) {
                            if (id == 92) {
                                this.smashBlock(new BlockPos(x, y, z));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
