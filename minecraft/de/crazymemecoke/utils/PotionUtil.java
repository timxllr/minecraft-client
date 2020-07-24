package de.crazymemecoke.utils;

import java.util.ArrayList;

import net.minecraft.potion.Potion;

public class PotionUtil {
    public PotionUtil() {
        addPotion(Potion.absorption);
        addPotion(Potion.blindness);
        addPotion(Potion.confusion);
        addPotion(Potion.damageBoost);
        addPotion(Potion.digSlowdown);
        addPotion(Potion.digSpeed);
        addPotion(Potion.fireResistance);
        addPotion(Potion.harm);
        addPotion(Potion.heal);
        addPotion(Potion.hunger);
        addPotion(Potion.invisibility);
        addPotion(Potion.jump);
        addPotion(Potion.moveSlowdown);
        addPotion(Potion.moveSpeed);
        addPotion(Potion.nightVision);
        addPotion(Potion.poison);
        addPotion(Potion.regeneration);
        addPotion(Potion.resistance);
        addPotion(Potion.saturation);
        addPotion(Potion.waterBreathing);
        addPotion(Potion.weakness);
        addPotion(Potion.wither);
    }

    private void addPotion(Potion p) {
        this.allPotionTypes.add(p);
    }

    private ArrayList<Potion> allPotionTypes = new ArrayList();

    public Potion getPotionFromId(int id) {
        for (Potion p : this.allPotionTypes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
