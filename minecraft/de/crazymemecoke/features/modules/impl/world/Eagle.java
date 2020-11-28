package de.crazymemecoke.features.modules.impl.world;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import de.crazymemecoke.features.modules.Category;
import de.crazymemecoke.features.modules.Module;
import de.crazymemecoke.features.modules.ModuleInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

@ModuleInfo(name = "Eagle", category = Category.WORLD, description = "Automatically fastbridges")
public class Eagle extends Module {
    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof EventUpdate) {
            BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0D, mc.thePlayer.posZ);
            if (mc.thePlayer.fallDistance <= 4.0F) {
                mc.gameSettings.keyBindSneak.pressed = mc.theWorld.getBlockState(bp).getBlock() == Blocks.air;
            }
        }
    }
}
