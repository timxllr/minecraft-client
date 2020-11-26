package de.crazymemecoke.manager.eventmanager.impl;

import de.crazymemecoke.manager.eventmanager.Event;

public class EventKeyPress extends Event {

    int key;

    public EventKeyPress(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
