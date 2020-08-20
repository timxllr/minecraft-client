package de.crazymemecoke.features.modules.world;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;

public class Nuker extends Module {

    float nukerRange;

    public Nuker() {
        super("Nuker", Keyboard.KEY_NONE, Category.WORLD, -1);

        Client.instance().setMgr().newSetting(new Setting("Range", this, 3.5, 0, 5, false));
    }

    @Override
    public void onUpdate() {
        if (getState()) {
            nukerRange = (float) Client.instance().setMgr().getSettingByName("Range", this).getNum();
            if (mc.thePlayer.capabilities.isCreativeMode) {
                for (int y = (int) nukerRange; y >= (int) (-nukerRange); --y) {
                    for (int z = (int) (-nukerRange); (float) z <= nukerRange; ++z) {
                        for (int x = (int) (-nukerRange); (float) x <= nukerRange; ++x) {
                            int xPos = (int) Math.round(mc.thePlayer.posX + (double) x);
                            int yPos = (int) Math.round(mc.thePlayer.posY + (double) y);
                            int zPos = (int) Math.round(mc.thePlayer.posZ + (double) z);
                            BlockPos blockPos = new BlockPos(xPos, yPos, zPos);
                            IBlockState state = mc.theWorld.getBlockState(blockPos);
                            if (state.getBlock().getMaterial() != Material.air && mc.thePlayer.getDistance((double) xPos, (double) yPos, (double) zPos) < (double) nukerRange) {
                                mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.NORTH));
                                mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.NORTH));
                            }
                        }
                    }
                }
            }
        }
    }
}
