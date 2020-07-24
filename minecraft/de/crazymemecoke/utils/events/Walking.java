package de.crazymemecoke.utils.events;

import de.crazymemecoke.utils.events.Event;

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

