package de.crazymemecoke.manager.eventmanager;

public class Event {

    boolean canceled = false;

    public boolean isCancelled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
