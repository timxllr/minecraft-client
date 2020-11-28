package de.crazymemecoke.manager.eventmanager.impl;

import de.crazymemecoke.manager.eventmanager.Event;

public class EventSafeWalk extends Event {

    boolean safe;

    public EventSafeWalk(boolean safe) {
        this.safe = safe;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}
