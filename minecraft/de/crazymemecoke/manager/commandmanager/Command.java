package de.crazymemecoke.manager.commandmanager;

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