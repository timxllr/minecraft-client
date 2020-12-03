package de.crazymemecoke.features.commands;

import de.crazymemecoke.Methods;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.Wrapper;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.Minecraft;

public abstract class Command extends Methods implements Wrapper {

    public static Minecraft mc = Minecraft.mc();
    public TimeHelper timeHelper = new TimeHelper();
    private String label;

    public abstract void execute(String[] args);

    public abstract String getName();

    public void onEvent(Event event) {

    }

    public String[] getArguments() {
		return this.label.split(" ");
    }

}