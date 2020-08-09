package de.crazymemecoke.utils.events;

import de.crazymemecoke.utils.events.eventapi.events.callables.EventCancellable;

import net.minecraft.network.Packet;

public class PacketReceiveEvent
  extends EventCancellable
{
  private Packet packet;
  
  public PacketReceiveEvent(Packet packet)
  {
    this.packet = packet;
  }
  
  public Packet getPacket()
  {
    return this.packet;
  }
  
  public void setPacket(Packet packet)
  {
    this.packet = packet;
  }
}
