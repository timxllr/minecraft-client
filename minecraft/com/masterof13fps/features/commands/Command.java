package com.masterof13fps.features.commands;

import com.masterof13fps.Wrapper;
import com.masterof13fps.utils.time.TimeHelper;
import com.masterof13fps.Methods;
import com.masterof13fps.manager.eventmanager.Event;
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