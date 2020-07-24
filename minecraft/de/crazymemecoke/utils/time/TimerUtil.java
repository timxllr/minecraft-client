package de.crazymemecoke.utils.time;

public class TimerUtil
{
  private long lastMS;
  
  public long getCurrentMS()
  {
    return System.nanoTime() / 1000000L;
  }
  
  public long getLastMS()
  {
    return this.lastMS;
  }
  
  public boolean hasReached(float f)
  {
    return (float)(getCurrentMS() - this.lastMS) >= f;
  }
  
  public void reset()
  {
    this.lastMS = getCurrentMS();
  }
  
  public void setLastMS(long currentMS)
  {
    this.lastMS = currentMS;
  }
  
  public boolean isDelayComplete(long delay)
  {
    if (System.currentTimeMillis() - this.lastMS >= delay) {
      return true;
    }
    return false;
  }

  private long lastMs;

  public long getLastMs() {
    return this.lastMs;
  }

  public void setLastMs(int i) {
    this.lastMs = System.currentTimeMillis() + (long)i;
  }


  public void setLastMS()
  {
    this.lastMS = System.currentTimeMillis();
  }
  
  public int convertToMS(int perSecond)
  {
    return 1000 / perSecond;
  }
}
