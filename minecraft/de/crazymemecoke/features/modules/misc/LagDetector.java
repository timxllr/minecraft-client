package de.crazymemecoke.features.modules.misc;

import de.crazymemecoke.Client;
import de.crazymemecoke.manager.eventmanager.Event;
import de.crazymemecoke.manager.eventmanager.impl.EventPacket;
import de.crazymemecoke.manager.eventmanager.impl.EventRender;
import de.crazymemecoke.manager.fontmanager.UnicodeFontRenderer;
import de.crazymemecoke.manager.modulemanager.Category;
import de.crazymemecoke.manager.modulemanager.Module;
import de.crazymemecoke.utils.render.RenderUtils;
import de.crazymemecoke.utils.time.TimeHelper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.server.S02PacketChat;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class LagDetector extends Module {

    TimeHelper timeHelper = new TimeHelper();

    public LagDetector() {
        super("LagDetector", Keyboard.KEY_NONE, Category.MISC, -1);
    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventRender) {
            if (((EventRender) event).getType() == EventRender.Type.twoD) {
                if (timeHelper.hasReached(1000L)) {
                    ScaledResolution s = new ScaledResolution(mc);

                    UnicodeFontRenderer font = Client.main().fontMgr().font("Comfortaa", 32, Font.BOLD);

                    int currentMS = (int) (timeHelper.getCurrentMS() / 1000);
                    int lastMS = (int) (timeHelper.getLastMS() / 1000);
                    int lagSeconds = currentMS - lastMS;
                    int lagMS = (int) (timeHelper.getCurrentMS() - timeHelper.getLastMS());

                    String lagMessage = "Der Server sendet keine Reaktionen mehr (Lag?)";
                    String laggingSince = "Lag seit: " + lagSeconds + " Sekunde(n) / " + lagMS + "ms";

                    font.drawStringWithShadow(lagMessage, s.width() / 2 - font.getStringWidth(lagMessage) / 2, 5, -1);
                    font.drawStringWithShadow(laggingSince, s.width() / 2 - font.getStringWidth(laggingSince) / 2, 20, -1);
                }
            }
        }
        if (event instanceof EventPacket) {
            if (((EventPacket) event).getType() == EventPacket.Type.RECEIVE) {
                if (!(((EventPacket) event).getPacket() instanceof S02PacketChat)) {
                    timeHelper.reset();
                }
            }
        }
    }
}