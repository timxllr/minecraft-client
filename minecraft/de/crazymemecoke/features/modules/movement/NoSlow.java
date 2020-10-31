package de.crazymemecoke.features.modules.movement;

import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventMotion;
import de.crazymemecoke.manager.eventmanager.impl.EventUpdate;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import org.lwjgl.input.Keyboard;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlow extends Module {

    public NoSlow() {
        super("NoSlow", Keyboard.KEY_NONE, Category.MOVEMENT, -1);
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