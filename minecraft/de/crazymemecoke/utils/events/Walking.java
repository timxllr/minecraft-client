package de.crazymemecoke.utils.events;

public class Walking implements Event {
	
	boolean walk = false;

	public void setSafeWalk(boolean walk)
	{
		this.walk = walk;
	}

	public boolean getSafeWalk()
	{
		return this.walk;
	}
}

