package de.crazymemecoke.utils.events;

import de.crazymemecoke.module.Module;
import net.minecraft.client.renderer.WorldRenderer.State;

public class UpdateEvent{
  public State state;
  public float yaw;
  public float pitch;
  public double y;
  public boolean ground;
  
  
  public UpdateEvent(double y, float yaw, float pitch, boolean ground)
  {
    this.yaw = yaw;
    this.pitch = pitch;
    this.y = y;
    this.ground = ground;
  }
  
  public double getY()
  {
    return this.y;
  }
  
  public float getYaw()
  {
    return this.yaw;
  }
  
  public float getPitch()
  {
    return this.pitch;
  }
  
  public boolean onGround()
  {
    return this.ground;
  }
  
  public State getState()
  {
    return this.state;
  }
}
