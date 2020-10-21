package de.crazymemecoke.manager.events.impl;

import de.crazymemecoke.manager.events.Event;

public class EventMoveFlying extends Event {

    float yaw;

    public EventMoveFlying(float yaw) {
        this.yaw = yaw;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}
