package de.crazymemecoke.manager.events.impl;

import de.crazymemecoke.manager.events.Event;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacket extends Event {

    Type type;
    Packet<? extends INetHandler> packet;

    public EventPacket(Type type, Packet<? extends INetHandler> packet) {
        this.type = type;
        this.packet = packet;
    }

    public Packet<? extends INetHandler> getPacket() {
        return packet;
    }

    public Type getType() {
        return type;
    }

    public void setPacket(Packet<? extends INetHandler> packet) {
        this.packet = packet;
    }

    public enum Type {
        SEND, RECEIVE;
    }
}
