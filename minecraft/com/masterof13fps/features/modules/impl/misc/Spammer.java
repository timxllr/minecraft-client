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

    Setting mode = new Setting("Mode", this, "Normal", new String[]{"Normal", "Memes"});
    Setting antiSpam = new Setting("Anti Spam", this, true);
    Setting delay = new Setting("Delay", this, 2, 0.1, 20, false);

    String[] messages = {"Got Rekt? Get " + getClientName() + "!", "Enjoy Eskay!", "N00B G3T R3KT", "www" +
            ".masterof13fps" +
            ".com", "Sub " +
            "CrazyMemeCoke on YT!"};
    String[] memeMessages = {"Pastebin, weil Ich ein Paste bin", "Ja Ok, Ich bin 12", "Ich fand Nero dumm, und jetzt " +
            "ist er auch noch Asiate", "Jannick hat eine Gomme Aura", "Ich weiß, woran's liegt ...", "Du kannst aus " +
            "jeder Schlampe eine Bitch machen ...", "Ich glaub Ich muss noch meine Tabletten nehmen.", "Spiel mit " +
            "mir, Ich bin performanter als Eject", "Ich bin Jannick, mich besteigt man.", "Kein Ding für'n Ping.",
            "Woran erkennt man wo Osten ist? Man legt eine Banane auf die Mauer und guckt wo abgebissen wird.",
            "Welcher Client ist unperformanter als Eject?"};
    int lastUsed;

    private String randomPhrase() {
        Random rand = new Random();
        int randInt = 0;
        switch(mode.getCurrentMode()){
            case "Normal": {
                randInt = rand.nextInt(messages.length);
                while (lastUsed == randInt) {
                    randInt = rand.nextInt(messages.length);
                }
                lastUsed = randInt;
                return messages[randInt];
            }
            case "Memes":{
                randInt = rand.nextInt(memeMessages.length);
                while (lastUsed == randInt) {
                    randInt = rand.nextInt(memeMessages.length);
                }
                lastUsed = randInt;
                return memeMessages[randInt];
            }
        }
        return null;
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
