package de.crazymemecoke.features.modules.world;

import de.crazymemecoke.manager.clickguimanager.settings.Setting;
import de.crazymemecoke.Client;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class Fucker extends Module {
    public TimeHelper delay = new TimeHelper();
    private int xOffset;
    private int zOffset;
    private int yOffset;

    public Fucker() {
        super("Fucker", Keyboard.KEY_NONE, Category.WORLD, -1);
        ArrayList<String> options = new ArrayList<>();
        options.add("Beds");
        options.add("Cores");
        options.add("Eggs");
        options.add("Cakes");
        Client.getInstance().getSetmgr().rSetting(new Setting("Mode", this, "Beds", options));
    }

    @Override
    public void onUpdate() {
        String mode = Client.getInstance().getSetmgr().getSettingByName("Mode", this).getValString();
        if (getState()) {
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

    public void smashBlock(BlockPos pos) {
        mc.thePlayer.sendQueue.addToSendQueue(
                new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, EnumFacing.UP));
        mc.thePlayer.sendQueue.addToSendQueue(
                new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.UP));
    }

}
