package de.crazymemecoke.features.commands;

import net.minecraft.client.Minecraft;

public abstract class Command {

	public static Minecraft mc = Minecraft.mc();
	
	public abstract void execute(String[] args);
	
	public abstract String getName();
	
	private String label;
	
	  public String[] getArguments()
	  {
	    String[] argus = this.label.split(" ");
	    return argus;
	  }
	
}