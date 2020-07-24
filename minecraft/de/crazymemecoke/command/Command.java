package de.crazymemecoke.command;

import de.crazymemecoke.utils.Wrapper;
import net.minecraft.util.ChatComponentText;

public abstract class Command {
	
	public abstract void execute(String[] args);
	
	public abstract String getName();
	
	private String label;
	
	  public String[] getArguments()
	  {
	    String[] argus = this.label.split(" ");
	    return argus;
	  }
	
}