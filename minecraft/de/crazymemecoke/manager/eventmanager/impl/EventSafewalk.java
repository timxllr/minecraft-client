package de.crazymemecoke.manager.eventmanager.impl;

import de.crazymemecoke.manager.eventmanager.Event;

public class EventSafewalk extends Event {

    boolean safe;

    public EventSafewalk(boolean safe) {
        this.safe = safe;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}
