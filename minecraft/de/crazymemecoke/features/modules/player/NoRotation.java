package de.crazymemecoke.features.modules.player;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventTarget;

import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.events.PacketReceiveEvent;
import de.crazymemecoke.utils.render.Rainbow;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class NoRotation extends Module{

	public NoRotation(){
		super("NoRotation", Keyboard.KEY_NONE, Category.PLAYER, Rainbow.rainbow(1, 1).hashCode());
	}
	
	@EventTarget
	  public void onPacketReceive(PacketReceiveEvent e)
	  {
	    if ((e.getPacket() instanceof S08PacketPlayerPosLook))
	    {
	      S08PacketPlayerPosLook p = (S08PacketPlayerPosLook)e.getPacket();
	      if ((p.getYaw() != this.mc.thePlayer.rotationYaw) || (p.getPitch() != this.mc.thePlayer.rotationPitch))
	      {
	        ((S08PacketPlayerPosLook)e.getPacket()).setYaw(mc.thePlayer.rotationYaw);
	        ((S08PacketPlayerPosLook)e.getPacket()).setPitch(mc.thePlayer.rotationPitch);
	      }
	    }
	  }

}
