package com.masterof13fps.features.modules.impl.misc;

import com.masterof13fps.features.modules.Module;
import com.masterof13fps.features.modules.Category;
import com.masterof13fps.features.modules.ModuleInfo;
import com.masterof13fps.manager.eventmanager.Event;
import com.masterof13fps.manager.eventmanager.impl.EventTick;
import com.masterof13fps.manager.settingsmanager.Setting;

import java.util.Random;

@ModuleInfo(name = "Spammer", category = Category.MISC, description = "Spams the chat with pre-defined messages")
public class Spammer extends Module {

    public Setting antiSpam = new Setting("Anti Spam", this, true);
    public Setting delay = new Setting("Delay", this, 2, 0.1, 20, false);

    String[] messages = {"Got Rekt? Get Vanity!", "Enjoy Eskay!", "N00B G3T R3KT", "www.masterof13fps.com", "Sub CrazyMemeCoke on YT!"};
    int lastUsed;

    private String randomPhrase() {
        Random rand = new Random();
        int randInt = rand.nextInt(messages.length);
        while (lastUsed == randInt) {
            randInt = rand.nextInt(messages.length);
        }
        lastUsed = randInt;
        return messages[randInt];
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof EventTick) {
            if (timeHelper.hasReached((long) (delay.getCurrentValue() * 1000))) {
                if (antiSpam.isToggled()) {
                    Random random = new Random();
                    int randomInt = random.nextInt(2000) + 2000;
                    sendChatMessage(randomPhrase() + " | " + randomInt);
                } else {
                    sendChatMessage(randomPhrase());
                }

                timeHelper.reset();
            }
        }
    }
}
