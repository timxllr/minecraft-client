package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.modulemanager.ModuleInfo;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

@ModuleInfo(name = "NoSlow", category = Category.MOVEMENT, description = "You won't get slowed down anymore while blocking, eating or walking through webs")
public class NoSlow extends Module {

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
        if (event instanceof EventMotion) {
            if (((EventMotion) event).getType() == EventMotion.Type.PRE) {
                if (mc.thePlayer.isBlocking()) {
                    mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.UP));
                }
            }
            if (((EventMotion) event).getType() == EventMotion.Type.POST) {
                if (mc.thePlayer.isBlocking()) {
                    mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
                }
            }
        }
    }
}